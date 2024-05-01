package com.gokhanakbas.isyeriegitimiandroidapp.domain.repository

import arrow.core.Either
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Commission
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Form
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.NetworkError

interface CommissionsRepository {

    suspend fun getCommissionInformation() : Either<NetworkError, Commission> // virgülün solu hata alınırsa ne olacağı sağı hata alınmazsa ne olacağı

    suspend fun getCommissions() : Either<NetworkError, List<Commission>>
}