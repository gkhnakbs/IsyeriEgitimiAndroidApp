package com.gokhanakbas.isyeriegitimiandroidapp.domain.repository

import arrow.core.Either
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Form
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.NetworkError


interface FormsRepository {

    suspend fun getStudentsForms(): Either<NetworkError, List<Form>>  // Burada öğrencilerin formu çekilecek

    suspend fun getCommonForms(): Either<NetworkError, List<Form>>  // Burada öğrencilerin formu çekilecek

    suspend fun getFirmForms(): Either<NetworkError, List<Form>>  // Burada öğrencilerin formu çekilecek

    suspend fun getLecturerForms() : Either<NetworkError,List<Form>> // Burada komisyonun formu çekilecek

    suspend fun getCommissionForms() : Either<NetworkError,List<Form>> // Burada komisyonun formu çekilecek
}