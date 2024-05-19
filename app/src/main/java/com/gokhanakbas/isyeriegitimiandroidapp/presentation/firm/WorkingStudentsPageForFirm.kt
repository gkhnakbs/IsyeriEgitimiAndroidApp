package com.gokhanakbas.isyeriegitimiandroidapp.presentation.firm

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.gokhanakbas.isyeriegitimiandroidapp.R
import com.gokhanakbas.isyeriegitimiandroidapp.presentation.navigation.Screen
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Student
import com.gokhanakbas.isyeriegitimiandroidapp.ui.theme.GaziKoyuMavi
import com.google.gson.Gson

@Composable
fun WorkingStudentsPageForFirm(paddingValues: PaddingValues, navController: NavController) {
    val workingStudentName = remember {
        mutableStateOf("")
    }
    val taskGivingPageState = remember {
        mutableStateOf(false)
    }
    val focus = LocalFocusManager.current
    val studentListOf = emptyList<Student>()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            OutlinedTextField(
                value = workingStudentName.value,
                onValueChange = {
                    workingStudentName.value = it
                    //Bu bolumde viewModel den fonksiyon calisitirilacak o da repository den calisitirip veritabanindan veri cekip liste halinde verecek
                    //o listede tekrar LazyColumn da gosterilecek.
                },
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.search_icon),
                        contentDescription = "Search Icon Working Students For Firm",
                        Modifier.size(18.dp)
                    )
                },
                trailingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.cross_icon),
                        contentDescription = "Clear icon Working Students For Firm Search",
                        modifier = Modifier
                            .size(18.dp)
                            .clickable {
                                workingStudentName.value = ""
                                focus.clearFocus()
                            })
                },
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.calisan_ogrenciler),
                        color = Color.Gray
                    )
                },
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.White,
                    focusedContainerColor = Color.White
                ),
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                textStyle = TextStyle(fontSize = 18.sp),
                shape = RoundedCornerShape(8.dp)
            )
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            items(count = studentListOf.size, key = { studentListOf[it].id }) {
                val student = studentListOf[it]
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 5.dp, bottom = 5.dp),
                    shape = RoundedCornerShape(10.dp), elevation = CardDefaults.cardElevation(
                        defaultElevation = 5.dp
                    )
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(5.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.student_thin_icon),
                                contentDescription = "Student Icon For Working Students"
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(text = student.student_name, fontSize = 22.sp)
                        }
                        Spacer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(0.5f.dp)
                                .background(Color.Gray)
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start
                        ) {
                            Text(
                                text = stringResource(id = R.string.gorev_sayisi) + ": 5 " + stringResource(
                                    id = R.string.tamamlanan_gorev_sayisi
                                ) + " 3 ", color = Color.Black
                            )
                        }
                        Spacer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(0.5f.dp)
                                .background(Color.Gray)
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start
                        ) {
                            OutlinedButton(
                                onClick = {
                                    //Gorev verme alertDialog u açılacak
                                    taskGivingPageState.value = true
                                },
                                border = BorderStroke(1.dp, Color.Green),
                                colors = ButtonDefaults.outlinedButtonColors(
                                    contentColor = Color.Black
                                )
                            ) {
                                Text(text = stringResource(id = R.string.gorev_ver))
                            }
                            OutlinedButton(
                                onClick = {
                                    //öğrencinin bilgilerini görüntüleyecek
                                    navController.navigate(Screen.StudentPage.passNavigate(student_no = student.student_no))

                                },
                                border = BorderStroke(1.dp, Color.Black),
                                colors = ButtonDefaults.outlinedButtonColors(
                                    contentColor = Color.Black
                                )
                            ) {
                                Text(text = stringResource(id = R.string.ogrenciyi_goruntule))
                            }
                        }
                    }
                }
            }
        }
        if (taskGivingPageState.value) {
            GivingTaskPage(taskGivingPageState)
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GivingTaskPage(taskGivingPageState: MutableState<Boolean>) {
    val taskLabel = remember {
        mutableStateOf("")
    }
    val taskStartDate = remember {
        mutableStateOf("")
    }
    taskStartDate.value = stringResource(id = R.string.gun_ay_yil)
    val taskEndDate = remember {
        mutableStateOf("")
    }
    taskEndDate.value = stringResource(id = R.string.gun_ay_yil)

    BasicAlertDialog(
        onDismissRequest = { taskGivingPageState.value = false },
        modifier = Modifier
            .size(500.dp, 400.dp)
            .clip(RoundedCornerShape(20.dp))
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                OutlinedTextField(
                    value = taskLabel.value,
                    onValueChange = { taskLabel.value = it },
                    label = { Text(text = stringResource(id = R.string.gorev_basligi)) },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = GaziKoyuMavi,
                        unfocusedBorderColor = GaziKoyuMavi
                    ), minLines = 5, maxLines = 5, modifier = Modifier.fillMaxWidth(),
                    supportingText = { Text(text = stringResource(id = R.string.zorunlu_yazisi_tf)) }
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.date_icon),
                            contentDescription = "Date Icon For Giving Task To Student From Firm",
                            modifier = Modifier
                                .clickable {
                                    //calendarStartState.show()
                                }
                                .size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(
                            text = taskStartDate.value,
                            textDecoration = TextDecoration.Underline,
                            color = Color.LightGray,
                            modifier = Modifier
                                .clickable {
                                //    calendarStartState.displayMode
                                }

                        )
                    }
                    Text(text = "-", fontSize = 25.sp)
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.date_icon),
                            contentDescription = "Date Icon For Giving Task To Student From Firm",
                            modifier = Modifier
                                .clickable { //calendarEndState.show()
                                }
                                .size(16.dp),
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(
                            text = taskEndDate.value,
                            textDecoration = TextDecoration.Underline,
                            color = Color.LightGray,
                            modifier = Modifier.clickable {
                                //calendarEndState.show()
                            })
                    }
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedButton(
                        onClick = {
                            // kaydetme işlemi gerçekleşecek

                            if (taskEndDate.value.isNotEmpty() && taskStartDate.value.isNotEmpty() && taskLabel.value.trim()
                                    .isNotEmpty()
                            ) {
                                taskGivingPageState.value = false
                            }

                        }, border = BorderStroke(2.dp, GaziKoyuMavi),
                        colors = ButtonDefaults.outlinedButtonColors(
                            contentColor = Color.Black,
                            containerColor = Color.White
                        )
                    ) {
                        Text(text = stringResource(id = R.string.gorev_ver))
                    }
                }
            }
        }
    }
}
