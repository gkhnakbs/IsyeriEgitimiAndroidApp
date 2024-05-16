package com.gokhanakbas.isyeriegitimiandroidapp.presentation.pages.studentsPage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.gokhanakbas.isyeriegitimiandroidapp.R
import com.gokhanakbas.isyeriegitimiandroidapp.presentation.navigation.Screen
import com.gokhanakbas.isyeriegitimiandroidapp.presentation.util.components.LoadingDialog
import com.gokhanakbas.isyeriegitimiandroidapp.presentation.util.components.SkillComp
import com.gokhanakbas.isyeriegitimiandroidapp.ui.theme.GaziAcikMavi
import com.gokhanakbas.isyeriegitimiandroidapp.ui.theme.GaziKoyuMavi
import com.gokhanakbas.isyeriegitimiandroidapp.util.Constants

@Composable
fun StudentPage(
    navController: NavController,
    student_no: String,
    studentPageViewModel: StudentPageViewModel = hiltViewModel()
) {

    LaunchedEffect(key1 = studentPageViewModel) {
        studentPageViewModel.getStudentInformation(student_no)
        studentPageViewModel.getSkills(student_no)
        studentPageViewModel.getGroupsLecturerInformation(student_no)
    }

    val state by studentPageViewModel.state.collectAsStateWithLifecycle()

    StudentPageContent(navController = navController, studentPageState = state)

}


