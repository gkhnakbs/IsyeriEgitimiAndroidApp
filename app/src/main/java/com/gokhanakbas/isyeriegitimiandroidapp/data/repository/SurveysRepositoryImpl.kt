package com.gokhanakbas.isyeriegitimiandroidapp.data.repository

import arrow.core.Either
import com.gokhanakbas.isyeriegitimiandroidapp.data.mapper.toNetworkError
import com.gokhanakbas.isyeriegitimiandroidapp.data.remote.SurveyApi
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.NetworkError
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Survey
import com.gokhanakbas.isyeriegitimiandroidapp.domain.repository.SurveysRepository
import javax.inject.Inject

class SurveysRepositoryImpl @Inject constructor(private val surveysApi : SurveyApi): SurveysRepository {
    override suspend fun getStudentsSurvey(): Either<NetworkError, List<Survey>> {
        return Either.catch {
            surveysApi.getStudentsSurveys()
        }.mapLeft { it.toNetworkError() }
    }

    override suspend fun getFirmsSurvey(): Either<NetworkError, List<Survey>> {
        return Either.catch {
            surveysApi.getFirmsSurveys()
        }.mapLeft { it.toNetworkError() }
    }

    override suspend fun getLecturersSurveys(): Either<NetworkError, List<Survey>> {
        return Either.catch {
            surveysApi.getLecturerSurveys()
        }.mapLeft { it.toNetworkError() }
    }
}