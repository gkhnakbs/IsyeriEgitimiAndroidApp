package com.gokhanakbas.isyeriegitimiandroidapp.presentation.pages.formsPage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.gokhanakbas.isyeriegitimiandroidapp.R
import com.gokhanakbas.isyeriegitimiandroidapp.ui.theme.GaziAcikMavi

@Composable
fun FormPage(navController: NavController) {

        Column(modifier = Modifier.fillMaxSize().background(GaziAcikMavi)){
            OutlinedButton(
                onClick = {
                    navController.navigateUp()
                }, colors = ButtonDefaults.buttonColors(
                    containerColor = GaziAcikMavi,
                    contentColor = Color.Black
                ), shape = RoundedCornerShape(10.dp), modifier = Modifier.padding(8.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.back_button),
                        contentDescription = "",
                        modifier = Modifier.size(20.dp)
                    )
                    Text(text = stringResource(id = R.string.geri_don))
                }

            }
            LazyColumn(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                item {

                    Card(
                        modifier = Modifier
                            .padding(5.dp)
                            .fillMaxWidth(), elevation = CardDefaults.cardElevation(
                            defaultElevation = 5.dp
                        ), shape = RoundedCornerShape(10.dp)
                    ) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Top,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 5.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Text(text = "Soru 1", fontSize = 20.sp)
                            }
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 5.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            )  {
                                Text(text = "Cevap 1", fontSize = 20.sp)
                            }
                        }

                    }
                    Card(
                        modifier = Modifier
                            .padding(5.dp)
                            .fillMaxWidth(), elevation = CardDefaults.cardElevation(
                            defaultElevation = 5.dp
                        ), shape = RoundedCornerShape(10.dp)
                    ) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Top,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 5.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Text(text = "Soru 2", fontSize = 20.sp)
                            }
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 5.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            )  {
                                Text(text = "Cevap 2", fontSize = 20.sp)
                            }
                        }

                    }
                    Card(
                        modifier = Modifier
                            .padding(5.dp)
                            .fillMaxWidth(), elevation = CardDefaults.cardElevation(
                            defaultElevation = 5.dp
                        ), shape = RoundedCornerShape(10.dp)
                    ) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Top,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 5.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Text(text = "Soru 3", fontSize = 20.sp)
                            }
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 5.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            )  {
                                Text(text = "Cevap 3", fontSize = 20.sp)
                            }
                        }

                    }
                    Card(
                        modifier = Modifier
                            .padding(5.dp)
                            .fillMaxWidth(), elevation = CardDefaults.cardElevation(
                            defaultElevation = 5.dp
                        ), shape = RoundedCornerShape(10.dp)
                    ) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Top,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 5.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Text(text = "Soru 4", fontSize = 20.sp)
                            }
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 5.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            )  {
                                Text(text = "Cevap 4", fontSize = 20.sp)
                            }
                        }

                    }
                }

            }
        }


}
