package com.gokhanakbas.isyeriegitimiandroidapp.domain.model

import androidx.compose.runtime.Immutable
import java.io.Serializable
import java.util.UUID

@Immutable
data class Lecturer(
    var lecturer_id: String,
    var lecturer_name: String,
    var lecturer_faculty: String,
    var lecturer_department: String,
    var lecturer_specificField: String,
    var lecturer_degree: String,
    var lecturer_mail: String,
    var lecturer_info: String,
    var lecturer_password:String,
    var id: String = UUID.randomUUID().toString()
) : Serializable