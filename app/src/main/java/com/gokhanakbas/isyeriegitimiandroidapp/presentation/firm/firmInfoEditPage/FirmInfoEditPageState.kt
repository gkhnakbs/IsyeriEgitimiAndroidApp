package com.gokhanakbas.isyeriegitimiandroidapp.presentation.firm.firmInfoEditPage

import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Firm

data class FirmInfoEditPageState(
    var isLoading:Boolean=false,
    var firm:Firm=Firm("","","","","","","",""),
    var error : String?=null

)