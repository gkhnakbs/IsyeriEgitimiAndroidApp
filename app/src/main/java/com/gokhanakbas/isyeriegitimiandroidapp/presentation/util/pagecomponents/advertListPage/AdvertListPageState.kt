package com.gokhanakbas.isyeriegitimiandroidapp.presentation.util.pagecomponents.advertListPage

import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Advert

data class AdvertListPageState(
    var isLoading : Boolean = false,
    var advert_list : List<Advert> = emptyList(),
    var error : String? = null
)