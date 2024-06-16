package com.gokhanakbas.isyeriegitimiandroidapp.presentation.firm.advertsOfFirmPage

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.gokhanakbas.isyeriegitimiandroidapp.R
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Advert
import com.gokhanakbas.isyeriegitimiandroidapp.presentation.navigation.Screen
import com.gokhanakbas.isyeriegitimiandroidapp.presentation.navigation.SharedViewModel
import com.gokhanakbas.isyeriegitimiandroidapp.presentation.util.components.AdvertDeleteQuestionComp
import com.gokhanakbas.isyeriegitimiandroidapp.presentation.util.components.LoadingDialog
import com.gokhanakbas.isyeriegitimiandroidapp.ui.theme.GaziAcikMavi
import com.gokhanakbas.isyeriegitimiandroidapp.ui.theme.GaziKoyuMavi
import com.gokhanakbas.isyeriegitimiandroidapp.util.Constants
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun AdvertsOfFirmPage(
    paddingValues: PaddingValues,
    navController: NavController,
    viewModel: AdvertsOfFirmPageViewModel = hiltViewModel(),
    sharedViewModel: SharedViewModel
) {

    LaunchedEffect(key1 = viewModel) {
        viewModel.getFirmAdverts(Constants.FIRM_ID)
    }

    AdvertsOfFirmPageContent(
        paddingValues = paddingValues,
        navController = navController,
        viewModel = viewModel,
        sharedViewModel = sharedViewModel
    )

}

