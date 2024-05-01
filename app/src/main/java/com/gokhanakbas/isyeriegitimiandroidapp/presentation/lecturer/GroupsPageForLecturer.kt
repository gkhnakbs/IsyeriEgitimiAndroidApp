package com.gokhanakbas.isyeriegitimiandroidapp.presentation.lecturer

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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
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
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
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
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Group
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Student
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.StudentTask
import com.gokhanakbas.isyeriegitimiandroidapp.ui.theme.GaziAcikMavi
import com.google.gson.Gson

var studentIdForTask = 1

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GroupsPageForLecturer(paddingValues: PaddingValues, navController: NavController) {
    val studentListOf = emptyList<Student>()

    val groupList = remember {
        arrayListOf(
            Group(1, "Grup 1", studentListOf, "22.02.2024"),
            Group(2, "Grup 2", studentListOf, "23.02.2024"),
            Group(3, "Grup 3", studentListOf, "24.02.2024"),
            Group(4, "Grup 4", studentListOf, "25.02.2024"),
            Group(5, "Grup 5", studentListOf, "26.02.2024"),
            Group(6, "Grup 6", studentListOf, "27.02.2024"),
            Group(7, "Grup 7", studentListOf, "28.02.2024"),
        )
    }
    val alertDialogOfStudentList = remember { mutableStateOf(false) }
    val studentTaskTrackingPageState = remember { mutableStateOf(false) }

    var index = 0

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    )
    {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            items(
                count = groupList.count(),
                key = { groupList[it].id },
            ) {
                index = it
                val groupObject = groupList[it]
                Card(
                    modifier = Modifier
                        .padding(5.dp)
                        .fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 10.dp
                    ), colors = CardDefaults.cardColors(
                        containerColor = Color.White,
                    )
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(15.dp),
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(
                            text = groupObject.group_name,
                            fontSize = 25.sp,
                            modifier = Modifier.height(50.dp)
                        )
                        Text(
                            text = stringResource(id = R.string.olusturulma_tarihi) + ":" + groupObject.group_creationDate,
                            fontSize = 20.sp,
                            color = Color.Black
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.End
                        ) {
                            OutlinedButton(
                                onClick = {
                                    groupList.removeAt(it)
                                },
                                shape = RoundedCornerShape(10.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color.Red,
                                    contentColor = Color.White
                                )
                            ) {
                                Text(text = stringResource(id = R.string.grubu_sil))
                            }
                            Spacer(modifier = Modifier.width(10.dp))
                            OutlinedButton(
                                onClick = {
                                    alertDialogOfStudentList.value = true
                                },
                                shape = RoundedCornerShape(10.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = GaziAcikMavi,
                                    contentColor = Color.White
                                )
                            ) {
                                Text(text = stringResource(id = R.string.grubu_goruntule))
                            }

                        }
                    }
                }
            }
        }
        if (alertDialogOfStudentList.value) {
            AlertDialogOfStudentList(
                group = groupList[index],
                alertDialogOfStudentList = alertDialogOfStudentList,
                studentTaskTrackingPageState = studentTaskTrackingPageState,
                navController = navController
            )
        }
        if (studentTaskTrackingPageState.value) {
            StudentTaskTracking(
                student = studentListOf[1],
                studentTrackingState = studentTaskTrackingPageState
            )
        }

        OutlinedButton(
            onClick = {
                //Grup olusturma

            }, colors = ButtonDefaults.buttonColors(
                containerColor = Color.Green,
                contentColor = Color.White
            ), shape = RoundedCornerShape(10.dp), modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(15.dp)
        ) {
            Text(text = stringResource(id = R.string.grub_olustur), fontSize = 20.sp)
        }
    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlertDialogOfStudentList(
    group: Group,
    alertDialogOfStudentList: MutableState<Boolean>,
    studentTaskTrackingPageState: MutableState<Boolean>,
    navController: NavController
) {
    BasicAlertDialog(
        onDismissRequest = { alertDialogOfStudentList.value = false }, modifier = Modifier.clip(
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
                    .height(50.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(id = R.string.ogrenci_listesi) + " " + group.group_name,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold
                )
                IconButton(onClick = { alertDialogOfStudentList.value = false }) {
                    Icon(
                        painter = painterResource(id = R.drawable.cross_icon),
                        contentDescription = "Close Icon for Group List",
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
                    count = group.studentList.size,
                    key = { group.studentList[it].id }
                ) {
                    val student = group.studentList[it]
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
                        Column {
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
                                        contentDescription = "Student Image in Group List"
                                    )
                                    Spacer(modifier = Modifier.width(10.dp))
                                    Text(text = student.student_name, fontWeight = FontWeight.Bold)
                                }

                            }
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                OutlinedButton(
                                    onClick = {
                                        //Ogrenciyi gosterecek
                                        alertDialogOfStudentList.value = false
                                        val studentJson = Gson().toJson(student)
                                        navController.navigate(
                                            Screen.StudentPage.route
                                        )
                                    }, colors = ButtonDefaults.outlinedButtonColors(
                                        containerColor = Color.Green,
                                        contentColor = Color.White
                                    )
                                ) {
                                    Text(text = stringResource(id = R.string.ogrenciyi_goruntule))
                                }
                                OutlinedButton(
                                    onClick = {
                                        //Ogrenciyi gorevlerini gosterecek onun için burada global değişken olan studentIdForTask değerini değiştirecek
                                        studentTaskTrackingPageState.value = true
                                        studentIdForTask = student.student_no.toInt()
                                    }, colors = ButtonDefaults.outlinedButtonColors(
                                        containerColor = GaziAcikMavi,
                                        contentColor = Color.White
                                    )
                                ) {
                                    Text(text = stringResource(id = R.string.ogrencinin_gorevlerini_goruntule))
                                }

                            }
                        }


                    }
                }

            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentTaskTracking(student: Student, studentTrackingState: MutableState<Boolean>) {
    BasicAlertDialog(
        onDismissRequest = { },
        modifier = Modifier
            .size(500.dp, 600.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(Color.White)
    ) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Row(modifier = Modifier.weight(0.7f)) {
                    Text(
                        text = "${student.student_name} adlı kişinin ${student.student_workPlace} adlı işyerindeki görevleri",
                        fontSize = 20.sp
                    )
                }
                IconButton(
                    onClick = { studentTrackingState.value = false },
                    modifier = Modifier.weight(0.3f)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.cross_icon),
                        contentDescription = "Cross Icon for Task Tracking Page"
                    )
                }
            }
            //Burada öğrencinin görevleri id sayesinde çekilecek ve gösterilecek .
            val studentTask = arrayListOf(
                StudentTask(
                    1,
                    "Görev 1Görev 1Görev 1Görev 1Görev 1Görev 1Görev 1Görev 1",
                    "Tamamlandı",
                    "9.03.2024",
                    "27.03.2024"
                ),
                StudentTask(2, "Görev 2", "Devam Ediyor", "9.03.2024", "27.03.2024"),
                StudentTask(3, "Görev 3", "Tamamlandı", "9.03.2024", "27.03.2024"),
                StudentTask(4, "Görev 4", "Devam Ediyor", "9.03.2024", "27.03.2024"),
                StudentTask(5, "Görev 5", "Tamamlandı", "9.03.2024", "27.03.2024"),
                StudentTask(6, "Görev 6", "Tamamlanamadı", "9.03.2024", "27.03.2024"),
                StudentTask(7, "Görev 7", "Tamamlandı", "9.03.2024", "27.03.2024"),
                StudentTask(8, "Görev 8", "Devam Ediyor", "9.03.2024", "27.03.2024"),
                StudentTask(9, "Görev 9", "Tamamlandı", "9.03.2024", "27.03.2024"),
                StudentTask(10, "Görev 10", "Tamamlanamadı", "9.03.2024", "27.03.2024")
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                studentTask.forEachIndexed { index, studentTask ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 5.dp, bottom = 5.dp),
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 10.dp
                        ),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White
                        ),
                        border = BorderStroke(2.dp, Color.Black), shape = RoundedCornerShape(10.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(7.dp)
                                .fillMaxSize()
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Start
                            ) {
                                Text(
                                    text = studentTask.taskLabel,
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            Spacer(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(0.5.dp)
                                    .background(Color.Gray)
                            )
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = studentTask.task_startDate + "-" + studentTask.task_endDate,
                                    color = Color.LightGray,
                                    fontSize = 15.sp
                                )
                                Text(
                                    text = studentTask.taskState, color =
                                    when (studentTask.taskState) {
                                        "Tamamlandı" -> Color.Green
                                        "Devam Ediyor" -> Color.LightGray
                                        else -> Color.Red
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

        
        
        
        
        