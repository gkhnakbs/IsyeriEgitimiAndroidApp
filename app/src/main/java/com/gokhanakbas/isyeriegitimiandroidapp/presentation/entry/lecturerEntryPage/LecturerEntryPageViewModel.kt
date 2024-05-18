package com.gokhanakbas.isyeriegitimiandroidapp.presentation.entry.lecturerEntryPage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.Either
import com.gokhanakbas.isyeriegitimiandroidapp.domain.repository.LecturersRepository
import com.gokhanakbas.isyeriegitimiandroidapp.presentation.util.sendEvent
import com.gokhanakbas.isyeriegitimiandroidapp.util.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LecturerEntryPageViewModel @Inject constructor(private val lecturersRepository: LecturersRepository) : ViewModel() {

    private val _state= MutableStateFlow(LecturerEntryPageState())
    val state=_state.asStateFlow()

    suspend fun checkLoginCredentials(lecturer_id:String,lecturer_password : String) : Boolean {
        val resultState : Deferred<Boolean> =viewModelScope.async {
            _state.update {
                it.copy(isLoading = true)
            }
            delay(2000)
            val result=lecturersRepository.login_lecturer(lecturer_id = lecturer_id, lecturer_password = lecturer_password)
            _state.update {
                it.copy(isLoading = false)
            }
            when(result){
                is Either.Left->{
                    _state.update {
                        it.copy(error = result.value.error.message)
                    }
                    sendEvent(Event.Toast(result.value.error.message))
                    false
                }
                is Either.Right->{
                    if(result.value.lecturer_id!=""){
                        _state.update {
                            it.copy(loginSuccesfullyLecturer = result.value)
                        }
                        true
                    }else{
                        false
                    }

                }
            }
        }
        return resultState.await()
    }



}