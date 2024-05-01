package com.gokhanakbas.isyeriegitimiandroidapp.presentation.pages.reportsPage

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
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
import com.gokhanakbas.isyeriegitimiandroidapp.ui.theme.GaziKoyuMavi
import com.gokhanakbas.isyeriegitimiandroidapp.util.Constants
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun ReportsPage(
    navController: NavController,
    report_id: String,
    editable: Boolean,
    viewModel: ReportsPageViewModel = hiltViewModel()
) {

    LaunchedEffect(key1 = viewModel) {
        if (!editable) {
            viewModel.getReportsInformation(report_id)
        }
    }

    val state by viewModel.state.collectAsStateWithLifecycle()
    state.editable = editable

    ReportsPageContent(navController = navController, state = state, viewModel = viewModel)

}

@Composable
fun ReportsPageContent(
    navController: NavController,
    state: ReportsPageState,
    viewModel: ReportsPageViewModel
) {

    val context = LocalContext.current

    val saveQuestionState = remember {
        mutableStateOf(false)
    }
    val saveQuestionResult = remember {
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
                .padding(15.dp)
        ) {

            OutlinedTextField(
                value = tf_report_description.value,
                onValueChange = { tf_report_description.value = it },
                readOnly = !state.editable,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.8f),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = GaziKoyuMavi,
                    focusedBorderColor = GaziKoyuMavi,
                ),
                label = { Text(text = stringResource(id = R.string.haftalik_rapor)) }
            )

            if (state.editable) {
                Row(
                    modifier = Modifier.fillMaxWidth().weight(0.2f),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End
                ) {
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
    }

    if (saveQuestionState.value) ReportSaveQuestionComp(
        saveQuestionState = saveQuestionState,
        saveQuestionResult = saveQuestionResult
    )


    LaunchedEffect(key1 = saveQuestionState.value) {
        if (!saveQuestionState.value) {
            if (saveQuestionResult.value) {
                
                val report = Report("", tf_report_description.value.trim(),"", "")

                CoroutineScope(Dispatchers.IO).launch {
                    val job1 = async { viewModel.addWeeklyReports(context, report, Constants.STUDENT_NO) }
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