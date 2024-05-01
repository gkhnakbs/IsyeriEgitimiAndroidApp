package com.gokhanakbas.isyeriegitimiandroidapp.presentation.firm

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.gokhanakbas.isyeriegitimiandroidapp.R
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Firm
import com.gokhanakbas.isyeriegitimiandroidapp.ui.theme.GaziAcikMavi
import com.gokhanakbas.isyeriegitimiandroidapp.ui.theme.GaziKoyuMavi

@Composable
fun FirmInfoEditPage(navController: NavController, firm: Firm) {
    val scrollState = rememberScrollState()

    val tf_firmInfo = remember {
        mutableStateOf(firm.firm_info)
    }

    val tf_firmEmail = remember {
        mutableStateOf(firm.firm_mail)
    }
    val tf_firmPhone = remember {
        mutableStateOf(firm.firm_phone)
    }
    val tf_firmAddress = remember {
        mutableStateOf(firm.firm_address)
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
                label = { Text(text = stringResource(id = R.string.izleyici_no)) }
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
                label = { Text(text = stringResource(id = R.string.hakkimda)) }
            )

            OutlinedTextField(
                value = tf_firmEmail.value,
                onValueChange = { tf_firmEmail.value = it},
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
                label = { Text(text = stringResource(id = R.string.firma_mail)) },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = "Email Icon For Firm Info Edit Page"
                    )
                }
            )
            OutlinedTextField(
                value = tf_firmPhone.value,
                onValueChange = { tf_firmPhone.value = it },
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
                label = { Text(text = stringResource(id = R.string.firma_telNo)) },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Phone,
                        contentDescription = "Phone Icon For Firm Info Edit Page"
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

            OutlinedButton(
                onClick = {
                    // Firma Bilgilerini kaydetme islemleri gerceklesecek

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