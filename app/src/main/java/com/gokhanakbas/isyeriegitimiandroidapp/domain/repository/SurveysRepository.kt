package com.gokhanakbas.isyeriegitimiandroidapp.domain.repository

import arrow.core.Either
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.NetworkError
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Survey

interface SurveysRepository {

    suspend fun getStudentsSurvey(): Either<NetworkError, List<Survey>> // Burada öğrencilerin anketi çekilecek

    suspend fun getFirmsSurvey(): Either<NetworkError, List<Survey>> // Burada firmanın anketi çekilecek

    suspend fun getLecturersSurveys() : Either<NetworkError, List<Survey>>  //Burada izleyicinin anketi çekilecek
}