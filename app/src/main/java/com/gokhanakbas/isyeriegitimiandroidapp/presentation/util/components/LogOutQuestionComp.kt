package com.gokhanakbas.isyeriegitimiandroidapp.presentation.util.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.gokhanakbas.isyeriegitimiandroidapp.R
import com.gokhanakbas.isyeriegitimiandroidapp.presentation.navigation.Screen
import com.gokhanakbas.isyeriegitimiandroidapp.presentation.commission.lastPage

@Composable
fun LogOutQuestionComp(
    log_out_choice: MutableState<Boolean>,
    navController: NavController,
    popUpScreen : Screen
) {
    log_out_choice.value = true
    when (log_out_choice.value) {
        true -> {
            AlertDialog(onDismissRequest = { log_out_choice.value }, confirmButton = {
                Button(
                    onClick = {
                        //ayni zamanda burada beni hatirla ozelligi sayesinde kaydetmis oldugumuz bilgileri telefonun veritabanindan silinecek
                        log_out_choice.value = false
                        lastPage=1
                        com.gokhanakbas.isyeriegitimiandroidapp.presentation.student.lastPage=1
                        com.gokhanakbas.isyeriegitimiandroidapp.presentation.firm.lastPage=1
                        com.gokhanakbas.isyeriegitimiandroidapp.presentation.lecturer.lastPage=1
                        navController.navigate(Screen.EntryPage.route) {
                            popUpTo(popUpScreen.route) { inclusive = true }
                        }
                    }, colors = ButtonDefaults.buttonColors(
                        contentColor = Color.Red
                    )
                ) {
                    Text(text = stringResource(id = R.string.cikis_yap))
                }
            }, dismissButton = {
                Button(
                    onClick = {
                        log_out_choice.value = false
                    }, colors = ButtonDefaults.buttonColors(
                        contentColor = Color.Green
                    )
                ) {
                    Text(text = stringResource(id = R.string.iptal_et))
                }
            }, title = { Text(text = stringResource(id = R.string.cikis_soru), fontSize = 24.sp) })
        }

        false -> {
            navController.popBackStack()
        }
    }
}