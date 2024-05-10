package com.gokhanakbas.isyeriegitimiandroidapp.presentation.pages.advertsPage

import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Advert
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Firm

data class AdvertPageState(
    var isLoading: Boolean = false,
    var applyResult:String="Failed",
    var advert: Advert = Advert(
        "",
        "",
        "","","",
        Firm("", "", "", "", "", "", "", "",""),
        emptyList()
    ,""),
    var error : String? = null
)