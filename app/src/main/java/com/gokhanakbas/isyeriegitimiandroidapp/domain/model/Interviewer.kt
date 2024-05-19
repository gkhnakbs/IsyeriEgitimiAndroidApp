package com.gokhanakbas.isyeriegitimiandroidapp.domain.model

import androidx.compose.runtime.Immutable
import java.util.UUID

@Immutable
data class Interviewer (
    val interviewer_id : String,
    val interviewer_student_id : String,
    val interviewer_student_name : String,
    var interviewed_date : String,
    var interviewed_advert_id :String,
    var interview_state: String,
    val id  :String = UUID.randomUUID().toString()
)