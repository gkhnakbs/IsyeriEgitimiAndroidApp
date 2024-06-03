package com.gokhanakbas.isyeriegitimiandroidapp.data.repository

import arrow.core.Either
import com.gokhanakbas.isyeriegitimiandroidapp.data.mapper.toNetworkError
import com.gokhanakbas.isyeriegitimiandroidapp.data.remote.CommissionApi
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Commission
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.NetworkError
import com.gokhanakbas.isyeriegitimiandroidapp.domain.repository.CommissionsRepository
import javax.inject.Inject

class CommissionsRepositoryImpl @Inject constructor(private val commissionApi: CommissionApi) :
    CommissionsRepository {

    override suspend fun getCommissionInformation(commission_id: String): Either<NetworkError, Commission> {
        return Either.catch {
            commissionApi.getCommissionsInformation(commission_id)
        }.mapLeft {
            it.toNetworkError()
        }
    }

    override suspend fun getCommissions(): Either<NetworkError, List<Commission>> {
        return Either.catch {
            commissionApi.getCommissions()
        }.mapLeft {
            it.toNetworkError()
        }
    }

    override suspend fun loginCommission(
        commission_no: String,
        commission_password: String
    ): Either<NetworkError, Commission> {
        return Either.catch {
            commissionApi.loginCommission(
                commission_id = commission_no,
                commission_password = commission_password
            )
        }.mapLeft {
            it.toNetworkError()
        }
    }
}