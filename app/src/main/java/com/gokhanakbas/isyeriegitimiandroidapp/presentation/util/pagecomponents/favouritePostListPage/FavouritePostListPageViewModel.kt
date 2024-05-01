package com.gokhanakbas.isyeriegitimiandroidapp.presentation.util.pagecomponents.favouritePostListPage

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
class FavouritePostListPageViewModel @Inject constructor(private val postsRepository: PostsRepository) : ViewModel() {

    private val _state = MutableStateFlow(FavouritePostListPageState())
    val state = _state.asStateFlow()

    fun getFavouritePosts(student_no: String){

        viewModelScope.launch {
            _state.update {
                it.copy(isLoading = true)
            }
            delay(2000)
            postsRepository.getFavouritePost(student_no)
                .onRight {postList ->
                    _state.update {
                        it.copy(postList=postList)
                    }
                }
                .onLeft { error->
                    _state.update {
                        it.copy(error = error.error.message)
                    }
                    sendEvent(Event.Toast(error.error.message))
                }
            _state.update {
                it.copy(isLoading = false)
            }
        }
    }


    fun removeFromFavouritePosts(student_no:String,post_id:String){
        viewModelScope.launch {
            _state.update {
                it.copy(isLoading = true)
            }
            delay(2000)
            //Posts Api ye favoriden kaldırma gelecek
        }
    }
}




