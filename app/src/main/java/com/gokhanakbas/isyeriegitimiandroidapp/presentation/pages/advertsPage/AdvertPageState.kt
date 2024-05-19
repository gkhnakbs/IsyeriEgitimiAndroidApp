package com.gokhanakbas.isyeriegitimiandroidapp.presentation.pages.advertsPage

import android.annotation.SuppressLint
import androidx.compose.runtime.mutableStateOf
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Advert
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Firm
@SuppressLint("MutableCollectionMutableState")
data class AdvertPageState(
    var isLoading: Boolean = false,
    var applyResult:String="Failed",
     var advert: Advert = Advert(
        "",
        "",
        "","","",
        "",
        "",
        null,
        mutableStateOf(arrayListOf())
    ,""),
    var error : String? = null
)