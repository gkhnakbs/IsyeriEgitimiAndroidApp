package com.gokhanakbas.isyeriegitimiandroidapp.presentation.student.studentInfoEditPage

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
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
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.gokhanakbas.isyeriegitimiandroidapp.R
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Skill
import com.gokhanakbas.isyeriegitimiandroidapp.presentation.navigation.SharedViewModel
import com.gokhanakbas.isyeriegitimiandroidapp.presentation.util.components.LoadingDialog
import com.gokhanakbas.isyeriegitimiandroidapp.presentation.util.components.SkillEntryComp
import com.gokhanakbas.isyeriegitimiandroidapp.ui.theme.GaziAcikMavi
import com.gokhanakbas.isyeriegitimiandroidapp.ui.theme.GaziKoyuMavi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

@Composable
fun StudentInfoEditPage(
    navController: NavController,
    viewModel: StudentInfoEditPageViewModel = hiltViewModel(),
    sharedViewModel: SharedViewModel
) {

    StudentInfoEditPageContent(
        navController = navController,
        viewModel = viewModel,
        sharedViewModel = sharedViewModel
    )

}

@Composable
private fun StudentInfoEditPageContent(
    navController: NavController,
    viewModel: StudentInfoEditPageViewModel,
    sharedViewModel: SharedViewModel
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    state.student=sharedViewModel.student!!

    LoadingDialog(isLoading = state.isLoading)

    val student = state.student

    val tf_studentInfo = remember { mutableStateOf("") }
    val tf_studentEmail = remember { mutableStateOf("") }
    val tf_studentAddress = remember { mutableStateOf("") }
    val tf_studentPhone = remember { mutableStateOf("") }

    val tf_studentCurrentPassword = remember { mutableStateOf("") }
    val tf_studentNewPassword = remember { mutableStateOf("") }
    val tf_studentNewPasswordValid = remember { mutableStateOf("") }

    val tf_studentSkillList = remember { mutableStateListOf<Skill>() }

    val currentPasswordErrorState = remember { mutableStateOf(false) } //Uyuşup uyuşmuyor

    val newPasswordErrorState =
        remember { mutableStateOf(false) }  //8 karakter , Büyük harf , Küçük harf
    val newPasswordValidErrorState =
        remember { mutableStateOf(false) }   // 8 karakter , Büyük harf , Küçük harf

    val upperCaseSentenceColor = remember {
        mutableStateOf(Color.Red)
    }
    val lowerCaseSentenceColor = remember {
        mutableStateOf(Color.Red)
    }
    val eightCharacterSentenceColor = remember {
        mutableStateOf(Color.Red)
    }
    val calisanFunc = remember {
        mutableIntStateOf(0)
    }
    val calismisFunc = remember {
        mutableIntStateOf(0)
    }


    LaunchedEffect(key1 = sharedViewModel) {
        tf_studentSkillList.addAll(sharedViewModel.student!!.student_skillList)
        tf_studentAddress.value=sharedViewModel.student!!.student_address
        tf_studentInfo.value=sharedViewModel.student!!.student_info
        tf_studentPhone.value=sharedViewModel.student!!.student_phone
        tf_studentEmail.value=sharedViewModel.student!!.student_mail
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
                .clip(RoundedCornerShape(20.dp)), verticalArrangement = Arrangement.Top
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
                ),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                label = { Text(text = stringResource(id = R.string.email)) }
            )
            OutlinedTextField(
                value = tf_studentPhone.value,
                onValueChange = { tf_studentPhone.value = it },
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
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone, imeAction = ImeAction.Done),
                label = { Text(text = stringResource(id = R.string.telefon_no)) }
            )
            OutlinedTextField(
                value = tf_studentAddress.value,
                onValueChange = { tf_studentAddress.value = it },
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
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text , imeAction = ImeAction.Done),
                label = { Text(text = stringResource(id = R.string.adres)) }
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp), horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = stringResource(id = R.string.parola),
                    fontWeight = FontWeight.Black,
                    textDecoration = TextDecoration.Underline,
                    fontSize = 22.sp
                )
            }
            OutlinedTextField(
                value = tf_studentCurrentPassword.value,
                onValueChange = {
                    tf_studentCurrentPassword.value = it
                    currentPasswordErrorState.value = false
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 15.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedContainerColor = Color.White,
                    focusedContainerColor = Color.White,
                    disabledContainerColor = Color.White,
                    unfocusedBorderColor = GaziKoyuMavi,
                    focusedBorderColor = GaziKoyuMavi,
                ), keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                isError = currentPasswordErrorState.value,
                supportingText = {
                    if (currentPasswordErrorState.value) Text(
                        text = stringResource(
                            id = R.string.mevcut_parolaniz_uyusmuyor
                        ), color = Color.Red
                    )
                },
                singleLine = true,
                label = { Text(text = stringResource(id = R.string.mevcut_parola)) }
            )
            OutlinedTextField(
                value = tf_studentNewPassword.value,
                onValueChange = {
                    tf_studentNewPassword.value = it

                    if (it.length >= 8) {
                        eightCharacterSentenceColor.value = Color.Green
                        newPasswordErrorState.value = false
                    } else {
                        eightCharacterSentenceColor.value = Color.Red
                        newPasswordErrorState.value = true
                    }

                    if (containsLowerCase(it)) {
                        lowerCaseSentenceColor.value = Color.Green
                        newPasswordErrorState.value = false
                    } else {
                        lowerCaseSentenceColor.value = Color.Red
                        newPasswordErrorState.value = true
                    }

                    if (containsUpperCase(it)) {
                        upperCaseSentenceColor.value = Color.Green
                        newPasswordErrorState.value = false
                    } else {
                        upperCaseSentenceColor.value = Color.Red
                        newPasswordErrorState.value = true
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 15.dp, start = 15.dp, end = 15.dp, bottom = 5.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedContainerColor = Color.White,
                    focusedContainerColor = Color.White,
                    disabledContainerColor = Color.White,
                    unfocusedBorderColor = GaziKoyuMavi,
                    focusedBorderColor = GaziKoyuMavi,
                ), keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                isError = newPasswordErrorState.value,
                supportingText = {
                    Column(
                        modifier = Modifier
                            .padding(all = 15.dp)
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.Top
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = stringResource(id = R.string.en_az_8_karakter_olmalidir),
                                color = eightCharacterSentenceColor.value,
                                fontSize = 14.sp
                            )
                            Spacer(modifier = Modifier.width(2.dp))
                            Icon(
                                imageVector = if (eightCharacterSentenceColor.value == Color.Red) Icons.Default.Close else Icons.Default.Check,
                                contentDescription = "",
                                tint = eightCharacterSentenceColor.value,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = stringResource(id = R.string.buyuk_harf_icermelidir),
                                color = upperCaseSentenceColor.value,
                                fontSize = 14.sp
                            )
                            Spacer(modifier = Modifier.width(2.dp))
                            Icon(
                                imageVector = if (upperCaseSentenceColor.value == Color.Red) Icons.Default.Close else Icons.Default.Check,
                                contentDescription = "",
                                tint = upperCaseSentenceColor.value,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = stringResource(id = R.string.kucuk_harf_icermelidir),
                                color = lowerCaseSentenceColor.value,
                                fontSize = 14.sp
                            )
                            Spacer(modifier = Modifier.width(2.dp))
                            Icon(
                                imageVector = if (lowerCaseSentenceColor.value == Color.Red) Icons.Default.Close else Icons.Default.Check,
                                contentDescription = "",
                                tint = lowerCaseSentenceColor.value,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }
                },
                singleLine = true,
                label = { Text(text = stringResource(id = R.string.yeni_parola)) }
            )

            OutlinedTextField(
                value = tf_studentNewPasswordValid.value,
                onValueChange = {
                    tf_studentNewPasswordValid.value = it
                    newPasswordValidErrorState.value =
                        tf_studentNewPassword.value.trim() != it.trim()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 15.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedContainerColor = Color.White,
                    focusedContainerColor = Color.White,
                    disabledContainerColor = Color.White,
                    unfocusedBorderColor = GaziKoyuMavi,
                    focusedBorderColor = GaziKoyuMavi,
                ), keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                singleLine = true,
                isError = newPasswordValidErrorState.value,
                supportingText = {
                    if (newPasswordValidErrorState.value) Text(
                        text = stringResource(
                            id = R.string.yeni_parolaniz_uyusmuyor
                        ), fontSize = 14.sp, color = Color.Red
                    )
                },
                label = { Text(text = stringResource(id = R.string.yeni_parola_tekrar)) }
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
                            Text(
                                text = when (tf_studentSkillList[index].skill_level) {
                                    "İleri" -> stringResource(id = R.string.ileri_seviye)
                                    "Orta" -> stringResource(id = R.string.orta_seviye)
                                    else -> {
                                        stringResource(id = R.string.baslangic_seviye)
                                    }
                                }, fontSize = 20.sp
                            )
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

                            if (tf_studentInfo.value.trim() != student.student_info || tf_studentEmail.value.trim() != student.student_mail || tf_studentPhone.value.trim() != student.student_phone || tf_studentAddress.value.trim() != student.student_address ) {
                                calisanFunc.intValue += 1

                                student.student_mail = tf_studentEmail.value.trim()
                                student.student_info = tf_studentInfo.value.trim()
                                student.student_phone = tf_studentPhone.value.trim()
                                student.student_address = tf_studentAddress.value.trim()

                                val job1 =
                                    async { viewModel.saveStudentInformation(student = student) }
                                if (job1.await()) {
                                    calismisFunc.intValue += 1
                                    sharedViewModel.addStudent(sharedViewModel.student!!.copy(student_mail = student.student_mail, student_info = student.student_info, student_address = student.student_address, student_phone = student.student_phone))
                                } else {
                                    calisanFunc.intValue -= 1
                                }
                            }
                            if (tf_studentSkillList.toList() != student.student_skillList.toList()) {

                                calisanFunc.intValue += 1
                                val job2 = async {
                                    viewModel.saveStudentSkills(
                                        student_no = student.student_no,
                                        skillList = tf_studentSkillList
                                    )
                                }
                                if (job2.await()) {
                                    calismisFunc.intValue += 1
                                    sharedViewModel.addStudent(sharedViewModel.student!!.copy(student_skillList = student.student_skillList))
                                } else {
                                    calisanFunc.intValue -= 1
                                }
                            }
                            if (tf_studentCurrentPassword.value != "" && tf_studentNewPassword.value != "" && !newPasswordValidErrorState.value) {

                                if (tf_studentCurrentPassword.value == student.student_password) {

                                    calisanFunc.intValue += 1
                                    student.student_password = tf_studentNewPasswordValid.value
                                    val job3 = async { viewModel.saveStudentPassword(student) }
                                    if (job3.await()) {
                                        calismisFunc.intValue += 1
                                        sharedViewModel.addStudent(sharedViewModel.student!!.copy(student_password = student.student_password))
                                    } else {
                                        calisanFunc.intValue -= 1
                                    }
                                } else {
                                    currentPasswordErrorState.value = true
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
                newSkill.value = Skill("", "", "", "")
            }

        }


        LaunchedEffect(key1 = calismisFunc.intValue) {
            if (calismisFunc.intValue == calisanFunc.intValue && calisanFunc.intValue != 0) {
                navController.popBackStack()
            }
        }
    }
    Spacer(modifier = Modifier.windowInsetsBottomHeight(WindowInsets.systemBars))
}

fun containsLowerCase(text: String): Boolean {
    for (char in text) {
        if (char.isLowerCase()) {
            return true
        }
    }
    return false
}

fun containsUpperCase(text: String): Boolean {
    for (char in text) {
        if (char.isUpperCase()) {
            return true
        }
    }
    return false
}