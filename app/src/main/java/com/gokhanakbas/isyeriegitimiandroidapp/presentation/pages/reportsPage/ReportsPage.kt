package com.gokhanakbas.isyeriegitimiandroidapp.presentation.pages.reportsPage

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.gokhanakbas.isyeriegitimiandroidapp.R
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Report
import com.gokhanakbas.isyeriegitimiandroidapp.presentation.util.components.LoadingDialog
import com.gokhanakbas.isyeriegitimiandroidapp.presentation.util.components.ReportSaveQuestionComp
import com.gokhanakbas.isyeriegitimiandroidapp.presentation.util.components.ReportUpdateQuestionComp
import com.gokhanakbas.isyeriegitimiandroidapp.ui.theme.GaziKoyuMavi
import com.gokhanakbas.isyeriegitimiandroidapp.util.Constants
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun ReportsPage(
    navController: NavController,
    report_id: String,
    reason: String,
    viewModel: ReportsPageViewModel = hiltViewModel()
) {

    LaunchedEffect(key1 = viewModel) {
        if (reason == "updateReport") {
            viewModel.getReportsInformation(report_id)
        } else if (reason == "showReport") {
            viewModel.getReportsInformation(report_id)
        }
    }


    ReportsPageContent(navController = navController, reason = reason, viewModel = viewModel)

}

@Composable
private fun ReportsPageContent(
    navController: NavController,
    reason: String,
    viewModel: ReportsPageViewModel
) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    val context = LocalContext.current

    val saveQuestionState = remember {
        mutableStateOf(false)
    }
    val saveQuestionResult = remember {
        mutableStateOf(false)
    }

    val updateQuestionState = remember {
        mutableStateOf(false)
    }
    val updateQuestionResult = remember {
        mutableStateOf(false)
    }

    val tf_report_description = remember {
        mutableStateOf("")
    }

    val tf_report_date = remember {
        mutableStateOf("")
    }

    LoadingDialog(isLoading = state.isLoading)


    LaunchedEffect(key1 = state.report) {
        tf_report_description.value = state.report.report_description
        tf_report_date.value = state.report.report_date
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top=40.dp, start = 15.dp, end = 15.dp, bottom = 15.dp)
        ) {

            OutlinedTextField(
                value = tf_report_description.value,
                onValueChange = { tf_report_description.value = it },
                readOnly = reason == "showReport",
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.8f),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = GaziKoyuMavi,
                    focusedBorderColor = GaziKoyuMavi,
                ),
                label = { Text(text = stringResource(id = R.string.haftalik_rapor)) }
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.2f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                if (reason == "updateReport") {
                    OutlinedButton(
                        onClick = {
                            //Raporu kaydetme işlemi gerçekleşecek ancak kaydetmeden önce bir dialog ile emin misin sorusu sorulacak.
                            if (tf_report_description.value.trim().isNotEmpty()) {
                                updateQuestionState.value = true
                            }
                        }, colors = ButtonDefaults.outlinedButtonColors(
                            containerColor = Color.White
                        ), border = BorderStroke(0.5.dp, GaziKoyuMavi)
                    ) {
                        Text(text = stringResource(id = R.string.guncelle), fontSize = 20.sp)
                    }
                } else if(reason=="newReport"){
                    OutlinedButton(
                        onClick = {
                            //Raporu kaydetme işlemi gerçekleşecek ancak kaydetmeden önce bir dialog ile emin misin sorusu sorulacak.
                            if (tf_report_description.value.trim().isNotEmpty()) {
                                saveQuestionState.value = true
                            }
                        }, colors = ButtonDefaults.outlinedButtonColors(
                            containerColor = Color.White
                        ), border = BorderStroke(0.5.dp, GaziKoyuMavi)
                    ) {
                        Text(text = stringResource(id = R.string.kaydet), fontSize = 20.sp)
                    }
                }
            }

        }
        OutlinedButton(
            onClick = {
                //Raporu kaydetme işlemi gerçekleşecek ancak kaydetmeden önce bir dialog ile emin misin sorusu sorulacak.
                navController.navigateUp()
            }, colors = ButtonDefaults.outlinedButtonColors(
                containerColor = Color.White
            ), border = BorderStroke(0.5.dp, GaziKoyuMavi), modifier = Modifier.align(Alignment.TopStart)
        ) {
            Text(text = stringResource(id = R.string.geri_don), fontSize = 20.sp)
        }
    }

    if (saveQuestionState.value) {
        ReportSaveQuestionComp(
            saveQuestionState = saveQuestionState,
            saveQuestionResult = saveQuestionResult
        )
    }

    if (updateQuestionState.value) {
        ReportUpdateQuestionComp(
            updateQuestionState = updateQuestionState,
            updateQuestionResult = updateQuestionResult
        )
    }



    LaunchedEffect(key1 = saveQuestionState.value) {
        if (!saveQuestionState.value) {
            if (saveQuestionResult.value) {

                val report = Report("", tf_report_description.value.trim(), "", "")

                CoroutineScope(Dispatchers.IO).launch {
                    val job1 =
                        async { viewModel.addWeeklyReports(context, report, Constants.STUDENT_NO) }
                    if (job1.await()) {
                        withContext(Dispatchers.Main) {
                            navController.popBackStack()
                        }
                    }
                }
            }
        }
    }

    LaunchedEffect(key1 = updateQuestionState.value) {
        if (!updateQuestionState.value) {
            if (updateQuestionResult.value) {
                val todayDate = LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE)!!

                val report1 = Report(
                    state.report.report_id,
                    tf_report_description.value.trim(),
                    todayDate,
                    state.report.report_studentNo
                )

                CoroutineScope(Dispatchers.IO).launch {
                    val job1 = viewModel.updateWeeklyReport(report1)
                    if (job1.await()) {
                        withContext(Dispatchers.Main) {
                            navController.popBackStack()
                        }
                    }
                }
            }
        }
    }
}