package com.gokhanakbas.isyeriegitimiandroidapp.domain.usecases

import com.gokhanakbas.isyeriegitimiandroidapp.domain.manager.LocalUserManager

class SaveUserInfo(
    private val localUserManager: LocalUserManager
) {

    suspend operator fun invoke(user_mail:String,user_password:String){
        localUserManager.saveUserEntryInfo(user_mail = user_mail, user_password = user_password)
    }
}