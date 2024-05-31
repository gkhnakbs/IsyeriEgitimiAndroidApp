package com.gokhanakbas.isyeriegitimiandroidapp.presentation.pages.reportsPage

import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Report

data class ReportsPageState (
    var isLoading : Boolean = false,
    var report : Report = Report("","","",""),
    var savedSuccessfully : Boolean=false,
    var error: String?=null
)