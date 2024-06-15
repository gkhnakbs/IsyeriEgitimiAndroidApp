package com.gokhanakbas.isyeriegitimiandroidapp.presentation.firm.advertsOfFirmPage

import android.annotation.SuppressLint
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Advert

@SuppressLint("MutableCollectionMutableState")
data class AdvertsOfFirmPageState (
    var isLoading : Boolean = false,
    var advertList : MutableList<Advert> =  mutableListOf(),
    var successfullyDeleted : Boolean=false,
    var error : String?=null
    )