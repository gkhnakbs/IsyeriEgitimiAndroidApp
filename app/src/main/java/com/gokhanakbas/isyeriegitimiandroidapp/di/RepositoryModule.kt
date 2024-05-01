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
    fun provideStudentsRepository(studentsApi: StudentsApi) : StudentsRepository{
        return StudentsRepositoryImpl(studentsApi)
    }

    @Singleton
    @Provides
    fun provideFirmsRepository(firmsApi: FirmsApi) : FirmsRepository{
        return FirmsRepositoryImpl(firmsApi)
    }

    @Singleton
    @Provides
    fun provideLecturersRepository(lecturerApi: LecturerApi) : LecturersRepository{
        return LecturersRepositoryImpl(lecturerApi)
    }

    @Singleton
    @Provides
    fun provideCommissionsRepository(commissionApi: CommissionApi) : CommissionsRepository{
        return CommissionsRepositoryImpl(commissionApi)
    }

    @Singleton
    @Provides
    fun provideAdvertsRepository(advertsApi: AdvertsApi) : AdvertsRepository{
        return AdvertsRepositoryImpl(advertsApi)
    }

    @Singleton
    @Provides
    fun provideFormsRepository(formsApi: FormsApi) : FormsRepository{
        return FormsRepositoryImpl(formsApi)
    }

    @Singleton
    @Provides
    fun provideSurveysRepository(surveyApi: SurveyApi) : SurveysRepository{
        return SurveysRepositoryImpl(surveyApi)
    }

    @Singleton
    @Provides
    fun providePostsRepository(postsApi: PostsApi) : PostsRepository{
        return PostsRepositoryImpl(postsApi)
    }

    @Singleton
    @Provides
    fun provideReportsRepository(reportsApi: ReportsApi) : ReportsRepository{
        return ReportsRepositoryImpl(reportsApi)
    }

    @Singleton
    @Provides
    fun provideDatabase() : DatabaseConnection{
        return DatabaseConnection()
    }

    @Singleton
    @Provides
    fun provideStudentsApi(databaseConnection: DatabaseConnection) : StudentsApi{
        return StudentsApi(databaseConnection)
    }

    @Singleton
    @Provides
    fun provideFirmsApi(databaseConnection: DatabaseConnection) : FirmsApi{
        return FirmsApi(databaseConnection)
    }

    @Singleton
    @Provides
    fun provideLecturersApi(databaseConnection: DatabaseConnection) : LecturerApi {
        return LecturerApi(databaseConnection)
    }

    @Singleton
    @Provides
    fun provideCommissionsApi(databaseConnection: DatabaseConnection) : CommissionApi{
        return CommissionApi(databaseConnection)
    }

    @Singleton
    @Provides
    fun provideFormsApi(databaseConnection: DatabaseConnection) : FormsApi{
        return FormsApi(databaseConnection)
    }

    @Singleton
    @Provides
    fun provideSurveysApi(databaseConnection: DatabaseConnection) : SurveyApi{
        return SurveyApi(databaseConnection)
    }

    @Singleton
    @Provides
    fun provideAdvertsApi(databaseConnection: DatabaseConnection) : AdvertsApi{
        return AdvertsApi(databaseConnection)
    }

    @Singleton
    @Provides
    fun providePostsApi(databaseConnection: DatabaseConnection) : PostsApi{
        return PostsApi(databaseConnection)
    }

    @Singleton
    @Provides
    fun provideReportsApi(databaseConnection: DatabaseConnection) : ReportsApi{
        return ReportsApi(databaseConnection)
    }
}