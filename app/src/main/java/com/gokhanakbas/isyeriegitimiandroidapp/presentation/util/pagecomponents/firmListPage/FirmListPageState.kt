package com.gokhanakbas.isyeriegitimiandroidapp.presentation.util.pagecomponents.firmListPage

import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Firm

data class FirmListPageState(
    var isLoading : Boolean = false,
    var firmList : List<Firm> = emptyList(),
    var error : String? = null
)
