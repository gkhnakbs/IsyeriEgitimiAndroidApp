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
fun ReportUpdateQuestionComp(
    updateQuestionState: MutableState<Boolean>,
    updateQuestionResult: MutableState<Boolean>
) {

    AlertDialog(
        onDismissRequest = { updateQuestionState.value = false },
        confirmButton = {
            OutlinedButton(
                onClick = {
                    updateQuestionResult.value = true
                    updateQuestionState.value = false
                }, border = BorderStroke(0.5.dp, GaziKoyuMavi), shape = RoundedCornerShape(20.dp)
            ) { Text(text = stringResource(id = R.string.guncelle)) }
        },
        dismissButton = {
            OutlinedButton(
                onClick = {
                    updateQuestionResult.value = false
                    updateQuestionState.value = false
                }, border = BorderStroke(0.5.dp, Color.Red),
                shape = RoundedCornerShape(20.dp)
            ) { Text(text = stringResource(id = R.string.iptal_et)) }
        },
        title = {
            Text(
                text = stringResource(id = R.string.raporu_guncellemek_istedigine_emin_misin),
                fontSize = 24.sp
            )
        }
    )

}