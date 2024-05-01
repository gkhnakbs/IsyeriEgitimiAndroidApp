package com.gokhanakbas.isyeriegitimiandroidapp.domain.repository

import arrow.core.Either
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.NetworkError
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Report

interface ReportsRepository {

    suspend fun getReports(student_no : String) : Either<NetworkError,List<Report>>

    suspend fun addWeeklyReports(report: Report,student_no: String) : Either<NetworkError,Boolean>

    suspend fun getReportsInformation(report_id : String) : Either<NetworkError,Report>

}