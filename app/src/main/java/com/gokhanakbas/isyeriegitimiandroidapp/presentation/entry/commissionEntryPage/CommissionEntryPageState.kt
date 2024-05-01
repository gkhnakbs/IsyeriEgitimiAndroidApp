package com.gokhanakbas.isyeriegitimiandroidapp.presentation.entry.commissionEntryPage

import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Commission

data class CommissionEntryPageState(
    var isLoading: Boolean = false,
    var commissionList : List<Commission> = emptyList(),
    var error : String? =null
)