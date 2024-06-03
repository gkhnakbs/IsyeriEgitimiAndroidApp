package com.gokhanakbas.isyeriegitimiandroidapp.presentation.util.pagecomponents.weeklyReportListPage

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Report

data class WeeklyReportListPageState(
    var isLoading : Boolean=false,
    var report_list : MutableState<ArrayList<Report>> = mutableStateOf(arrayListOf()),
    var deletedSuccesfully : Boolean = false,
    var error: String?=null
)