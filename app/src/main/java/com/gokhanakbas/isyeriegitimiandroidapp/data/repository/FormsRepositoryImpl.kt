package com.gokhanakbas.isyeriegitimiandroidapp.data.repository

import arrow.core.Either
import com.gokhanakbas.isyeriegitimiandroidapp.data.mapper.toNetworkError
import com.gokhanakbas.isyeriegitimiandroidapp.data.remote.FormsApi
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Form
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.NetworkError
import com.gokhanakbas.isyeriegitimiandroidapp.domain.repository.FormsRepository
import javax.inject.Inject

class FormsRepositoryImpl @Inject constructor(private val formsApi : FormsApi):FormsRepository {

    override suspend fun getStudentsForms(): Either<NetworkError, List<Form>> {
        return Either.catch {
            formsApi.getStudentsForms()
        }.mapLeft {
            it.toNetworkError()
        }
    }

    override suspend fun getCommonForms(): Either<NetworkError, List<Form>> {
        return Either.catch {
            formsApi.getCommonForms()
        }.mapLeft {
            it.toNetworkError()
        }
    }

    override suspend fun getFirmForms(): Either<NetworkError, List<Form>> {
        return Either.catch {
            formsApi.getFirmForms()
        }.mapLeft {
            it.toNetworkError()
        }
    }

    override suspend fun getLecturerForms(): Either<NetworkError, List<Form>> {
        return Either.catch {
            formsApi.getLecturerForms()
        }.mapLeft {
            it.toNetworkError()
        }
    }

    override suspend fun getCommissionForms(): Either<NetworkError, List<Form>> {
        return Either.catch {
            formsApi.getCommissionForms()
        }.mapLeft {
            it.toNetworkError()
        }
    }

}