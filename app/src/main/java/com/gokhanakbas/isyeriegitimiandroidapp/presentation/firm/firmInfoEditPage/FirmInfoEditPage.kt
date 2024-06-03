package com.gokhanakbas.isyeriegitimiandroidapp.presentation.firm.firmInfoEditPage

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
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Place
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
import com.gokhanakbas.isyeriegitimiandroidapp.presentation.navigation.SharedViewModel
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
fun FirmInfoEditPage(
    navController: NavController,
    viewModel: FirmInfoEditPageViewModel = hiltViewModel(),
    sharedViewModel: SharedViewModel
) {

    /* LaunchedEffect(key1 = viewModel) {
         viewModel.getFirmInformation(firm_id)
     }*/

    val state by viewModel.state.collectAsStateWithLifecycle()
    state.firm = sharedViewModel.firm!!

    FirmInfoEditPageContent(navController, state, viewModel, sharedViewModel = sharedViewModel)

}


@Composable
private fun FirmInfoEditPageContent(
    navController: NavController,
    firmInfoEditPageState: FirmInfoEditPageState,
    viewModel: FirmInfoEditPageViewModel,
    sharedViewModel: SharedViewModel
) {

    LoadingDialog(isLoading = firmInfoEditPageState.isLoading)

    val scrollState = rememberScrollState()

    val firm = firmInfoEditPageState.firm

    val tf_firmInfo = remember {
        mutableStateOf(firm.firm_info)
    }

    val tf_firmMail = remember {
        mutableStateOf(firm.firm_mail)
    }
    val tf_firmPhone = remember {
        mutableStateOf(firm.firm_phone)
    }
    val tf_firmAddress = remember {
        mutableStateOf(firm.firm_address)
    }

    val tf_firmCurrentPassword = remember { mutableStateOf("") }
    val tf_firmNewPassword = remember { mutableStateOf("") }
    val tf_firmNewPasswordValid = remember { mutableStateOf("") }

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

    LaunchedEffect(key1 = firmInfoEditPageState.firm.firm_info) {
        tf_firmInfo.value = firm.firm_info
        tf_firmMail.value = firm.firm_mail
        tf_firmAddress.value = firm.firm_address
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
                value = firm.firm_name,
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
                label = { Text(text = stringResource(id = R.string.firma_no)) }
            )

            OutlinedTextField(
                value = firm.firm_sector,
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
                label = { Text(text = stringResource(id = R.string.firma_adi)) }
            )

            OutlinedTextField(
                value = tf_firmInfo.value,
                onValueChange = { tf_firmInfo.value = it },
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
                label = { Text(text = stringResource(id = R.string.firma_hakkinda)) }
            )

            OutlinedTextField(
                value = tf_firmMail.value,
                onValueChange = { tf_firmMail.value = it },
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
                singleLine = true,
                label = { Text(text = stringResource(id = R.string.firma_mail)) },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = "Email Icon For Firm Info Edit Page"
                    )
                }
            )
            OutlinedTextField(
                value = tf_firmAddress.value,
                onValueChange = { tf_firmAddress.value = it },
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
                minLines = 5,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                label = { Text(text = stringResource(id = R.string.firma_adres)) },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Place,
                        contentDescription = "Address Icon For Firm Info Edit Page"
                    )
                }
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
                value = tf_firmCurrentPassword.value,
                onValueChange = {
                    tf_firmCurrentPassword.value = it
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
                value = tf_firmNewPassword.value,
                onValueChange = {
                    tf_firmNewPassword.value = it

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
                value = tf_firmNewPasswordValid.value,
                onValueChange = {
                    tf_firmNewPasswordValid.value = it
                    newPasswordValidErrorState.value = tf_firmNewPassword.value.trim() != it.trim()
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

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedButton(
                    onClick = {

                        CoroutineScope(Dispatchers.IO).launch {
                            // Firma Bilgilerini kaydetme islemleri gerceklesecek
                            if (tf_firmMail.value != firm.firm_mail || tf_firmInfo.value != firm.firm_info || tf_firmAddress.value != firm.firm_address) {
                                calisanFunc.intValue += 1
                                firm.firm_info = tf_firmInfo.value.trim()
                                firm.firm_address = tf_firmAddress.value.trim()
                                firm.firm_mail = tf_firmMail.value.trim()

                                val job = async { viewModel.saveFirmInformation(firm) }

                                if (job.await()) {
                                    calismisFunc.intValue += 1
                                    sharedViewModel.addFirm(
                                        sharedViewModel.firm!!.copy(
                                            firm_info = firm.firm_info,
                                            firm_address = firm.firm_address,
                                            firm_mail = firm.firm_mail
                                        )
                                    )
                                } else {
                                    calisanFunc.intValue -= 1
                                }


                            }

                            if (tf_firmCurrentPassword.value != "" && tf_firmNewPassword.value != "" && !newPasswordValidErrorState.value) {
                                if (tf_firmCurrentPassword.value == firm.firm_password) {
                                    calisanFunc.intValue += 1
                                    firm.firm_password = tf_firmNewPassword.value
                                    val job1 = async { viewModel.saveFirmPassword(firm) }
                                    if (job1.await()) {
                                        calismisFunc.intValue += 1
                                        sharedViewModel.addFirm(
                                            sharedViewModel.firm!!.copy(
                                                firm_password = firm.firm_password
                                            )
                                        )
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


        LaunchedEffect(key1 = calismisFunc.intValue) {
            if (calismisFunc.intValue == calisanFunc.intValue && calisanFunc.intValue != 0) {
                navController.popBackStack()
            }
        }

    }
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