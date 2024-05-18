package com.gokhanakbas.isyeriegitimiandroidapp.presentation.firm

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
import androidx.compose.runtime.MutableState
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.gokhanakbas.isyeriegitimiandroidapp.R
import com.gokhanakbas.isyeriegitimiandroidapp.presentation.navigation.Screen
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Advert
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Firm
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Student
import com.gokhanakbas.isyeriegitimiandroidapp.ui.theme.GaziAcikMavi
import com.gokhanakbas.isyeriegitimiandroidapp.ui.theme.GaziKoyuMavi
import com.google.gson.Gson

@Composable
fun AdvertsOfFirm(paddingValues: PaddingValues,navController: NavController) {
    val studentListOf = emptyList<Student>()

    val advertList = emptyList<Advert>()


    val indexOfAdvert= remember {
        mutableIntStateOf(0)
    }
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)) {

        val alertDialogOfInterViewers= remember {
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
                                    //Ilani silme islemi gerceklesecek

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
                                    alertDialogOfInterViewers.value=true
                                    indexOfAdvert.intValue=index
                                }, colors = ButtonDefaults.buttonColors(
                                    containerColor = GaziAcikMavi,
                                    contentColor = Color.White
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
                navController.navigate(Screen.AdvertCreatingPage.route)

            }, colors = ButtonDefaults.outlinedButtonColors(
                containerColor = Color.Green,
                contentColor = Color.White
            ), modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(15.dp), shape = RoundedCornerShape(0.dp,10.dp,0.dp,10.dp),
        ) {
            Text(text = stringResource(id = R.string.ilan_olustur))
        }

        if(alertDialogOfInterViewers.value){
            AlertDialogOfInterviewerList(advert = advertList[indexOfAdvert.intValue], alertDialogOfInterviewers = alertDialogOfInterViewers, navController = navController)
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
                    count = advert.advert_interviewers .size,
                    key = { advert.advert_interviewers[it].id }
                ) {
                    val student = advert.advert_interviewers[it]
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
                                modifier = Modifier.weight(0.8f),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Start
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.student_thin_icon),
                                    contentDescription = "Student Image in Interviewer List"
                                )
                                Spacer(modifier = Modifier.width(10.dp))
                                Text(text = student.student_name, fontWeight = FontWeight.Bold)
                            }
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                OutlinedButton(
                                    onClick = {
                                        //Ogrenciyi gosterecek
                                        alertDialogOfInterviewers.value = false
                                        navController.navigate(Screen.StudentPage.passNavigate(student_no = student.student_no))
                                    }, colors = ButtonDefaults.outlinedButtonColors(
                                        containerColor = Color.Green,
                                        contentColor = Color.White
                                    )
                                ) {
                                    Text(text = stringResource(id = R.string.ogrenciyi_goruntule))
                                }
                            }
                        }

                    }
                }

            }
        }
    }
}

