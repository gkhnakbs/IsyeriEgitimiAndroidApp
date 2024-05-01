package com.gokhanakbas.isyeriegitimiandroidapp.presentation.lecturer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gokhanakbas.isyeriegitimiandroidapp.domain.repository.LecturersRepository
import com.gokhanakbas.isyeriegitimiandroidapp.presentation.util.sendEvent
import com.gokhanakbas.isyeriegitimiandroidapp.util.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LecturerMainPageViewModel @Inject constructor(private val lecturersRepository: LecturersRepository) : ViewModel() {

    private val _state= MutableStateFlow(LecturerMainPageState())
    val state=_state.asStateFlow()


    fun getLecturersInformation(lecturer_id:String){
        viewModelScope.launch {
            _state.update {
                it.copy(isLoading = true)
            }
            lecturersRepository.getLecturerInformation(lecturer_id)
                .onRight { lecturer->
                    _state.update {
                        it.copy(lecturer = lecturer)
                    }
                }
                .onLeft {error->
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


}