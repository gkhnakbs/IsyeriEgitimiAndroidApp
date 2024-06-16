package com.gokhanakbas.isyeriegitimiandroidapp.data.repository

import androidx.compose.runtime.MutableState
import arrow.core.Either
import com.gokhanakbas.isyeriegitimiandroidapp.data.mapper.toNetworkError
import com.gokhanakbas.isyeriegitimiandroidapp.data.remote.ReportsApi
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.NetworkError
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Report
import com.gokhanakbas.isyeriegitimiandroidapp.domain.repository.ReportsRepository
import javax.inject.Inject

class ReportsRepositoryImpl @Inject constructor(private val reportsApi: ReportsApi) :
    ReportsRepository {

    override suspend fun getReports(student_no: String): Either<NetworkError, MutableList<Report>> {
        return Either.catch {
            reportsApi.getReports(student_no)
        }.mapLeft {
            it.toNetworkError()
        }
    }

    override suspend fun addWeeklyReports(report: Report,student_no: String): Either<NetworkError, Boolean> {
        return Either.catch {
            reportsApi.addWeeklyReport(report, student_no)
        }.mapLeft {
            it.toNetworkError()
        }
    }

    override suspend fun getReportsInformation(report_id: String): Either<NetworkError, Report> {
        return Either.catch {
            reportsApi.getReportsInformation(report_id)
        }.mapLeft {
            it.toNetworkError()
        }
    }

    override suspend fun deleteWeeklyReport(report_id: String): Either<NetworkError, Boolean> {
        return Either.catch {
            reportsApi.deleteWeeklyReport(report_id)
        }.mapLeft {
            it.toNetworkError()
        }
    }

    override suspend fun updateWeeklyReport(report: Report): Either<NetworkError, Boolean> {
        return Either.catch {
            reportsApi.updateWeeklyReport(report)
        }.mapLeft {
            it.toNetworkError()
        }
    }
}