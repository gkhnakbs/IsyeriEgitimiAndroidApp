package com.gokhanakbas.isyeriegitimiandroidapp.presentation.util.pagecomponents.favouritePostListPage

import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Post

data class FavouritePostListPageState(
    val isLoading : Boolean=false,
    val postList: List<Post> = emptyList(),
    val error : String?=null
    )