@Composable
fun StudentPageContent(navController: NavController, studentPageState: StudentPageState) {

    LoadingDialog(isLoading = studentPageState.isLoading)

    val studentObject = studentPageState.student

    val tf_studentName = studentObject.student_name
    val tf_studentAge = studentObject.student_age
    val tf_studentFaculty = studentObject.student_faculty
    val tf_studentDepartment = studentObject.student_department
    val tf_studentAGNO = studentObject.student_AGNO
    val tf_studentWorkPlace = studentObject.student_workPlace.firm_name
    val tf_studentNo = studentObject.student_no
    val tf_studentMail = studentObject.student_mail

    val scrollState = rememberScrollState()
    val scrollState1 = rememberScrollState()
    Box(
        modifier = Modifier.fillMaxSize()
    )
    {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(GaziKoyuMavi)
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.gazi_rektorluk),
                    contentDescription = "Student Page Background Gazi Photo",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(160.dp),
                    contentScale = ContentScale.FillWidth,
                    alignment = Alignment.TopCenter
                )
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomStart),
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                        modifier = Modifier
                            .height(80.dp)
                            .padding(all = 5.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.student_thin_icon),
                            contentDescription = "",
                            contentScale = ContentScale.Fit,
                            alignment = Alignment.Center,
                            modifier = Modifier
                                .size(70.dp)
                                .clip(RoundedCornerShape(50.dp))
                                .background(Color.White)
                        )
                        Row(
                            modifier = Modifier
                                .fillMaxSize(),
                            verticalAlignment = Alignment.Bottom,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = tf_studentName,
                                color = Color.White,
                                fontSize = 22.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.weight(0.8f, fill = true)
                            )
                            if (Constants.USER_TYPE == Constants.STUDENT) {
                                IconButton(onClick = {
                                    //Profil duzenleme sayfasina gidecek
                                    navController.navigate(
                                        Screen.StudentInfoEditPage.passNavigate(
                                            studentObject.student_no
                                        )
                                    )
                                }) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.pencil_icon),
                                        contentDescription = "Student Info Edit Icon",
                                        modifier = Modifier.size(20.dp),
                                        tint = Color.White,
                                    )
                                }
                            }

                        }
                    }
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp)
                    .clip(RoundedCornerShape(20.dp))
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(GaziAcikMavi)
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
                                    value = tf_studentNo,
                                    onValueChange = { },
                                    readOnly = true,
                                    label = { Text(text = stringResource(id = R.string.ogrenci_no)) },
                                    textStyle = TextStyle.Default.copy(fontSize = 18.sp),
                                    modifier = Modifier
                                        .weight(0.5f)
                                        .padding(10.dp)
                                )
                                OutlinedTextField(
                                    value = tf_studentAge,
                                    onValueChange = { },
                                    readOnly = true,
                                    label = { Text(text = stringResource(id = R.string.yas)) },
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
                                    value = tf_studentFaculty,
                                    onValueChange = { },
                                    readOnly = true,
                                    label = { Text(text = stringResource(id = R.string.fakulte)) },
                                    textStyle = TextStyle.Default.copy(fontSize = 18.sp),
                                    modifier = Modifier
                                        .weight(0.5f)
                                        .padding(10.dp)
                                )
                                OutlinedTextField(
                                    value = tf_studentDepartment,
                                    onValueChange = {},
                                    readOnly = true,
                                    label = { Text(text = stringResource(id = R.string.bolum)) },
                                    textStyle = TextStyle.Default.copy(fontSize = 18.sp),
                                    modifier = Modifier
                                        .weight(0.5f)
                                        .padding(10.dp)
                                )
                            }
                            Row {
                                OutlinedTextField(
                                    value = tf_studentAGNO,
                                    onValueChange = { },
                                    readOnly = true,
                                    label = { Text(text = stringResource(id = R.string.agno)) },
                                    textStyle = TextStyle.Default.copy(fontSize = 18.sp),
                                    modifier = Modifier
                                        .weight(0.5f)
                                        .padding(10.dp)
                                )
                                OutlinedTextField(
                                    value = tf_studentWorkPlace,
                                    onValueChange = { },
                                    readOnly = true,
                                    label = { Text(text = stringResource(id = R.string.is_yeri)) },
                                    textStyle = TextStyle.Default.copy(fontSize = 18.sp),
                                    modifier = Modifier
                                        .weight(0.5f)
                                        .padding(10.dp)
                                )
                            }
                            Row {
                                OutlinedTextField(
                                    value = tf_studentMail,
                                    onValueChange = { },
                                    readOnly = true,
                                    label = { Text(text = stringResource(id = R.string.email)) },
                                    textStyle = TextStyle.Default.copy(fontSize = 18.sp),
                                    modifier = Modifier
                                        .weight(0.5f)
                                        .fillMaxWidth()
                                        .padding(10.dp)
                                )
                            }
                        }


                    }
                    Text(
                        text = stringResource(id = R.string.hakkimda),
                        fontSize = 23.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(10.dp),
                        textDecoration = TextDecoration.Underline
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 5.dp, start = 10.dp, end = 10.dp, bottom = 10.dp)
                    ) {
                        Text(text = studentObject.student_info, fontSize = 18.sp)
                    }
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .background(GaziAcikMavi),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Top
                ) {
                    Text(
                        text = stringResource(id = R.string.yeteneklerim),
                        fontSize = 23.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(10.dp),
                        textDecoration = TextDecoration.Underline
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    repeat(studentPageState.skillList.size) { index ->
                        val skill = studentPageState.skillList[index]
                        SkillComp(skill = skill)
                    }
                }
            }
            Box(modifier = Modifier.fillMaxWidth()) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .background(GaziAcikMavi),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Top
                ) {
                    Text(
                        text = stringResource(id = R.string.grup_bilgileri),
                        fontSize = 23.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(10.dp),
                        textDecoration = TextDecoration.Underline
                    )
                    Spacer(modifier = Modifier.height(5.dp))

                    Card(modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp), shape = RoundedCornerShape(20.dp) , colors = CardDefaults.cardColors(
                            containerColor = Color.White
                        )) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(5.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                modifier = Modifier.weight(0.8f),
                                text = studentPageState.groupsLecturer.lecturer_name,
                                textDecoration = TextDecoration.Underline,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 20.sp
                            )
                            IconButton(onClick = {
                                navController.navigate(
                                    Screen.LecturerPage.passNavigate(
                                        studentPageState.groupsLecturer.lecturer_id
                                    )
                                )
                            }, modifier = Modifier.weight(0.2f)) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                                    contentDescription = ""
                                )
                            }
                        }
                    }
                }
            }

        }
        OutlinedButton(
            onClick = {
                navController.popBackStack()
            }, colors = ButtonDefaults.buttonColors(
                containerColor = GaziAcikMavi,
                contentColor = Color.Black
            ), shape = RoundedCornerShape(10.dp), modifier = Modifier
                .padding(8.dp)
                .align(Alignment.TopStart)
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

    }
}
