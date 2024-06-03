package com.gokhanakbas.isyeriegitimiandroidapp.presentation.commission.commissionMainPage

import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Commission

data class CommissionMainPageState(
    var isLoading : Boolean=false,
    var commission : Commission=Commission("","","","",""),
    var error:String?=null
)