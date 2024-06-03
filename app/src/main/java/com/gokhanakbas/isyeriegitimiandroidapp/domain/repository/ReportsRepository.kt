package com.gokhanakbas.isyeriegitimiandroidapp.domain.repository

import androidx.compose.runtime.MutableState
import arrow.core.Either
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.NetworkError
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Report

interface ReportsRepository {

    suspend fun getReports(student_no : String) : Either<NetworkError,MutableState<ArrayList<Report>>>

    suspend fun addWeeklyReports(report: Report,student_no: String) : Either<NetworkError,Boolean>

    suspend fun getReportsInformation(report_id : String) : Either<NetworkError,Report>

    suspend fun deleteWeeklyReport(report_id : String) : Either<NetworkError,Boolean>

    suspend fun updateWeeklyReport(report: Report) : Either<NetworkError,Boolean>
}