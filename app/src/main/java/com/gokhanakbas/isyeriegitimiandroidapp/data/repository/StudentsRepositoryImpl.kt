package com.gokhanakbas.isyeriegitimiandroidapp.data.repository

import androidx.collection.MutableObjectList
import arrow.core.Either
import com.gokhanakbas.isyeriegitimiandroidapp.data.mapper.toNetworkError
import com.gokhanakbas.isyeriegitimiandroidapp.data.remote.StudentsApi
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.NetworkError
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Skill
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Student
import com.gokhanakbas.isyeriegitimiandroidapp.domain.repository.StudentsRepository
import javax.inject.Inject

class StudentsRepositoryImpl @Inject constructor(private val studentsApi: StudentsApi) : StudentsRepository {

    //Implementation da gerçek işlemler yazılır.

    override suspend fun getStudents(): Either<NetworkError, List<Student>> {
        // tek yol bu şekilde suspend fun çalıştırmak benim için
        return Either.catch {
            studentsApi.getStudents()
        }.mapLeft { it.toNetworkError() }
    }

    override suspend fun getStudentInformation(student_no: String): Either<NetworkError, Student> {
        return Either.catch {
            studentsApi.getStudentsInformation(student_no)
        }.mapLeft { it.toNetworkError() }
    }

    override suspend fun login_student(student_no:String,student_password:String): Either<NetworkError, Boolean> {
        return Either.catch {
            studentsApi.login_student(student_no,student_password)
        }.mapLeft {
            it.toNetworkError()
        }
    }

    override suspend fun getSkills(student_no:String): Either<NetworkError, MutableList<Skill>> {
        return Either.catch {
            studentsApi.getSkills(student_no)
        }.mapLeft {
            it.toNetworkError()
        }
    }

    override suspend fun saveStudentsInformation(student: Student): Either<NetworkError, Boolean> {
        return Either.catch {
            studentsApi.saveStudentInformation(student)
        }.mapLeft {
            it.toNetworkError()
        }
    }
    override suspend fun saveStudentsSkills(student_no: String,skillList: MutableList<Skill>): Either<NetworkError, Boolean> {
        return Either.catch {
            studentsApi.saveStudentSkills(student_no,skillList)
        }.mapLeft {
            it.toNetworkError()
        }
    }

}