package com.gokhanakbas.isyeriegitimiandroidapp.presentation.util.pagecomponents.appliedAdvertsListPage

import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Advert

data class AppliedAdvertsListPageState(
    var isLoading: Boolean = false,
    var deletedSuccessfully : Boolean=false,
    var advert_list : List<Advert> = emptyList(),
    var error: String?=null
)