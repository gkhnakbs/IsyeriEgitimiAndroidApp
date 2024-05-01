package com.gokhanakbas.isyeriegitimiandroidapp.data.repository

import arrow.core.Either
import com.gokhanakbas.isyeriegitimiandroidapp.data.mapper.toNetworkError
import com.gokhanakbas.isyeriegitimiandroidapp.data.remote.AdvertsApi
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Advert
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.NetworkError
import com.gokhanakbas.isyeriegitimiandroidapp.domain.repository.AdvertsRepository
import javax.inject.Inject

class AdvertsRepositoryImpl @Inject constructor(private val advertsApi: AdvertsApi) :
    AdvertsRepository {
    override suspend fun getAdverts(): Either<NetworkError, List<Advert>> {
        return Either.catch {
            advertsApi.getAdverts()
        }.mapLeft {
            it.toNetworkError()
        }
    }

    override suspend fun getFirmsAdverts(firm_id: String): Either<NetworkError, List<Advert>> {
        return Either.catch {
            advertsApi.getFirmsAdverts(firm_id)
        }.mapLeft {
            it.toNetworkError()
        }
    }

    override suspend fun getAdvertsInformation(advert_id: String): Either<NetworkError, Advert> {
        return Either.catch {
            advertsApi.getAdvertsInformation(advert_id)
        }.mapLeft {
            it.toNetworkError()
        }
    }

    override suspend fun getAppliedAdverts(student_no: String): Either<NetworkError, List<Advert>> {
        return Either.catch {
            advertsApi.getAppliedAdverts(student_no)
        }.mapLeft {
            it.toNetworkError()
        }
    }

    override suspend fun deleteFromAppliedAdvert(
        advert_id: String,
        student_no: String
    ): Either<NetworkError, Boolean> {
        return Either.catch {
            advertsApi.deleteFromAppliedAdvert(advert_id, student_no)
        }.mapLeft {
            it.toNetworkError()
        }
    }

    override suspend fun applyToAdvert(
        advert_id: String,
        student_no: String
    ): Either<NetworkError, String> {
        return Either.catch {
            advertsApi.applyToAdvert(advert_id, student_no)
        }.mapLeft {
            it.toNetworkError()
        }
    }

    override suspend fun createAdvert(
        firm_id: String,
        advert: Advert
    ): Either<NetworkError, Boolean> {
        return Either.catch {
            advertsApi.createAdvert(firm_id, advert)
        }.mapLeft {
            it.toNetworkError()
        }
    }

}