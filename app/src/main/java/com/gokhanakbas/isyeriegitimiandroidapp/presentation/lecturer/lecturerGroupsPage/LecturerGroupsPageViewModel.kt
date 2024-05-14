package com.gokhanakbas.isyeriegitimiandroidapp.presentation.lecturer.lecturerGroupsPage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.Either
import com.gokhanakbas.isyeriegitimiandroidapp.domain.repository.LecturersRepository
import com.gokhanakbas.isyeriegitimiandroidapp.presentation.util.sendEvent
import com.gokhanakbas.isyeriegitimiandroidapp.util.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class LecturerGroupsPageViewModel @Inject constructor (private val lecturersRepository: LecturersRepository) : ViewModel() {

    private val _state= MutableStateFlow(LecturerGroupsPageState())
    val state = _state.asStateFlow()


    suspend fun getGroups(lecturer_id : String) : Boolean {
        val resultState=viewModelScope.async {
            _state.update {
                it.copy(isLoading = true)
            }
            delay(2000)
            val result=lecturersRepository.getLecturersGroups(lecturer_id)
            _state.update {
                it.copy(isLoading = false)
            }
            when(result){
                is Either.Right -> {
                    _state.update {
                        it.copy(groupList = result.value)
                    }
                    true
                }
                is Either.Left ->{
                    _state.update {
                        it.copy(error = result.value.error.message)
                    }
                    sendEvent(Event.Toast(result.value.error.message))
                    false
                }
            }
        }
        return resultState.await()
    }

}