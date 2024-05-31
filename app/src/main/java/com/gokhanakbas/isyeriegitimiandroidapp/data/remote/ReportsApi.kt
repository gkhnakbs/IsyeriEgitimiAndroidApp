package com.gokhanakbas.isyeriegitimiandroidapp.data.remote

import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Report
import java.sql.Date
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
        val report_object = Report(report_id, "", "","")
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

    fun updateWeeklyReport(report: Report): Boolean {
        val statement = connection.prepareStatement("Update haftalik_rapor set rapor_aciklama='${report.report_description}' , rapor_tarih='${report.report_date}' where rapor_id='${report.report_id}'")
        val result=statement.executeUpdate()
        return result > 0
    }

    fun deleteWeeklyReport(report_id: String): Boolean {
        val statement = connection.createStatement()
        val result = statement.executeUpdate("delete haftalik_rapor where rapor_id='$report_id'")
        return result > 0
    }

}