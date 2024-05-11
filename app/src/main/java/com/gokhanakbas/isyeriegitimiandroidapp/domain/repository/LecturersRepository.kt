package com.gokhanakbas.isyeriegitimiandroidapp.domain.repository

import arrow.core.Either
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Group
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Lecturer
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.NetworkError
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Survey

interface LecturersRepository {
    suspend fun getLecturers(): Either<NetworkError, List<Lecturer>>   // virgülün solu hata alınırsa ne olacağı sağı hata alınmazsa ne olacağı

    suspend fun getLecturerInformation(lecturer_id:String): Either<NetworkError, Lecturer>  //Burada izleyicinin bilgileri çekilecek

    suspend fun getLecturersGroups(): Either<NetworkError, List<Group>>  // Burada ise izleyicinin grupları çekilecek

    suspend fun saveLecturerInformation(lecturer: Lecturer): Either<NetworkError,Boolean>

    suspend fun saveLecturerPassword(lecturer: Lecturer): Either<NetworkError, Boolean>
}