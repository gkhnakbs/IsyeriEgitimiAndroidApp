package com.gokhanakbas.isyeriegitimiandroidapp.presentation.util.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import com.gokhanakbas.isyeriegitimiandroidapp.R

@Composable
fun ApplyQuestionComp(applyQuestion : MutableState<Boolean>,result: MutableState<Boolean>){
    if(applyQuestion.value) {
        AlertDialog(
            onDismissRequest = { applyQuestion.value },
            confirmButton = {
                OutlinedButton(onClick = {
                    result.value = true
                    applyQuestion.value = false
                }) { Text(text = stringResource(id = R.string.basvur)) }
            },
            dismissButton = {
                OutlinedButton(onClick = {
                    result.value = false
                    applyQuestion.value = false
                }) { Text(text = stringResource(id = R.string.iptal_et)) }
            },
            title = {
                Text(
                    text = stringResource(id = R.string.basvurmak_istedigine_emin_misin),
                    fontSize = 24.sp
                )
            })

    }

}