package com.gokhanakbas.isyeriegitimiandroidapp.presentation.entry

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.gokhanakbas.isyeriegitimiandroidapp.R


@Composable
fun EntryPage(navController: NavController) {

    val activity = (LocalContext.current as Activity)
    BackHandler(onBack = {
        activity.finish()
    })
    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.gazi_university_logo),
            contentDescription = "Gazi University Logo",
            modifier = Modifier.size(200.dp)
        )
        LazyColumn {
            item {
                Card(
                    modifier = Modifier
                        .size(300.dp, 60.dp)
                        .padding(all = 5.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
                    colors = CardDefaults.cardColors(
                        disabledContainerColor = Color.LightGray
                    )
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .clickable {
                                //Ogrenci modulune gidilecek
                                navController.navigate("studentEntryPage")
                            },
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = stringResource(id = R.string.ogrenci_giris_sayfasi),
                                fontSize = 15.sp,
                                color = Color.Black
                            )
                        }
                    }
                }
            }
            item {
                Card(
                    modifier = Modifier
                        .size(300.dp, 60.dp)
                        .padding(all = 5.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
                    colors = CardDefaults.cardColors(
                        disabledContainerColor = Color.LightGray
                    )
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .clickable {
                                //Firma modulune gidilecek
                                navController.navigate("firmEntryPage")
                            },
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = stringResource(id = R.string.firma_giris_sayfasi),
                                fontSize = 15.sp,
                                color = Color.Black
                            )
                        }
                    }
                }
            }
            item {
                Card(
                    modifier = Modifier
                        .size(300.dp, 60.dp)
                        .padding(all = 5.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
                    colors = CardDefaults.cardColors(
                        disabledContainerColor = Color.LightGray
                    )
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .clickable {
                                //Izleyici modulune gidilecek
                                navController.navigate("lecturerEntryPage")
                            },
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = stringResource(id = R.string.izleyici_giris_sayfasi),
                                fontSize = 15.sp,
                                color = Color.Black
                            )
                        }
                    }
                }
            }
            item {
                Card(
                    modifier = Modifier
                        .size(300.dp, 60.dp)
                        .padding(all = 5.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
                    colors = CardDefaults.cardColors(
                        disabledContainerColor = Color.LightGray
                    )
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .clickable {
                                //Komisyon modulune gidilecek
                                navController.navigate("commissionEntryPage")
                            },
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = stringResource(id = R.string.komisyon_giris_sayfasi),
                                fontSize = 15.sp,
                                color = Color.Black
                            )
                        }
                    }
                }
            }
        }
    }
}