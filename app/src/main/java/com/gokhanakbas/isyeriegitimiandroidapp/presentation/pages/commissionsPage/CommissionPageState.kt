package com.gokhanakbas.isyeriegitimiandroidapp.presentation.pages.commissionsPage

import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Commission

data class CommissionPageState(
    var isLoading :Boolean=false,
    var commission : Commission = Commission("","","","",""),
    var error : String?=null
)