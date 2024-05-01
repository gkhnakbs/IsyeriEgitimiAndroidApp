package com.gokhanakbas.isyeriegitimiandroidapp.presentation.firm.advertCreatingPage

data class AdvertCreatingPageState(
    var isLoading : Boolean = false,
    var succesfullyCreated :Boolean = false,
    var error: String?=null
)