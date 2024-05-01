package com.gokhanakbas.isyeriegitimiandroidapp.presentation.lecturer.lecturerInfoEditPage

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.gokhanakbas.isyeriegitimiandroidapp.R
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Lecturer
import com.gokhanakbas.isyeriegitimiandroidapp.presentation.util.components.LoadingDialog
import com.gokhanakbas.isyeriegitimiandroidapp.ui.theme.GaziAcikMavi
import com.gokhanakbas.isyeriegitimiandroidapp.ui.theme.GaziKoyuMavi

@Composable
fun LecturerInfoEditPage(navController: NavController,lecturer_id:String,viewModel: LecturerInfoEditPageViewModel= hiltViewModel()) {

    LaunchedEffect(key1 = viewModel) {
        viewModel.getLecturerInformation(lecturer_id)
    }

    val state by viewModel.state.collectAsStateWithLifecycle()

    LecturerInfoEditPageContent(navController = navController, state = state)

}

@Composable
fun LecturerInfoEditPageContent(navController: NavController,state: LecturerInfoEditPageState) {

    LoadingDialog(isLoading = state.isLoading)

    val lecturer=state.lecturer

    val scrollState = rememberScrollState()

    val tf_lecturerInfo = remember {
        mutableStateOf("")
    }
    tf_lecturerInfo.value=lecturer.lecturer_info

    val tf_lecturerEmail = remember {
        mutableStateOf("")
    }
    tf_lecturerEmail.value=lecturer.lecturer_mail

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
                ), keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                label = { Text(text = stringResource(id = R.string.email)) }
            )
            OutlinedButton(
                onClick = {
                    // Izleyici Bilgilerini kaydetme islemleri gerceklesecek

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

    }
}