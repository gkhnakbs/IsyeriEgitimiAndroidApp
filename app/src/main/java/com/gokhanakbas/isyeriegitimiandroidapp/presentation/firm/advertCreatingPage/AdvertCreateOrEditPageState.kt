package com.gokhanakbas.isyeriegitimiandroidapp.presentation.firm.advertCreatingPage

import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Advert

data class AdvertCreateOrEditPageState(
    var isLoading : Boolean = false,
    var succesfullyCreated :Boolean = false,
    var succesfullyUpdated : Boolean=false,
    var advert : Advert? = null,
    var error: String?=null
)