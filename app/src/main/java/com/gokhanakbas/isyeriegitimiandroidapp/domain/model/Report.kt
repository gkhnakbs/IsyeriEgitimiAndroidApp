package com.gokhanakbas.isyeriegitimiandroidapp.domain.model

import java.util.UUID

data class Report(
    val report_id : String,
    var report_description : String,
    var report_date : String,
    var report_studentNo : String,
    var id : String = UUID.randomUUID().toString()
)
