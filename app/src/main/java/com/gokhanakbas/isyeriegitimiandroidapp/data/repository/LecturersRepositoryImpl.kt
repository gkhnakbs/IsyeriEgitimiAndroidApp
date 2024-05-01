package com.gokhanakbas.isyeriegitimiandroidapp.data.repository

import arrow.core.Either
import com.gokhanakbas.isyeriegitimiandroidapp.data.mapper.toNetworkError
import com.gokhanakbas.isyeriegitimiandroidapp.data.remote.LecturerApi
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Group
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Lecturer
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.NetworkError
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Survey
import com.gokhanakbas.isyeriegitimiandroidapp.domain.repository.LecturersRepository
import javax.inject.Inject

class LecturersRepositoryImpl @Inject constructor(private val lecturerApi: LecturerApi): LecturersRepository {

    //Implementation da gerçek işlemler yazılır.

    override suspend fun getLecturers(): Either<NetworkError, List<Lecturer>> {
        return Either.catch {
            lecturerApi.getLecturers()
        }.mapLeft {
            it.toNetworkError()
        }
    }

    override suspend fun getLecturerInformation(lecturer_id:String): Either<NetworkError, Lecturer> {
        return Either.catch {
            lecturerApi.getLecturersInformation(lecturer_id)
        }.mapLeft {
            it.toNetworkError()
        }
    }

    override suspend fun getLecturersGroups(): Either<NetworkError, List<Group>> {
        return Either.catch {
            lecturerApi.getLecturerGroups()
        }.mapLeft {
            it.toNetworkError()
        }
    }
}