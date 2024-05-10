package com.gokhanakbas.isyeriegitimiandroidapp.domain.repository

import arrow.core.Either
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Advert
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Firm
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Form
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.NetworkError
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Student
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Survey

interface FirmsRepository {

    suspend fun getFirms(): Either<NetworkError, List<Firm>>   // virgülün solu hata alınırsa ne olacağı sağı hata alınmazsa ne olacağı

    suspend fun getFirmInformation(firm_id:String): Either<NetworkError, Firm>

    suspend fun saveFirmInformation(firm:Firm) : Either<NetworkError,Boolean>

    suspend fun getFirmsWorkingStudents(): Either<NetworkError, List<Student>>  //Burada firmanın çalışan öğrencileri çekilecek

}