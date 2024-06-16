package com.gokhanakbas.isyeriegitimiandroidapp.presentation.util.components.postComponent

import androidx.compose.ui.res.stringResource
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.Either
import com.gokhanakbas.isyeriegitimiandroidapp.R
import com.gokhanakbas.isyeriegitimiandroidapp.domain.repository.PostsRepository
import com.gokhanakbas.isyeriegitimiandroidapp.presentation.util.sendEvent
import com.gokhanakbas.isyeriegitimiandroidapp.util.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class PostComponentViewModel @Inject constructor (private val postsRepository: PostsRepository) : ViewModel() {

    private val _state = MutableStateFlow(PostComponentState())
    val state = _state.asStateFlow()


    suspend fun addToFavourite(student_no : String,post_id : String) : Deferred<Boolean> {

        val resultState= viewModelScope.async {

            val result= postsRepository.addPostToFavourite(student_no,post_id)

            when(result){
                is Either.Left -> {
                    sendEvent(Event.Toast(result.value.error.message))
                    _state.value = _state.value.copy(successfullySaved = false, error = result.value.error.message)
                    false
                }
                is Either.Right -> {
                    if(result.value){
                        sendEvent(Event.Toast("Başarılı"))
                        _state.value = _state.value.copy(successfullySaved = true)
                        true
                    }
                    else{

                        sendEvent(Event.Toast("Başarısız"))
                        _state.value = _state.value.copy(successfullySaved = false)
                        false
                    }
                }
            }

        }
        return resultState
    }

    suspend fun removeFromFavourite(student_no : String,post_id : String) : Deferred<Boolean> {

        val resultState= viewModelScope.async {

            val result= postsRepository.removeFromFavourite(student_no = student_no,post_id = post_id)

            when(result){
                is Either.Left -> {
                    sendEvent(Event.Toast(result.value.error.message))
                    _state.value = _state.value.copy(succesfullyRemoved = false, error = result.value.error.message)
                    false
                }
                is Either.Right -> {
                    if(result.value){
                        sendEvent(Event.Toast("Başarılı"))
                        _state.value = _state.value.copy(succesfullyRemoved = true)
                        true
                    }
                    else{
                        sendEvent(Event.Toast("Başarısız"))
                        _state.value = _state.value.copy(succesfullyRemoved = false)
                        false
                    }
                }
            }

        }

        return resultState
    }


}