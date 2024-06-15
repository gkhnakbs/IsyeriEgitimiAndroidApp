package com.gokhanakbas.isyeriegitimiandroidapp.presentation.util.components

import android.app.Activity
import androidx.compose.foundation.BorderStroke
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gokhanakbas.isyeriegitimiandroidapp.R

@Composable
fun AdvertDeleteQuestionComp(deleteQuestion: MutableState<Boolean>, result : MutableState<Boolean>) {
    val activity = (LocalContext.current) as Activity
    if (deleteQuestion.value) {
        AlertDialog(onDismissRequest = { deleteQuestion.value = false }, confirmButton = {
            Button(
                onClick = {
                    //ayni zamanda burada beni hatirla ozelligi sayesinde kaydetmis oldugumuz bilgileri telefonun veritabanindan silinecek
                    deleteQuestion.value = false
                    result.value=true
                }, colors = ButtonDefaults.buttonColors(
                    contentColor = Color.Red,
                    containerColor = Color.White
                ),
                border = BorderStroke(0.5.dp, Color.Red)

            ) {
                Text(text = stringResource(id = R.string.sil))
            }
        }, dismissButton = {
            Button(
                onClick = {
                    deleteQuestion.value = false
                    result.value=false
                }, colors = ButtonDefaults.buttonColors(
                    contentColor = Color.Green,
                    containerColor = Color.White
                ), border = BorderStroke(0.5.dp, Color.Green)
            ) {
                Text(text = stringResource(id = R.string.iptal_et))
            }
        }, title = { Text(text = stringResource(id = R.string.ilani_silmek_istedigine_emin_misin), fontSize = 24.sp) })
    }
}