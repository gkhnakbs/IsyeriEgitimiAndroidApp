package com.gokhanakbas.isyeriegitimiandroidapp.data.remote

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Report
import java.sql.Date
import javax.inject.Inject

class ReportsApi @Inject constructor(private val databaseConnection: DatabaseConnection) {

    val connection = databaseConnection.connection

    fun getReports(student_no: String): MutableList<Report> {
        val report_list = mutableListOf<Report>()
        val statement = connection.prepareStatement("select * from haftalik_rapor where ogrenci_no= ? ")
        statement.setBigDecimal(1,student_no.toBigDecimal())
        val result = statement.executeQuery()
        while (result.next()) {
            report_list.add(
                Report(
                    report_id = result.getBigDecimal("rapor_id").toString(),
                    report_description = result.getString("rapor_aciklama"),
                    report_date = result.getString("rapor_tarih"),
                    report_studentNo = result.getBigDecimal("ogrenci_no").toString()
                )
            )
        }
        return report_list
    }

    fun getReportsInformation(report_id: String): Report {
        val report_object = Report(report_id, "", "","")
        val statement = connection.prepareStatement("select * from haftalik_rapor where rapor_id= ? ")
        statement.setBigDecimal(1,report_id.toBigDecimal())
        val result =statement.executeQuery()
        while (result.next()) {
            report_object.report_description = result.getString("rapor_aciklama")
            report_object.report_date = result.getString("rapor_tarih")
            report_object.report_studentNo=result.getBigDecimal("ogrenci_no").toString()
        }
        return report_object
    }


    fun addWeeklyReport(report: Report, student_no: String): Boolean {
        val statement = connection.prepareStatement("Insert into haftalik_rapor(rapor_aciklama,ogrenci_no) VALUES(?,?)")
        statement.setString(1,report.report_description)
        statement.setBigDecimal(2,student_no.toBigDecimal())
        val result = statement.executeUpdate()
        return result > 0
    }

    fun updateWeeklyReport(report: Report): Boolean {
        val statement = connection.prepareStatement("Update haftalik_rapor set rapor_aciklama= ? , rapor_tarih= ? where rapor_id= ? ")
        statement.setString(1,report.report_description)
        statement.setString(2,report.report_date)
        statement.setBigDecimal(3,report.report_id.toBigDecimal())
        val result=statement.executeUpdate()
        return result > 0
    }

    fun deleteWeeklyReport(report_id: String): Boolean {
        val statement = connection.prepareStatement("delete from haftalik_rapor where rapor_id= ? ")
        statement.setBigDecimal(1,report_id.toBigDecimal())
        val result = statement.executeUpdate()
        return result > 0
    }

}