package com.gokhanakbas.isyeriegitimiandroidapp.domain.model

import androidx.collection.MutableObjectList
import java.util.UUID

data class Student(
    var student_no: String,
    var student_name: String,
    var student_age: String,
    var student_faculty: String,
    var student_department: String,
    var student_ID : String,
    var student_classLevel : String,
    var student_AGNO: String,
    var student_info: String,
    var student_address: String,
    var student_phone: String,
    var student_mail: String,
    var student_workPlace: Firm,
    var student_password : String,
    var student_skillList: MutableList<Skill>,
    var id: String = UUID.randomUUID().toString()
)