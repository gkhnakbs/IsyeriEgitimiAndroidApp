package com.gokhanakbas.isyeriegitimiandroidapp.data.repository

import arrow.core.Either
import com.gokhanakbas.isyeriegitimiandroidapp.data.mapper.toNetworkError
import com.gokhanakbas.isyeriegitimiandroidapp.data.remote.PostsApi
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.NetworkError
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Post
import com.gokhanakbas.isyeriegitimiandroidapp.domain.repository.PostsRepository
import javax.inject.Inject


class PostsRepositoryImpl @Inject constructor(private val postsApi: PostsApi): PostsRepository {

    override suspend fun getPosts(): Either<NetworkError, List<Post>> {
        return  Either.catch {
            postsApi.getPosts()
        }.mapLeft {
            it.toNetworkError()
        }
    }

    override suspend fun getFavouritePost(student_no: String): Either<NetworkError,List<Post>> {
        return  Either.catch {
            postsApi.getFavouritePosts(student_no)
        }.mapLeft {
            it.toNetworkError()
        }
    }

    override suspend fun addPostFavourite(student_no: String, post_id: String) : Either<NetworkError,Boolean> {
        return Either.catch {
            postsApi.addPostFavourite("",Post("","","","","",post_isFavourite = false))
        }.mapLeft {
            it.toNetworkError()
        }
    }
}