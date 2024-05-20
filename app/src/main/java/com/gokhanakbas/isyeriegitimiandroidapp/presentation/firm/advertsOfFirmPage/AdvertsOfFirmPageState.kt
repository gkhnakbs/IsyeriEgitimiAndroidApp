package com.gokhanakbas.isyeriegitimiandroidapp.presentation.firm.advertsOfFirmPage

import android.annotation.SuppressLint
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Advert

@SuppressLint("MutableCollectionMutableState")
data class AdvertsOfFirmPageState (
    var isLoading : Boolean = false,
    var advertList : MutableState<ArrayList<Advert>> =  mutableStateOf(arrayListOf()),
    var successfullyDeleted : Boolean=false,
    var error : String?=null
    )