package com.gokhanakbas.isyeriegitimiandroidapp.domain.repository

import arrow.core.Either
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Commission
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Form
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.NetworkError

interface CommissionsRepository {

    suspend fun getCommissionInformation(commission_id:String) : Either<NetworkError, Commission> // virgülün solu hata alınırsa ne olacağı sağı hata alınmazsa ne olacağı

    suspend fun getCommissions() : Either<NetworkError, List<Commission>>

    suspend fun loginCommission(commission_no: String,commission_password: String) : Either<NetworkError,Commission>
}