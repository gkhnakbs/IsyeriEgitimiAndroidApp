package com.gokhanakbas.isyeriegitimiandroidapp.presentation.pages.lecturersPage

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.gokhanakbas.isyeriegitimiandroidapp.R
import com.gokhanakbas.isyeriegitimiandroidapp.presentation.navigation.Screen
import com.gokhanakbas.isyeriegitimiandroidapp.presentation.util.components.LoadingDialog
import com.gokhanakbas.isyeriegitimiandroidapp.ui.theme.GaziAcikMavi
import com.gokhanakbas.isyeriegitimiandroidapp.ui.theme.GaziKoyuMavi

@Composable
fun LecturerPage(navController: NavController,lecturer_id : String,viewModel: LecturerPageViewModel= hiltViewModel()) {

    LaunchedEffect(key1 = viewModel) {
        viewModel.getLecturerInformation(lecturer_id)
    }

    val state by viewModel.state.collectAsStateWithLifecycle()

    LecturerPageContent(navController = navController, state = state)

}

@Composable
fun LecturerPageContent(navController: NavController,state: LecturerPageState) {


    val lecturerObject=state.lecturer

    LoadingDialog(isLoading = state.isLoading)

    var tf_lecturerID = lecturerObject.lecturer_id
    val tf_lecturerName = lecturerObject.lecturer_name
    val tf_lecturerFaculty = lecturerObject.lecturer_faculty
    val tf_lecturerDepartment = lecturerObject.lecturer_department
    val tf_lecturerDegree = lecturerObject.lecturer_degree
    val tf_lecturerSpecificField =lecturerObject.lecturer_specificField

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                GaziKoyuMavi
            ), verticalArrangement = Arrangement.Top, horizontalAlignment = Alignment.Start
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.gazi_rektorluk),
                contentDescription = "Lecturer Page Background Gazi Photo",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp),
                contentScale = ContentScale.FillWidth,
                alignment = Alignment.TopCenter
            )
            OutlinedButton(
                onClick = {
                    navController.popBackStack()
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
            Box(
                modifier = Modifier
                    .align(Alignment.BottomStart),
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier.height(80.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .clip(
                                RoundedCornerShape(50.dp)
                            )
                            .border(BorderStroke(5.dp, Color.White))
                            .size(80.dp)
                            .background(Color.Transparent),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.aydin_resim),
                            contentDescription = "",
                            contentScale = ContentScale.Fit,
                            alignment = Alignment.Center,
                            modifier = Modifier
                                .size(70.dp)
                                .background(Color.White)
                        )
                    }
                    Row(
                        modifier = Modifier.fillMaxSize(),
                        verticalAlignment = Alignment.Bottom,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = tf_lecturerDegree+tf_lecturerName,
                            color = Color.White,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.weight(0.8f,fill = true),
                        )
                        IconButton(onClick = {
                            //Profil duzenleme sayfasina gidecek
                            navController.navigate(Screen.LecturerInfoEditPage.passNavigate(lecturer_id = lecturerObject.lecturer_id))
                        }) {
                            Icon(
                                painter = painterResource(id = R.drawable.pencil_icon),
                                contentDescription = "",
                                Modifier.size(24.dp),
                                tint = Color.White
                            )
                        }
                    }
                }
            }
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(20.dp))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(GaziAcikMavi)
                    .verticalScroll(rememberScrollState())
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Column {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            OutlinedTextField(
                                value = tf_lecturerID,
                                onValueChange = {  },
                                //singleLine = true,
                                readOnly = true,
                                label = { Text(text = stringResource(id = R.string.izleyici_no)) },
                                textStyle = TextStyle.Default.copy(fontSize = 18.sp),
                                modifier = Modifier
                                    .weight(0.5f)
                                    .padding(10.dp)
                            )
                            OutlinedTextField(
                                value = tf_lecturerFaculty,
                                onValueChange = { },
                                //singleLine = true,
                                readOnly = true,
                                label = { Text(text = stringResource(id = R.string.fakulte)) },
                                textStyle = TextStyle.Default.copy(fontSize = 18.sp),
                                modifier = Modifier
                                    .weight(0.5f)
                                    .padding(10.dp)
                            )
                        }
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            OutlinedTextField(
                                value = tf_lecturerDepartment,
                                onValueChange = { },
                                //singleLine = true,
                                readOnly = true,
                                label = { Text(text = stringResource(id = R.string.bolum)) },
                                textStyle = TextStyle.Default.copy(fontSize = 18.sp),
                                modifier = Modifier
                                    .weight(0.5f)
                                    .padding(10.dp)
                            )
                            OutlinedTextField(
                                value = tf_lecturerSpecificField,
                                onValueChange = {  },
                                //singleLine = true,
                                readOnly = true,
                                label = { Text(text = stringResource(id = R.string.bolum)) },
                                textStyle = TextStyle.Default.copy(fontSize = 18.sp),
                                modifier = Modifier
                                    .weight(0.5f)
                                    .padding(10.dp)
                            )
                        }
                    }


                }
                Text(
                    text = stringResource(id = R.string.bilgilerim),
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(10.dp),
                    textDecoration = TextDecoration.Underline
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                ) {
                    Text(
                        text = lecturerObject.lecturer_info,
                        fontSize = 18.sp
                    )
                }
            }
        }

    }

}
