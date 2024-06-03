package com.gokhanakbas.isyeriegitimiandroidapp.presentation.commission.commissionInfoEditPage

import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Commission

data class CommissionInfoEditPageState(
    var isLoading : Boolean = false,
    var commission: Commission=Commission("","","","",""),
    var error : String?=null
)