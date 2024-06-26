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

    override suspend fun login_lecturer(
        lecturer_id: String,
        lecturer_password: String
    ): Either<NetworkError, Lecturer> {
        return Either.catch {
            lecturerApi.loginLecturer(lecturer_id = lecturer_id,lecturer_password=lecturer_password)
        }.mapLeft {
            it.toNetworkError()
        }
    }

    override suspend fun getLecturersGroups(lecturer_id: String): Either<NetworkError, List<Group>> {
        return Either.catch {
            lecturerApi.getLecturerGroups(lecturer_id)
        }.mapLeft {
            it.toNetworkError()
        }
    }

    override suspend fun saveLecturerInformation(lecturer: Lecturer): Either<NetworkError, Boolean> {
        return Either.catch {
            lecturerApi.saveLecturerInformation(lecturer)
        }.mapLeft {
            it.toNetworkError()
        }
    }

    override suspend fun saveLecturerPassword(lecturer: Lecturer): Either<NetworkError, Boolean> {
        return Either.catch {
            lecturerApi.saveLecturerPassword(lecturer)
        }.mapLeft {
            it.toNetworkError()
        }
    }
}