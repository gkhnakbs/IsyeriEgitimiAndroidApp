package com.gokhanakbas.isyeriegitimiandroidapp.data.repository

import arrow.core.Either
import com.gokhanakbas.isyeriegitimiandroidapp.data.mapper.toNetworkError
import com.gokhanakbas.isyeriegitimiandroidapp.data.remote.FirmsApi
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Firm
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Form
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.NetworkError
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Student
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Survey
import com.gokhanakbas.isyeriegitimiandroidapp.domain.repository.FirmsRepository
import dagger.Provides
import javax.inject.Inject

class FirmsRepositoryImpl @Inject constructor(
    private val firmsApi: FirmsApi
) : FirmsRepository {


    override suspend fun getFirms(): Either<NetworkError, List<Firm>> {
        return Either.catch {
            firmsApi.getFirms()
        }.mapLeft { it.toNetworkError() }
    }

    override suspend fun getFirmInformation(firm_id:String): Either<NetworkError, Firm> {
        return Either.catch {
            firmsApi.getFirmsInformation(firm_id)
        }.mapLeft { it.toNetworkError() }
    }


    override suspend fun getFirmsWorkingStudents(): Either<NetworkError, List<Student>> {
        TODO("Not yet implemented")
    }
}