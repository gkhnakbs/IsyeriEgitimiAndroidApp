package com.gokhanakbas.isyeriegitimiandroidapp.presentation.util.pagecomponents.surveyListPage

import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Survey

data class SurveyListPageState(
    var isLoading : Boolean = false,
    var surveyList : List<Survey> = emptyList(),
    var error : String? = null
)