package com.gokhanakbas.isyeriegitimiandroidapp.presentation.student.studentInfoEditPage

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.gokhanakbas.isyeriegitimiandroidapp.R
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Skill
import com.gokhanakbas.isyeriegitimiandroidapp.presentation.util.components.LoadingDialog
import com.gokhanakbas.isyeriegitimiandroidapp.presentation.util.components.SkillEntryComp
import com.gokhanakbas.isyeriegitimiandroidapp.ui.theme.GaziAcikMavi
import com.gokhanakbas.isyeriegitimiandroidapp.ui.theme.GaziKoyuMavi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun StudentInfoEditPage(
    navController: NavController,
    student_no: String,
    viewModel: StudentInfoEditPageViewModel = hiltViewModel()
) {

    LaunchedEffect(key1 = viewModel) {
        viewModel.getStudentInformation(student_no)
        viewModel.getSkills(student_no)
    }

    val state by viewModel.state.collectAsStateWithLifecycle()

    StudentInfoEditPageContent(
        navController = navController,
        studentInfoEditPageState = state,
        viewModel = viewModel
    )

}

@Composable
fun StudentInfoEditPageContent(
    navController: NavController,
    studentInfoEditPageState: StudentInfoEditPageState,
    viewModel: StudentInfoEditPageViewModel
) {

    LoadingDialog(isLoading = studentInfoEditPageState.isLoading)

    val student = studentInfoEditPageState.student

    val tf_studentInfo = remember { mutableStateOf("") }
    val tf_studentEmail = remember { mutableStateOf("") }
    val tf_studentSkillList = remember { mutableStateListOf<Skill>() }

    LaunchedEffect(key1 = studentInfoEditPageState.skillList) {
        tf_studentInfo.value = student.student_info
        tf_studentEmail.value = student.student_mail
        tf_studentSkillList.addAll(studentInfoEditPageState.skillList)
    }

    val skillEntryState = remember {
        mutableStateOf(false)
    }

    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(GaziAcikMavi)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(GaziAcikMavi)
                .verticalScroll(scrollState)
                .padding(top = 40.dp, start = 10.dp, bottom = 10.dp, end = 10.dp)
                .clip(RoundedCornerShape(20.dp))
        ) {
            OutlinedTextField(
                value = student.student_no,
                onValueChange = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 15.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedContainerColor = Color.White,
                    focusedContainerColor = Color.White,
                    disabledContainerColor = Color.White,
                    unfocusedBorderColor = GaziKoyuMavi,
                    focusedBorderColor = GaziKoyuMavi,
                ), readOnly = true,
                label = { Text(text = stringResource(id = R.string.ogrenci_no)) })

            OutlinedTextField(
                value = student.student_name,
                onValueChange = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 15.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedContainerColor = Color.White,
                    focusedContainerColor = Color.White,
                    disabledContainerColor = Color.White,
                    unfocusedBorderColor = GaziKoyuMavi,
                    focusedBorderColor = GaziKoyuMavi,
                ), readOnly = true,
                label = { Text(text = stringResource(id = R.string.tam_ad)) })
            OutlinedTextField(
                value = student.student_age,
                onValueChange = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 15.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedContainerColor = Color.White,
                    focusedContainerColor = Color.White,
                    disabledContainerColor = Color.White,
                    unfocusedBorderColor = GaziKoyuMavi,
                    focusedBorderColor = GaziKoyuMavi,
                ), readOnly = true, label = { Text(text = stringResource(id = R.string.yas)) }
            )
            OutlinedTextField(
                value = student.student_faculty,
                onValueChange = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 15.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedContainerColor = Color.White,
                    focusedContainerColor = Color.White,
                    disabledContainerColor = Color.White,
                    unfocusedBorderColor = GaziKoyuMavi,
                    focusedBorderColor = GaziKoyuMavi,
                ), readOnly = true, label = { Text(text = stringResource(id = R.string.fakulte)) }
            )
            OutlinedTextField(
                value = student.student_department,
                onValueChange = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 15.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedContainerColor = Color.White,
                    focusedContainerColor = Color.White,
                    disabledContainerColor = Color.White,
                    unfocusedBorderColor = GaziKoyuMavi,
                    focusedBorderColor = GaziKoyuMavi,
                ), readOnly = true, label = { Text(text = stringResource(id = R.string.bolum)) }
            )
            OutlinedTextField(
                value = student.student_AGNO,
                onValueChange = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 15.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedContainerColor = Color.White,
                    focusedContainerColor = Color.White,
                    disabledContainerColor = Color.White,
                    unfocusedBorderColor = GaziKoyuMavi,
                    focusedBorderColor = GaziKoyuMavi,
                ),
                readOnly = true,
                label = { Text(text = stringResource(id = R.string.ogrenci_ortalama)) }
            )
            OutlinedTextField(
                value = student.student_workPlace.firm_name,
                onValueChange = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 15.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedContainerColor = Color.White,
                    focusedContainerColor = Color.White,
                    disabledContainerColor = Color.White,
                    unfocusedBorderColor = GaziKoyuMavi,
                    focusedBorderColor = GaziKoyuMavi,
                ),
                readOnly = true,
                label = { Text(text = stringResource(id = R.string.isyeri_firmasi)) }
            )
            OutlinedTextField(
                value = tf_studentInfo.value,
                onValueChange = { tf_studentInfo.value = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 15.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedContainerColor = Color.White,
                    focusedContainerColor = Color.White,
                    disabledContainerColor = Color.White,
                    unfocusedBorderColor = GaziKoyuMavi,
                    focusedBorderColor = GaziKoyuMavi,
                ), minLines = 5,
                label = { Text(text = stringResource(id = R.string.hakkimda)) })

            OutlinedTextField(
                value = tf_studentEmail.value,
                onValueChange = { tf_studentEmail.value = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 15.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedContainerColor = Color.White,
                    focusedContainerColor = Color.White,
                    disabledContainerColor = Color.White,
                    unfocusedBorderColor = GaziKoyuMavi,
                    focusedBorderColor = GaziKoyuMavi,
                ), keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                label = { Text(text = stringResource(id = R.string.email)) }
            )
            Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.Start) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp, end = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(id = R.string.yeteneklerim),
                        fontWeight = FontWeight.Black,
                        textDecoration = TextDecoration.Underline,
                        fontSize = 22.sp
                    )
                    IconButton(onClick = {
                        //AlertDialog açılacak, yetenek adını ve seviyesini girip ekleyecek.
                        skillEntryState.value = true
                    }) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "",
                            tint = GaziKoyuMavi
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(5.dp))
            repeat(tf_studentSkillList.size) { index ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    ),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 10.dp
                    )
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 10.dp, end = 10.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            modifier = Modifier.weight(0.8f, fill = true),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = tf_studentSkillList[index].skill_name, fontSize = 20.sp)
                            Text(text = when(tf_studentSkillList[index].skill_level){
                                "İleri" -> stringResource(id = R.string.ileri_seviye)
                                "Orta" -> stringResource(id = R.string.orta_seviye)
                                else -> {
                                    stringResource(id = R.string.baslangic_seviye)
                                }
                            }
                            , fontSize = 20.sp)
                        }
                        IconButton(onClick = {
                            tf_studentSkillList.apply {
                                removeAt(index)
                            }
                        }) {
                            Icon(
                                imageVector = Icons.Rounded.Delete,
                                contentDescription = "",
                                tint = Color.Red
                            )
                        }
                    }

                }
            }

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                OutlinedButton(
                    onClick = {
                        // Ogrenci Bilgilerini kaydetme islemleri gerceklesecek
                        CoroutineScope(Dispatchers.Main).launch {

                            if (tf_studentInfo.value.trim() != student.student_info || tf_studentEmail.value.trim() != student.student_mail) {

                                student.student_mail = tf_studentEmail.value.trim()
                                student.student_info = tf_studentInfo.value.trim()

                                if (tf_studentSkillList != studentInfoEditPageState.skillList) {
                                    val job1 =
                                        async { viewModel.saveStudentInformation(student = student) }
                                    val job2 =
                                        async {
                                            viewModel.saveStudentSkills(
                                                student_no = student.student_no,
                                                skillList = tf_studentSkillList
                                            )
                                        }
                                    if (job1.await() && job2.await()) {
                                        withContext(Dispatchers.Main) {
                                            if (viewModel.state.value.savedSuccessfully && viewModel.state.value.savedSkillsSuccessfully) {
                                                navController.popBackStack()
                                            }
                                        }
                                    }
                                } else {
                                    val job1 =
                                        async { viewModel.saveStudentInformation(student = student) }
                                    if (job1.await()) {
                                        withContext(Dispatchers.Main) {
                                            if (viewModel.state.value.savedSuccessfully) {
                                                navController.popBackStack()
                                            }
                                        }
                                    }
                                }
                            } else if (tf_studentInfo.value.trim() == student.student_info && tf_studentEmail.value.trim() == student.student_mail
                                && tf_studentSkillList != student.student_skillList
                            ) {
                                val job2 =
                                    async {
                                        viewModel.saveStudentSkills(
                                            student_no = student.student_no,
                                            skillList = tf_studentSkillList
                                        )
                                    }
                                if (job2.await()) {
                                    withContext(Dispatchers.Main) {
                                        if (viewModel.state.value.savedSkillsSuccessfully) {
                                            navController.popBackStack()
                                        }
                                    }
                                }
                            }
                        }
                    },
                    modifier = Modifier
                        .padding(10.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = GaziKoyuMavi,
                        contentColor = Color.White
                    ),
                    border = BorderStroke(2.dp, GaziKoyuMavi),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text(text = stringResource(id = R.string.kaydet))
                }
            }

        }

        OutlinedButton(
            onClick = {
                navController.popBackStack()
            },
            modifier = Modifier
                .padding(10.dp),
            colors = ButtonDefaults.outlinedButtonColors(
                containerColor = GaziKoyuMavi,
                contentColor = Color.White
            ),
            border = BorderStroke(2.dp, GaziKoyuMavi),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(text = stringResource(id = R.string.geri_don))
        }


        val newSkill = remember {
            mutableStateOf(Skill("", "", "", ""))
        }

        if (skillEntryState.value) {
            SkillEntryComp(skillEntryState = skillEntryState, skill = newSkill.value)
        }

        LaunchedEffect(key1 = newSkill.value.skill_name) {
            if (newSkill.value.skill_name != "") {
                tf_studentSkillList.add(newSkill.value)
                newSkill.value = Skill("","","","")
            }

        }

    }
}