package com.gokhanakbas.isyeriegitimiandroidapp.presentation.firm

import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Firm

data class FirmMainPageState(
    var isLoading : Boolean = false,
    var firm_object :Firm=Firm("","","","","","","",""),
    var error:String?=null
)