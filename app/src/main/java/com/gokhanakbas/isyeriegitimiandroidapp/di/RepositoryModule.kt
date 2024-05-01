package com.gokhanakbas.isyeriegitimiandroidapp.di

import com.gokhanakbas.isyeriegitimiandroidapp.data.remote.AdvertsApi
import com.gokhanakbas.isyeriegitimiandroidapp.data.remote.CommissionApi
import com.gokhanakbas.isyeriegitimiandroidapp.data.remote.DatabaseConnection
import com.gokhanakbas.isyeriegitimiandroidapp.data.remote.FirmsApi
import com.gokhanakbas.isyeriegitimiandroidapp.data.remote.FormsApi
import com.gokhanakbas.isyeriegitimiandroidapp.data.remote.LecturerApi
import com.gokhanakbas.isyeriegitimiandroidapp.data.remote.PostsApi
import com.gokhanakbas.isyeriegitimiandroidapp.data.remote.ReportsApi
import com.gokhanakbas.isyeriegitimiandroidapp.data.remote.StudentsApi
import com.gokhanakbas.isyeriegitimiandroidapp.data.remote.SurveyApi
import com.gokhanakbas.isyeriegitimiandroidapp.data.repository.AdvertsRepositoryImpl
import com.gokhanakbas.isyeriegitimiandroidapp.data.repository.CommissionsRepositoryImpl
import com.gokhanakbas.isyeriegitimiandroidapp.data.repository.FirmsRepositoryImpl
import com.gokhanakbas.isyeriegitimiandroidapp.data.repository.FormsRepositoryImpl
import com.gokhanakbas.isyeriegitimiandroidapp.data.repository.LecturersRepositoryImpl
import com.gokhanakbas.isyeriegitimiandroidapp.data.repository.PostsRepositoryImpl
import com.gokhanakbas.isyeriegitimiandroidapp.data.repository.ReportsRepositoryImpl
import com.gokhanakbas.isyeriegitimiandroidapp.data.repository.StudentsRepositoryImpl
import com.gokhanakbas.isyeriegitimiandroidapp.data.repository.SurveysRepositoryImpl
import com.gokhanakbas.isyeriegitimiandroidapp.domain.repository.AdvertsRepository
import com.gokhanakbas.isyeriegitimiandroidapp.domain.repository.CommissionsRepository
import com.gokhanakbas.isyeriegitimiandroidapp.domain.repository.FirmsRepository
import com.gokhanakbas.isyeriegitimiandroidapp.domain.repository.FormsRepository
import com.gokhanakbas.isyeriegitimiandroidapp.domain.repository.LecturersRepository
import com.gokhanakbas.isyeriegitimiandroidapp.domain.repository.PostsRepository
import com.gokhanakbas.isyeriegitimiandroidapp.domain.repository.ReportsRepository
import com.gokhanakbas.isyeriegitimiandroidapp.domain.repository.StudentsRepository
import com.gokhanakbas.isyeriegitimiandroidapp.domain.repository.SurveysRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideStudentsRepository() : StudentsRepository{
        return StudentsRepositoryImpl(provideStudentsApi())
    }

    @Singleton
    @Provides
    fun provideFirmsRepository() : FirmsRepository{
        return FirmsRepositoryImpl(provideFirmsApi())
    }

    @Singleton
    @Provides
    fun provideLecturersRepository() : LecturersRepository{
        return LecturersRepositoryImpl(provideLecturersApi())
    }

    @Singleton
    @Provides
    fun provideCommissionsRepository() : CommissionsRepository{
        return CommissionsRepositoryImpl(provideCommissionsApi())
    }

    @Singleton
    @Provides
    fun provideAdvertsRepository() : AdvertsRepository{
        return AdvertsRepositoryImpl(provideAdvertsApi())
    }

    @Singleton
    @Provides
    fun provideFormsRepository() : FormsRepository{
        return FormsRepositoryImpl(provideFormsApi())
    }

    @Singleton
    @Provides
    fun provideSurveysRepository() : SurveysRepository{
        return SurveysRepositoryImpl(provideSurveysApi())
    }

    @Singleton
    @Provides
    fun providePostsRepository() : PostsRepository{
        return PostsRepositoryImpl(providePostsApi())
    }

    @Singleton
    @Provides
    fun provideReportsRepository() : ReportsRepository{
        return ReportsRepositoryImpl(provideReportsApi())
    }

    @Singleton
    @Provides
    fun provideDatabase() : DatabaseConnection{
        return DatabaseConnection()
    }

    @Singleton
    @Provides
    fun provideStudentsApi() : StudentsApi{
        return StudentsApi(provideDatabase())
    }

    @Singleton
    @Provides
    fun provideFirmsApi() : FirmsApi{
        return FirmsApi(provideDatabase())
    }

    @Singleton
    @Provides
    fun provideLecturersApi() : LecturerApi {
        return LecturerApi(provideDatabase())
    }

    @Singleton
    @Provides
    fun provideCommissionsApi() : CommissionApi{
        return CommissionApi(provideDatabase())
    }

    @Singleton
    @Provides
    fun provideFormsApi() : FormsApi{
        return FormsApi(provideDatabase())
    }

    @Singleton
    @Provides
    fun provideSurveysApi() : SurveyApi{
        return SurveyApi(provideDatabase())
    }

    @Singleton
    @Provides
    fun provideAdvertsApi() : AdvertsApi{
        return AdvertsApi(provideDatabase())
    }

    @Singleton
    @Provides
    fun providePostsApi() : PostsApi{
        return PostsApi(provideDatabase())
    }

    @Singleton
    @Provides
    fun provideReportsApi() : ReportsApi{
        return ReportsApi(provideDatabase())
    }
}