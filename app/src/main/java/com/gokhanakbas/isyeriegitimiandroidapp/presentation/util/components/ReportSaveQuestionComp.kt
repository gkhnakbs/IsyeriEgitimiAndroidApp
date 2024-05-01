package com.gokhanakbas.isyeriegitimiandroidapp.presentation.util.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gokhanakbas.isyeriegitimiandroidapp.R
import com.gokhanakbas.isyeriegitimiandroidapp.ui.theme.GaziKoyuMavi

@Composable
fun ReportSaveQuestionComp(
    saveQuestionState: MutableState<Boolean>,
    saveQuestionResult: MutableState<Boolean>
) {

    AlertDialog(
        onDismissRequest = { saveQuestionState.value = false },
        confirmButton = {
            OutlinedButton(
                onClick = {
                    saveQuestionResult.value = true
                    saveQuestionState.value = false
                }, border = BorderStroke(0.5.dp, GaziKoyuMavi), shape = RoundedCornerShape(20.dp)
            ) { Text(text = stringResource(id = R.string.kaydet)) }
        },
        dismissButton = {
            OutlinedButton(
                onClick = {
                    saveQuestionResult.value = false
                    saveQuestionState.value = false
                }, border = BorderStroke(0.5.dp, Color.Red),
                shape = RoundedCornerShape(20.dp)
            ) { Text(text = stringResource(id = R.string.iptal_et)) }
        },
        title = {
            Text(
                text = stringResource(id = R.string.raporu_kaydetmek_istedigine_emin_misin),
                fontSize = 24.sp
            )
        },
        text = {
            Text(
                text = stringResource(id = R.string.eger_kaydedersen_tekrar_degistiremeyecek_veya_silemeyeceksin),
                fontSize = 18.sp
            )
        })

}