package com.gokhanakbas.isyeriegitimiandroidapp.presentation.util.pagecomponents.homePagePostFeed

import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gokhanakbas.isyeriegitimiandroidapp.domain.repository.PostsRepository
import com.gokhanakbas.isyeriegitimiandroidapp.presentation.util.sendEvent
import com.gokhanakbas.isyeriegitimiandroidapp.util.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomePagePostFeedViewModel @Inject constructor(private val postsRepository: PostsRepository) : ViewModel() {

    val context= LocalContext

    private val _state= MutableStateFlow(HomePagePostFeedState())
    val state=_state.asStateFlow()
    

    suspend fun getPosts(){
        viewModelScope.launch {
            _state.update {
                it.copy(isLoading = true)
            }
            delay(2000)
            postsRepository.getPosts()
                .onRight {postList ->
                    _state.update {
                        it.copy(post = postList)
                    }
                }
                .onLeft {error ->
                    _state.update {
                        it.copy(error=error.error.message)
                    }
                    sendEvent(Event.Toast(error.error.message))
                }
            _state.update {
                it.copy(isLoading = false)
            }
        }
    }

    suspend fun addPostFavourite(student_no : String,post_id:String) : Boolean{
        var addingState=false
        viewModelScope.launch {
            _state.update {
                it.copy(isLoading = true)
            }
            postsRepository.addPostFavourite(student_no,post_id)
                .onRight {
                    addingState=it
                }
                .onLeft { error ->
                    _state.update {
                        it.copy(error = error.error.message)
                    }
                    sendEvent(Event.Toast(error.error.message))
                }
            _state.update {
                it.copy(isLoading = false)
            }

        }

        return addingState
    }

    suspend fun getFavouritePosts(student_no: String){
        viewModelScope.launch{
            _state.update {
                it.copy(isLoading = true)
            }
            delay(2000)
            postsRepository.getFavouritePost(student_no)
                .onRight { favouriteList->
                    _state.update {
                        it.copy(favouritePostList = favouriteList)
                    }
                }
                .onLeft {error->
                    _state.update {
                        it.copy(
                            error = error.error.message
                        )
                    }
                    sendEvent(Event.Toast(error.error.message))
                }
            _state.update {
                it.copy(isLoading = false)
            }
        }
    }

}