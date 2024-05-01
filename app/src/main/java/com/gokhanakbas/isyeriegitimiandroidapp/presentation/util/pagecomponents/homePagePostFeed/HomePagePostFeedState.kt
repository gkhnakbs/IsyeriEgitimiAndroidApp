package com.gokhanakbas.isyeriegitimiandroidapp.presentation.util.pagecomponents.homePagePostFeed

import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Post

data class HomePagePostFeedState(
    var isLoading:Boolean=false,
    var post : List<Post> = emptyList(),
    var favouritePostList : List<Post> = emptyList(),
    var error : String? = null
)
