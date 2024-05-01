package com.gokhanakbas.isyeriegitimiandroidapp.domain.repository

import arrow.core.Either
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.NetworkError
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Post

interface PostsRepository {

    suspend fun getPosts() :  Either<NetworkError,List<Post>>

    suspend fun getFavouritePost(student_no : String) : Either<NetworkError,List<Post>>

    suspend fun addPostFavourite(student_no : String,post_id:String) : Either<NetworkError,Boolean>

}