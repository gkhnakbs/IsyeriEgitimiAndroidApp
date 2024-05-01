package com.gokhanakbas.isyeriegitimiandroidapp.data.remote

import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Form
import javax.inject.Inject

class FormsApi @Inject constructor(private val databaseconnection: DatabaseConnection) {

    fun getStudentsForms(): List<Form> {
        val formList = arrayListOf<Form>()
        val connection = databaseconnection.connection
        val statement = connection.createStatement()
        val result = statement.executeQuery("select * from form")
        while (result.next()) {
            formList.add(
                Form(
                    result.getBigDecimal("form_id").toInt(),
                    result.getString("form_baslik"),
                    result.getString("form_aciklama")
                )
            )
        }
        return formList
    }

    fun getCommonForms(): List<Form> {
        val formList = arrayListOf<Form>()
        val connection = databaseconnection.connection
        val statement = connection.createStatement()
        val result = statement.executeQuery("select * from form")
        while (result.next()) {
            formList.add(
                Form(
                    result.getBigDecimal("form_id").toInt(),
                    result.getString("form_baslik"),
                    result.getString("form_aciklama")
                )
            )
        }
        return formList
    }

    fun getFirmForms(): List<Form> {
        val formList = arrayListOf<Form>()
        val connection = databaseconnection.connection
        val statement = connection.createStatement()
        val result = statement.executeQuery("select * from form")
        while (result.next()) {
            formList.add(
                Form(
                    result.getBigDecimal("form_id").toInt(),
                    result.getString("form_baslik"),
                    result.getString("form_aciklama")
                )
            )
        }
        return formList
    }

    fun getLecturerForms(): List<Form> {
        val formList = arrayListOf<Form>()
        val connection = databaseconnection.connection
        val statement = connection.createStatement()
        val result = statement.executeQuery("select * from form")
        while (result.next()) {
            formList.add(
                Form(
                    result.getBigDecimal("form_id").toInt(),
                    result.getString("form_baslik"),
                    result.getString("form_aciklama")
                )
            )
        }
        return formList
    }

    fun getCommissionForms(): List<Form> {
        val formList = arrayListOf<Form>()
        val connection = databaseconnection.connection
        val statement = connection.createStatement()
        val result = statement.executeQuery("select * from form")
        while (result.next()) {
            formList.add(
                Form(
                    result.getBigDecimal("form_id").toInt(),
                    result.getString("form_baslik"),
                    result.getString("form_aciklama")
                )
            )
        }
        return formList
    }
}