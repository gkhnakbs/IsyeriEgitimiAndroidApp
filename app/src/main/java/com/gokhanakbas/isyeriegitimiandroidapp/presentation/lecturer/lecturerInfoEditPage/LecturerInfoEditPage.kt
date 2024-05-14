package com.gokhanakbas.isyeriegitimiandroidapp.presentation.lecturer.lecturerInfoEditPage

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
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
import com.gokhanakbas.isyeriegitimiandroidapp.presentation.student.studentInfoEditPage.containsLowerCase
import com.gokhanakbas.isyeriegitimiandroidapp.presentation.student.studentInfoEditPage.containsUpperCase
import com.gokhanakbas.isyeriegitimiandroidapp.presentation.util.components.LoadingDialog
import com.gokhanakbas.isyeriegitimiandroidapp.ui.theme.GaziAcikMavi
import com.gokhanakbas.isyeriegitimiandroidapp.ui.theme.GaziKoyuMavi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

@Composable
fun LecturerInfoEditPage(
    navController: NavController,
    lecturer_id: String,
    viewModel: LecturerInfoEditPageViewModel = hiltViewModel()
) {

    LaunchedEffect(key1 = viewModel) {
        viewModel.getLecturerInformation(lecturer_id)
    }

    val state by viewModel.state.collectAsStateWithLifecycle()

    LecturerInfoEditPageContent(navController = navController, state = state, viewModel = viewModel)

}

@Composable
fun LecturerInfoEditPageContent(navController: NavController, state: LecturerInfoEditPageState,viewModel: LecturerInfoEditPageViewModel) {

    LoadingDialog(isLoading = state.isLoading)

    var lecturer=state.lecturer

    val scrollState = rememberScrollState()

    val tf_lecturerInfo = remember {
        mutableStateOf("")
    }
    val tf_lecturerEmail = remember {
        mutableStateOf("")
    }

    val tf_lecturerCurrentPassword = remember { mutableStateOf("") }
    val tf_lecturerNewPassword = remember { mutableStateOf("") }
    val tf_lecturerNewPasswordValid = remember { mutableStateOf("") }

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

    LaunchedEffect(key1 = state.lecturer.lecturer_id) {
        lecturer=state.lecturer
        tf_lecturerInfo.value=state.lecturer.lecturer_info
        tf_lecturerEmail.value=state.lecturer.lecturer_mail
    }

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
                .padding(10.dp)
                .clip(RoundedCornerShape(20.dp))
        ) {
            OutlinedTextField(
                value = lecturer.lecturer_id,
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
                label = { Text(text = stringResource(id = R.string.izleyici_no)) })

            OutlinedTextField(
                value = lecturer.lecturer_name,
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
                value = lecturer.lecturer_faculty,
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
                label = { Text(text = stringResource(id = R.string.fakulte)) }
            )
            OutlinedTextField(
                value = lecturer.lecturer_department,
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
                label = { Text(text = stringResource(id = R.string.bolum)) })

            OutlinedTextField(
                value = tf_lecturerInfo.value,
                onValueChange = { tf_lecturerInfo.value = it },
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
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                label = { Text(text = stringResource(id = R.string.hakkimda)) })

            OutlinedTextField(
                value = tf_lecturerEmail.value,
                onValueChange = { tf_lecturerEmail.value = it },
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
                value = tf_lecturerCurrentPassword.value,
                onValueChange = {
                    tf_lecturerCurrentPassword.value = it
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
                value = tf_lecturerNewPassword.value,
                onValueChange = {
                    tf_lecturerNewPassword.value = it

                    if (it.length >= 8) {
                        eightCharacterSentenceColor.value = Color.Green
                        newPasswordErrorState.value = false
                    }   else {
                        eightCharacterSentenceColor.value = Color.Red
                        newPasswordErrorState.value = true
                    }

                    if (containsLowerCase(it)){
                        lowerCaseSentenceColor.value = Color.Green
                        newPasswordErrorState.value = false
                    } else {
                        lowerCaseSentenceColor.value = Color.Red
                        newPasswordErrorState.value = true
                    }

                    if (containsUpperCase(it)){
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
                value = tf_lecturerNewPasswordValid.value,
                onValueChange = {
                    tf_lecturerNewPasswordValid.value = it
                    newPasswordValidErrorState.value = tf_lecturerNewPassword.value.trim() != it.trim()
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
                supportingText = { if (newPasswordValidErrorState.value) Text(text = stringResource(
                    id = R.string.yeni_parolaniz_uyusmuyor) , fontSize = 14.sp,color=Color.Red)  },
                label = { Text(text = stringResource(id = R.string.yeni_parola_tekrar)) }
            )


            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedButton(
                    onClick = {
                        // Izleyici Bilgilerini kaydetme islemleri gerceklesecek
                        CoroutineScope(Dispatchers.IO).launch {
                            if(tf_lecturerInfo.value!=lecturer.lecturer_info || tf_lecturerEmail.value!=lecturer.lecturer_mail){

                                lecturer.lecturer_info=tf_lecturerInfo.value.trim()
                                lecturer.lecturer_mail=tf_lecturerEmail.value.trim()
                                calisanFunc.intValue+=1
                                val job = async { viewModel.saveLecturerInformation(lecturer) }
                                if(job.await()){
                                    calismisFunc.intValue+=1
                                }
                                else{
                                    calismisFunc.intValue-=1
                                }
                            }

                            if(tf_lecturerCurrentPassword.value!=""&&tf_lecturerNewPassword.value!=""&&!newPasswordValidErrorState.value){

                                println("tf password ${tf_lecturerNewPassword.value} lecturer password${lecturer.lecturer_password}  ")

                                if(tf_lecturerCurrentPassword.value==lecturer.lecturer_password){
                                    lecturer.lecturer_password=tf_lecturerNewPassword.value
                                    calisanFunc.intValue+=1

                                    val job2 = async { viewModel.saveLecturerPassword(lecturer) }
                                    if(job2.await()){
                                        calismisFunc.intValue+=1
                                    }
                                    else{
                                        calisanFunc.intValue-=1
                                    }
                                }else{
                                    currentPasswordErrorState.value=true
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

        LaunchedEffect(key1 = calismisFunc.intValue) {
            if(calisanFunc.intValue==calismisFunc.intValue && calisanFunc.intValue!=0){
                navController.popBackStack()
            }
        }

    }
}