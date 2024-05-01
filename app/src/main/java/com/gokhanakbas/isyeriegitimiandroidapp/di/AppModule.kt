package com.gokhanakbas.isyeriegitimiandroidapp.di

import android.app.Application
import com.gokhanakbas.isyeriegitimiandroidapp.data.manager.LocalUserManagerImpl
import com.gokhanakbas.isyeriegitimiandroidapp.data.remote.DatabaseConnection
import com.gokhanakbas.isyeriegitimiandroidapp.domain.manager.LocalUserManager
import com.gokhanakbas.isyeriegitimiandroidapp.domain.usecases.ReadUserInfo
import com.gokhanakbas.isyeriegitimiandroidapp.domain.usecases.SaveUserInfo
import com.gokhanakbas.isyeriegitimiandroidapp.domain.usecases.UserInfoUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLocalUserManager(
        application: Application
    ): LocalUserManager = LocalUserManagerImpl(application)

    //Burada LocalUserManager adlı interface in class ı olan LocalUserManagerImpl dan
    // bir instance oluşturur ve bütün uygulama yaşam döngüsü boyunca(Singleton sayesinde) kullanılır.

    @Provides
    @Singleton
    fun provideUserInfoUseCases(
        localUserManager: LocalUserManager
    ): UserInfoUseCases = UserInfoUseCases(
        ReadUserInfo(localUserManager),
        SaveUserInfo(localUserManager))

}