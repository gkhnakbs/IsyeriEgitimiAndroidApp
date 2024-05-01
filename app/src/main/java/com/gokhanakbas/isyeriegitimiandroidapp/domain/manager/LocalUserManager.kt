package com.gokhanakbas.isyeriegitimiandroidapp.domain.manager

import kotlinx.coroutines.flow.Flow

interface LocalUserManager {

    suspend fun saveUserEntryInfo(user_mail:String,user_password:String)

    suspend fun readUserEntryMailAndPassword() : Flow<String>

    suspend fun readUserEntryMail() : Flow<String>

    suspend fun readUserEntryPassword() : Flow<String>
}