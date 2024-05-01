package com.gokhanakbas.isyeriegitimiandroidapp.domain.repository

import androidx.collection.MutableObjectList
import arrow.core.Either
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.NetworkError
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Skill
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Student

interface StudentsRepository {

    suspend fun getStudents(): Either<NetworkError, List<Student>>   // virgülün solu hata alınırsa ne olacağı sağı hata alınmazsa ne olacağı

    suspend fun getStudentInformation(student_no:String): Either<NetworkError, Student>

    suspend fun login_student(student_no:String,student_password:String): Either<NetworkError,Boolean>

    suspend fun getSkills(student_no:String) : Either<NetworkError, MutableList<Skill>>

    suspend fun saveStudentsInformation(student:Student) : Either<NetworkError,Boolean>

    suspend fun saveStudentsSkills(student_no: String,skillList: MutableList<Skill>) : Either<NetworkError,Boolean>

}