@Composable
private fun AdvertsOfFirmPageContent(
    paddingValues: PaddingValues,
    navController: NavController,
    viewModel: AdvertsOfFirmPageViewModel,
    sharedViewModel: SharedViewModel
) {


    val state by viewModel.state.collectAsStateWithLifecycle()

    LoadingDialog(isLoading = state.isLoading)

    val advertList = state.advertList

    val indexOfAdvert = remember {
        mutableIntStateOf(0)
    }

    val deleteQuestionState = remember {
        mutableStateOf(false)
    }
    val deleteQuestionResultState = remember {
        mutableStateOf(false)
    }

    val willDeleteAdvert = remember {
        mutableStateOf<Advert?>(null)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        val alertDialogOfInterViewers = remember {
            mutableStateOf(false)
        }



        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            items(
                count = advertList.count(),
                key = { advertList[it].id }
            ) { index ->
                val advert = advertList[index]

                Card(
                    modifier = Modifier
                        .padding(5.dp)
                        .fillMaxWidth(), elevation = CardDefaults.cardElevation(
                        defaultElevation = 15.dp
                    ), colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    )
                ) {
                    Column(modifier = Modifier.fillMaxSize()) {
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(5.dp)
                        ) {
                            Text(
                                text = stringResource(id = R.string.ilan) + "#${index + 1}",
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp
                            )
                        }
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(5.dp)
                        ) {
                            Text(
                                text = advert.advert_title,
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp
                            )
                            Image(
                                painter = painterResource(id = R.drawable.gazi_university_logo),
                                contentDescription = "Icon Of Advert Of Firm",
                                modifier = Modifier.size(50.dp)
                            )
                        }
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.End,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(5.dp)
                        ) {
                            OutlinedButton(
                                onClick = {
                                    //Ilani duzenleme islemi gerceklesecek
                                    sharedViewModel.addAdvert(advert)
                                    navController.navigate(Screen.AdvertCreateOrEditPage.route)

                                }, colors = ButtonDefaults.outlinedButtonColors(
                                    containerColor = Color.White,
                                    contentColor = Color.Black,
                                    disabledContainerColor = GaziKoyuMavi
                                ), shape = RoundedCornerShape(10.dp)
                            ) {
                                Text(text = stringResource(id = R.string.ilani_duzenle))
                            }
                            OutlinedButton(
                                onClick = {
                                    //Ilani silme islemi gerceklesecek

                                    deleteQuestionState.value = true
                                    willDeleteAdvert.value = advert

                                }, colors = ButtonDefaults.buttonColors(
                                    containerColor = Color.Red,
                                    contentColor = Color.White
                                ), shape = RoundedCornerShape(10.dp)
                            ) {
                                Text(text = stringResource(id = R.string.sil))
                            }
                            OutlinedButton(
                                onClick = {
                                    //Ilan a basvurmus ogrencileri gosterme islemi gerceklesecek
                                    alertDialogOfInterViewers.value = true
                                    indexOfAdvert.intValue = index
                                }, colors = ButtonDefaults.buttonColors(
                                    containerColor = GaziAcikMavi,
                                    contentColor = Color.Black
                                ), shape = RoundedCornerShape(10.dp)
                            ) {
                                Text(text = stringResource(id = R.string.basvuranlari_Goster))
                            }
                        }
                    }
                }
            }
        }
        OutlinedButton(
            onClick = {
                //Burada yeni ilan olusturma islemi gerceklesecek
                sharedViewModel.addAdvert(null)
                navController.navigate(Screen.AdvertCreateOrEditPage.route)

            },
            colors = ButtonDefaults.outlinedButtonColors(
                containerColor = GaziKoyuMavi,
                contentColor = Color.White
            ),
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(15.dp),
            shape = RoundedCornerShape(20.dp),
        ) {
            Text(text = stringResource(id = R.string.ilan_olustur))
        }

        if (alertDialogOfInterViewers.value) {
            AlertDialogOfInterviewerList(
                advert = advertList[indexOfAdvert.intValue],
                alertDialogOfInterviewers = alertDialogOfInterViewers,
                navController = navController
            )
        }

        AdvertDeleteQuestionComp(
            deleteQuestion = deleteQuestionState,
            result = deleteQuestionResultState
        )

        LaunchedEffect(key1 = deleteQuestionState.value) {
            if (deleteQuestionResultState.value) {
                CoroutineScope(Dispatchers.IO).launch {
                    deleteQuestionResultState.value = false
                    val job = viewModel.deleteAdvert(advert_id = willDeleteAdvert.value!!.advert_id)
                    if (job.await()) {
                        advertList.remove(willDeleteAdvert.value)
                        willDeleteAdvert.value = null
                    }
                }
            }

        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlertDialogOfInterviewerList(
    advert: Advert,
    alertDialogOfInterviewers: MutableState<Boolean>,
    navController: NavController
) {
    BasicAlertDialog(
        onDismissRequest = { alertDialogOfInterviewers.value = false }, modifier = Modifier.clip(
            RoundedCornerShape(20.dp)
        )
    ) {
        Column(
            modifier = Modifier
                .size(500.dp, 600.dp)
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(100.dp)
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(id = R.string.basvuran_listesi) + " " + advert.advert_title,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold, modifier = Modifier.weight(0.8f)
                )
                IconButton(onClick = { alertDialogOfInterviewers.value = false }) {
                    Icon(
                        painter = painterResource(id = R.drawable.cross_icon),
                        contentDescription = "Close Icon for Interviewers List",
                        modifier = Modifier.size(30.dp)
                    )
                }
            }
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(
                    count = advert.advert_interviewers.value.size,
                    key = { advert.advert_interviewers.value[it].id }
                ) {
                    val interviewer = advert.advert_interviewers.value[it]
                    Card(
                        modifier = Modifier
                            .padding(5.dp)
                            .fillMaxWidth()
                            .heightIn(max = 80.dp),
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 10.dp
                        ), colors = CardDefaults.cardColors(
                            containerColor = Color.White,
                        )
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(
                                modifier = Modifier.weight(0.6f),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Start
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.student_thin_icon),
                                    contentDescription = "Student Image in Interviewer List"
                                )
                                Spacer(modifier = Modifier.width(10.dp))
                                Text(
                                    text = interviewer.interviewer_student_name,
                                    fontWeight = FontWeight.Bold,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                            Row(
                                modifier = Modifier.weight(0.4f),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.End
                            ) {
                                OutlinedButton(
                                    onClick = {
                                        //Ogrenciyi gosterecek
                                        alertDialogOfInterviewers.value = false
                                        navController.navigate(
                                            Screen.StudentPage.passNavigate(
                                                student_no = interviewer.interviewer_student_id
                                            )
                                        )

                                    },
                                    colors = ButtonDefaults.outlinedButtonColors(
                                        contentColor = Color.Black
                                    ),
                                    border = BorderStroke(0.5.dp, Color.Green),
                                ) {
                                    Text(
                                        text = stringResource(id = R.string.ogrenciyi_goruntule),
                                        overflow = TextOverflow.Ellipsis,
                                        textAlign = TextAlign.Center
                                    )
                                }
                            }
                        }

                    }
                }

            }
        }
    }
}

