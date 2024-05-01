package com.gokhanakbas.isyeriegitimiandroidapp.presentation.pages.firmsPage

import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Firm

data class FirmPageState(

    val isLoading :Boolean=false,
    val firm : Firm =Firm("0","","","","","","",""),
    val error : String? = null

)
