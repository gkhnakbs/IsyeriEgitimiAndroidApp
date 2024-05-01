package com.gokhanakbas.isyeriegitimiandroidapp.data.repository

import arrow.core.Either
import com.gokhanakbas.isyeriegitimiandroidapp.data.mapper.toNetworkError
import com.gokhanakbas.isyeriegitimiandroidapp.data.remote.ReportsApi
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.NetworkError
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Report
import com.gokhanakbas.isyeriegitimiandroidapp.domain.repository.ReportsRepository
import javax.inject.Inject

class ReportsRepositoryImpl @Inject constructor(private val reportsApi: ReportsApi) :
    ReportsRepository {

    override suspend fun getReports(student_no: String): Either<NetworkError, List<Report>> {
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
}