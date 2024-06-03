package com.gokhanakbas.isyeriegitimiandroidapp.presentation.entry.commissionEntryPage

import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Commission

data class CommissionEntryPageState(
    var isLoading: Boolean = false,
    var loginSuccessfullyCommission : Commission? = null,
    var error : String? =null
)