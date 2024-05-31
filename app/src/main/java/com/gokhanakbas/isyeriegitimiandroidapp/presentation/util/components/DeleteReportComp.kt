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
fun DeleteReportComp(deleteReportState : MutableState<Boolean>, deleteReportChoice : MutableState<Boolean>) {
    if(deleteReportState.value) {
        AlertDialog(
            onDismissRequest = { deleteReportState.value=false },
            confirmButton = {
                OutlinedButton(onClick = {
                    deleteReportChoice.value = true
                    deleteReportState.value = false
                }) { Text(text = stringResource(id = R.string.raporu_sil)) }
            },
            dismissButton = {
                OutlinedButton(onClick = {
                    deleteReportChoice.value = false
                    deleteReportState.value = false
                }) { Text(text = stringResource(id = R.string.iptal_et)) }
            },
            title = {
                Text(
                    text = stringResource(id = R.string.raporu_silmek_istedigine_emin_misin),
                    fontSize = 24.sp
                )
            })

    }
}