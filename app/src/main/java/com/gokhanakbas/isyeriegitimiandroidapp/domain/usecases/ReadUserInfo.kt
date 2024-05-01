package com.gokhanakbas.isyeriegitimiandroidapp.domain.usecases

import com.gokhanakbas.isyeriegitimiandroidapp.domain.manager.LocalUserManager
import kotlinx.coroutines.flow.Flow

class ReadUserInfo(
    private val localUserManager: LocalUserManager
) {

    suspend fun getMail(): Flow<String> {
        return localUserManager.readUserEntryMail()
    }

    suspend fun getPassword(): Flow<String> {
        return localUserManager.readUserEntryMail()
    }

}