package com.gokhanakbas.isyeriegitimiandroidapp.presentation.util.pagecomponents.formListPage

import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Form

data class FormListPageState(
    var isLoading : Boolean = false,
    var formList : List<Form> = emptyList(),
    var error : String? = null
)
