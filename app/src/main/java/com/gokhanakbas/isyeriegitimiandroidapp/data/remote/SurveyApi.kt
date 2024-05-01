package com.gokhanakbas.isyeriegitimiandroidapp.data.remote

import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Survey
import javax.inject.Inject

class SurveyApi @Inject constructor(private val databaseConnection: DatabaseConnection) {

    val connection=databaseConnection.connection

    fun getStudentsSurveys() : List<Survey>{
        val surveyList = arrayListOf<Survey>()
        val statement = connection.createStatement()
        val result = statement.executeQuery("select * from anket")
        while (result.next()) {
            surveyList.add(
                Survey(
                    result.getBigDecimal("anket_id").toInt(),
                    result.getString("anket_baslik"),
                    result.getString("anket_aciklama")
                )
            )
        }
        return surveyList
    }

    fun getFirmsSurveys() : List<Survey>{
        val surveyList = arrayListOf<Survey>()
        val statement = connection.createStatement()
        val result = statement.executeQuery("select * from anket")
        while (result.next()) {
            surveyList.add(
                Survey(
                    result.getBigDecimal("anket_id").toInt(),
                    result.getString("anket_baslik"),
                    result.getString("anket_aciklama")
                )
            )
        }
        return surveyList
    }

    fun getLecturerSurveys() : List<Survey>{
        val surveyList = arrayListOf<Survey>()
        val statement = connection.createStatement()
        val result = statement.executeQuery("select * from anket")
        while (result.next()) {
            surveyList.add(
                Survey(
                    result.getBigDecimal("anket_id").toInt(),
                    result.getString("anket_baslik"),
                    result.getString("anket_aciklama")
                )
            )
        }
        return surveyList
    }
}