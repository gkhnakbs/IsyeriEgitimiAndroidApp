package com.gokhanakbas.isyeriegitimiandroidapp.presentation.pages.formsPage

import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Form

data class FormPageState(
    val isLoading : Boolean = false,
    val form : List<Form> = emptyList(),
    val error : String? = null
)