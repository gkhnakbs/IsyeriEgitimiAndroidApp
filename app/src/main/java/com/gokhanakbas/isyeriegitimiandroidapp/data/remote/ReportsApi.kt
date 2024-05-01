package com.gokhanakbas.isyeriegitimiandroidapp.data.remote

import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Report
import javax.inject.Inject

class ReportsApi @Inject constructor(private val databaseConnection: DatabaseConnection) {

    val connection = databaseConnection.connection

    fun getReports(student_no: String): List<Report> {
        val report_list = arrayListOf<Report>()
        val statement = connection.createStatement()
        val result =
            statement.executeQuery("select * from haftalik_rapor where ogrenci_no=$student_no")
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
        var report_object = Report("", "", "","")
        val statement = connection.createStatement()
        val result =
            statement.executeQuery("select * from haftalik_rapor where rapor_id=$report_id")
        while (result.next()) {
            report_object.report_description = result.getString("rapor_aciklama")
            report_object.report_date = result.getString("rapor_tarih")
            report_object.report_studentNo=result.getBigDecimal("ogrenci_no").toString()
        }
        return report_object
    }


    fun addWeeklyReport(report: Report, student_no: String): Boolean {
        val statement = connection.createStatement()
        val result = statement.executeUpdate("Insert into haftalik_rapor(rapor_aciklama,ogrenci_no) VALUES('${report.report_description}',$student_no)")
        return result > 0
    }


}