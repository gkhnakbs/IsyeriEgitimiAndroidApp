package com.gokhanakbas.isyeriegitimiandroidapp.presentation.firm.firmMainPageInfo

import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Firm

data class FirmMainPageInfoState(
    var isLoading: Boolean=false,
    var firm  :Firm=Firm("","","","","","","","",""),
    var error  : String?=null
)
