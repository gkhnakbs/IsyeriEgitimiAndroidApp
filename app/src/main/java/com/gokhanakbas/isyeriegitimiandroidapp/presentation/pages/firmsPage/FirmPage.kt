package com.gokhanakbas.isyeriegitimiandroidapp.presentation.pages.firmsPage

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.gokhanakbas.isyeriegitimiandroidapp.R
import com.gokhanakbas.isyeriegitimiandroidapp.presentation.firm.firmMainPage.lastPage
import com.gokhanakbas.isyeriegitimiandroidapp.presentation.navigation.Screen
import com.gokhanakbas.isyeriegitimiandroidapp.presentation.navigation.SharedViewModel
import com.gokhanakbas.isyeriegitimiandroidapp.presentation.util.components.LoadingDialog
import com.gokhanakbas.isyeriegitimiandroidapp.ui.theme.GaziAcikMavi
import com.gokhanakbas.isyeriegitimiandroidapp.ui.theme.GaziKoyuMavi
import com.gokhanakbas.isyeriegitimiandroidapp.util.Constants

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FirmPage(
    navController: NavController,
    firm_id: String,
    firmPageViewModel: FirmPageViewModel = hiltViewModel(),
    sharedViewModel: SharedViewModel
) {

    val state by firmPageViewModel.state.collectAsState()

    if (Constants.USER_TYPE != Constants.FIRM) {
        LaunchedEffect(key1 = firmPageViewModel) {
            firmPageViewModel.getFirmInformation(firm_id)
            firmPageViewModel.getFirmAdverts(firm_id)
        }
    } else {
        LaunchedEffect(key1 = firmPageViewModel) {
            firmPageViewModel.getFirmAdverts(firm_id)
        }
        state.firm = sharedViewModel.firm!!
    }

    FirmPageContent(navController = navController, firmPageState = state)
}

@Composable
fun FirmPageContent(navController: NavController, firmPageState: FirmPageState) {

    LoadingDialog(isLoading = firmPageState.isLoading)

    val firmObject = firmPageState.firm

    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .background(GaziAcikMavi)
    ) {
        Column(
            modifier = Modifier
                .background(GaziAcikMavi)
                .fillMaxSize()
                .padding(top = 110.dp)
        ) {
            Spacer(modifier = Modifier.height(105.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(20.dp))
                        .background(Color.White)
                        .padding(top = 10.dp, start = 10.dp, end = 10.dp, bottom = 20.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                    ) {
                        Text(
                            text = stringResource(id = R.string.firma_hakkinda),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Spacer(
                        modifier = Modifier
                            .height(1.dp)
                            .fillMaxWidth()
                            .background(Color.LightGray)
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                    ) {
                        Text(
                            text = firmObject.firm_info
                        )
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                    ) {
                        Text(
                            text = stringResource(id = R.string.ilanlar),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    firmPageState.firmAdverts.value.forEach { advert ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp)
                                .clickable {
                                    if (Constants.USER_TYPE == Constants.FIRM) {
                                        lastPage = 4
                                        navController.navigate(Screen.FirmMainPage.route)
                                    } else {
                                        navController.navigate(
                                            Screen.AdvertPage.passNavigate(
                                                advert.advert_id
                                            )
                                        )
                                    }
                                },
                            colors = CardDefaults.cardColors(
                                containerColor = Color.White,
                                contentColor = Color.Black
                            ),
                            border = BorderStroke(0.5.dp, GaziKoyuMavi),
                            shape = RoundedCornerShape(20.dp)
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(10.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = advert.advert_title,
                                    modifier = Modifier.weight(0.8f),
                                    overflow = TextOverflow.Ellipsis,
                                    fontWeight = FontWeight.SemiBold
                                )
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                                    contentDescription = "",
                                )
                            }
                        }
                    }
                }
            }
        }


        Image(
            painter = painterResource(id = R.drawable.havelsan),
            contentDescription = "Firm Background Photo ",
            modifier = Modifier
                .fillMaxWidth()
                .height(140.dp)
                .align(Alignment.TopCenter)
                .clip(RoundedCornerShape(10.dp)),
            contentScale = ContentScale.FillWidth
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding()
                .padding(top = 100.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(modifier = Modifier.fillMaxWidth()) {
                Column(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .background(Color.Transparent),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.gazi_university_logo),
                        contentDescription = "",
                        alignment = Alignment.Center,
                        contentScale = ContentScale.Crop, modifier = Modifier.size(80.dp)
                    )
                    Text(
                        text = firmObject.firm_name,
                        fontSize = 25.sp, fontWeight = FontWeight.Bold
                    )

                }
                if (Constants.USER_TYPE == Constants.FIRM) {
                    Row(
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .padding(end = 10.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.End
                    ) {
                        IconButton(onClick = {
                            navController.navigate(Screen.FirmInfoEditPage.passNavigate(firmObject.firm_id))
                        }) {
                            Icon(
                                painter = painterResource(id = R.drawable.pencil_icon),
                                contentDescription = "",
                                modifier = Modifier.size(22.dp)
                            )
                        }
                    }
                }
            }
        }

        OutlinedButton(
            onClick = {
                navController.navigateUp()
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = GaziAcikMavi,
                contentColor = Color.Black
            ),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .padding(8.dp)
                .align(Alignment.TopStart)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.back_icon),
                    contentDescription = "Go back Icon For Firm Page",
                    modifier = Modifier.size(20.dp)
                )
                Text(text = stringResource(id = R.string.geri_don))
            }

        }
    }
}