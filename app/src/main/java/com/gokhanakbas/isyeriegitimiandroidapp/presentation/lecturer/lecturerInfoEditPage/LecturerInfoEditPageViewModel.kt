package com.gokhanakbas.isyeriegitimiandroidapp.presentation.lecturer.lecturerInfoEditPage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.Either
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Lecturer
import com.gokhanakbas.isyeriegitimiandroidapp.domain.repository.LecturersRepository
import com.gokhanakbas.isyeriegitimiandroidapp.presentation.util.sendEvent
import com.gokhanakbas.isyeriegitimiandroidapp.util.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LecturerInfoEditPageViewModel @Inject constructor(private val lecturersRepository: LecturersRepository) :
    ViewModel() {


    private val _state = MutableStateFlow(LecturerInfoEditPageState())
    val state = _state.asStateFlow()


    suspend fun getLecturerInformation(lecturer_id: String) {
        viewModelScope.launch {
            _state.update {
                it.copy(isLoading = true)
            }
            delay(2000)
            lecturersRepository.getLecturerInformation(lecturer_id)
                .onLeft { error ->
                    _state.update {
                        it.copy(error = error.error.message)
                    }
                    sendEvent(Event.Toast(error.error.message))
                }
                .onRight { lecturer ->
                    _state.update {
                        it.copy(lecturer = lecturer)
                    }
                }

            _state.update {
                it.copy(isLoading = false)
            }
        }
    }

    suspend fun saveLecturerInformation(lecturer: Lecturer) : Boolean {
        val result = viewModelScope.async {
            _state.update {
                it.copy(isLoading = true)
            }
            delay(2000)
            val stateResult = lecturersRepository.saveLecturerInformation(lecturer)
            _state.update {
                it.copy(isLoading = false)
            }
            when (stateResult) {
                is Either.Left -> {
                    _state.update {
                        it.copy(error = stateResult.value.error.message)
                    }
                    sendEvent(Event.Toast(stateResult.value.error.message))
                    false
                }
                is Either.Right -> {
                    _state.update {
                        it.copy(savedSuccesfully = stateResult.value)
                    }
                    true
                }
            }
        }
        return result.await()
    }

    suspend fun saveLecturerPassword(lecturer: Lecturer): Boolean {
        val result = viewModelScope.async {
            _state.update {
                it.copy(isLoading = true)
            }
            delay(2000)
            val stateResult = lecturersRepository.saveLecturerPassword(lecturer)
            _state.update {
                it.copy(isLoading = false)
            }
            when (stateResult) {
                is Either.Right -> {
                    _state.update {
                        it.copy(savedPasswordSuccesfully = stateResult.value)
                    }
                    true
                }

                is Either.Left -> {
                    _state.update {
                        it.copy(error = stateResult.value.error.message)
                    }
                    sendEvent(Event.Toast(stateResult.value.error.message))
                    false
                }
            }

        }
        return result.await()
    }
}