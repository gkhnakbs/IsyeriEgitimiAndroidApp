package com.gokhanakbas.isyeriegitimiandroidapp.presentation.pages.advertsPage

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.gokhanakbas.isyeriegitimiandroidapp.R
import com.gokhanakbas.isyeriegitimiandroidapp.presentation.navigation.Screen
import com.gokhanakbas.isyeriegitimiandroidapp.presentation.navigation.SharedViewModel
import com.gokhanakbas.isyeriegitimiandroidapp.presentation.util.components.ApplyQuestionComp
import com.gokhanakbas.isyeriegitimiandroidapp.presentation.util.components.LoadingDialog
import com.gokhanakbas.isyeriegitimiandroidapp.ui.theme.GaziAcikMavi
import com.gokhanakbas.isyeriegitimiandroidapp.util.Constants
import kotlinx.coroutines.delay

@Composable
fun AdvertPage(
    navController: NavController,
    advert_id: String,
    advertPageViewModel: AdvertPageViewModel = hiltViewModel(),
    sharedViewModel: SharedViewModel
) {

    LaunchedEffect(key1 = advertPageViewModel) {
        advertPageViewModel.getAdvertsInformation(advert_id)
    }

    val state by advertPageViewModel.state.collectAsStateWithLifecycle()

    AdvertPageContent(
        navController = navController,
        advertPageState = state,
        viewModel = advertPageViewModel
    )

}

@Composable
fun AdvertPageContent(
    navController: NavController,
    advertPageState: AdvertPageState,
    viewModel: AdvertPageViewModel
) {

    LoadingDialog(isLoading = advertPageState.isLoading)

    val textOfApplyButton = remember {
        mutableIntStateOf(R.string.basvur)
    }

    val applyQuestion = remember {
        mutableStateOf(false)
    }

    val context = LocalContext.current

    //Burada firm un bilgileri advertten gelen id ye gore cekilir.
    val advert_title = advertPageState.advert.advert_title ?: ""
    val advert_description = advertPageState.advert.advert_details ?: ""
    val advert_firmName = advertPageState.advert.advert_firm?.firm_name ?: ""
    val advert_firmInfo = advertPageState.advert.advert_firm?.firm_info ?: ""

    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(GaziAcikMavi)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Row {
                    IconButton(onClick = {
                        navController.navigateUp()
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.arrow_back_icon),
                            contentDescription = "",
                            modifier = Modifier
                                .size(30.dp)
                                .clip(CircleShape)
                                .background(Color.White),
                            tint = Color.Black
                        )
                    }
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(5.dp)
                    .background(shape = RoundedCornerShape(15.dp), color = Color.White)

            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Top
                ) {
                    Text(
                        text = advert_firmName,
                        style = MaterialTheme.typography.headlineLarge,
                        modifier = Modifier.clickable {
                            navController.navigate(Screen.FirmPage.passNavigate(advertPageState.advert.advert_firm_id))
                        }, textDecoration = TextDecoration.Underline)
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.LightGray)
                            .height(1.dp)
                            .padding(all = 5.dp)
                    )
                    Text(text = advert_title, style = MaterialTheme.typography.headlineMedium)
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(elevation = 5.dp)
                    .background(shape = RoundedCornerShape(15.dp), color = Color.White)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(all = 10.dp),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Top
                ) {
                    Text(
                        text = stringResource(id = R.string.senden_beklenenler),
                        style = MaterialTheme.typography.titleLarge
                    )
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(Color.LightGray)
                            .padding(5.dp)
                    )
                    Text(
                        text = advert_description,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Start
                    )

                    Spacer(
                        modifier = Modifier
                            .padding(5.dp)
                    )

                    Text(
                        text = stringResource(id = R.string.firma_hakkinda),
                        style = MaterialTheme.typography.titleLarge
                    )
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(Color.LightGray)
                            .padding(5.dp)
                    )
                    Text(
                        text = advert_firmInfo,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Start
                    )
                }
            }

        }

        if (Constants.USER_TYPE == Constants.STUDENT) {
            OutlinedButton(
                onClick = {
                    //Basvurma işlemi gerçekleşecek ve database e eklenecek
                    applyQuestion.value = true
                },
                modifier = Modifier.align(Alignment.BottomCenter),
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = Color.White
                )
            ) {
                Text(
                    text = stringResource(id = textOfApplyButton.intValue),
                    style = MaterialTheme.typography.headlineSmall
                )
            }
        }

    }

    val result = remember {
        mutableStateOf(false)
    }

    ApplyQuestionComp(applyQuestion = applyQuestion, result)

    LaunchedEffect(key1 = result.value) {
        if (result.value) {
            viewModel.applyToAdvert(context, advertPageState.advert.advert_id, Constants.STUDENT_NO)
            result.value = false
        }
        println("Çalıştı result")
    }

    LaunchedEffect(key1 = advertPageState.applyResult) {
        if (advertPageState.applyResult == "Success") {
            delay(800)
            navController.popBackStack()
        }
    }


}

