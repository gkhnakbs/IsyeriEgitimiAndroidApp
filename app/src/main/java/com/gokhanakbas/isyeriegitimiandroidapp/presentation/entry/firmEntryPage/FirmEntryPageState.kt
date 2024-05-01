package com.gokhanakbas.isyeriegitimiandroidapp.presentation.entry.firmEntryPage

import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Firm

data class FirmEntryPageState(
    var isLoading : Boolean=false,
    var firmList : List<Firm> = emptyList(),
    var error : String? =null
)