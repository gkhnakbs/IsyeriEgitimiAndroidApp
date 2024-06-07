package com.gokhanakbas.isyeriegitimiandroidapp.presentation.pages.firmsPage

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Advert
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Firm

data class FirmPageState(

    val isLoading :Boolean=false,
    var firm : Firm =Firm("0","","","","","","","",""),
    var firmAdverts : MutableState<ArrayList<Advert>> = mutableStateOf(arrayListOf()),
    val error : String? = null

)
