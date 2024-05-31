package com.gokhanakbas.isyeriegitimiandroidapp.presentation.util.pagecomponents.weeklyReportListPage

import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Report

data class WeeklyReportListPageState(
    var isLoading : Boolean=false,
    var report_list : List<Report> = emptyList(),
    var deletedSuccesfully : Boolean = false,
    var error: String?=null
)