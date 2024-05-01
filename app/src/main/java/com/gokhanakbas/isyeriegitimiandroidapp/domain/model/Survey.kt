package com.gokhanakbas.isyeriegitimiandroidapp.domain.model

import androidx.compose.runtime.Immutable
import java.util.UUID

@Immutable
data class Survey(
    var survey_Id: Int,
    var surveyName: String,
    var surveyDescription : String,
    var id: String = UUID.randomUUID().toString()
) {
}