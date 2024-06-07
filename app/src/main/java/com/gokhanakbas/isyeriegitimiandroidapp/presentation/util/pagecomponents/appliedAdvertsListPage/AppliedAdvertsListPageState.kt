package com.gokhanakbas.isyeriegitimiandroidapp.presentation.util.pagecomponents.appliedAdvertsListPage

import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Advert
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.AppliedAdvert

data class AppliedAdvertsListPageState(
    var isLoading: Boolean = false,
    var deletedSuccessfully : Boolean=false,
    var advert_list : List<AppliedAdvert> = emptyList(),
    var error: String?=null
)