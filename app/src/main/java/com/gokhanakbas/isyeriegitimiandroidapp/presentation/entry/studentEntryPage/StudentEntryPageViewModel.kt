package com.gokhanakbas.isyeriegitimiandroidapp.presentation.entry.studentEntryPage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gokhanakbas.isyeriegitimiandroidapp.domain.repository.StudentsRepository
import com.gokhanakbas.isyeriegitimiandroidapp.presentation.util.sendEvent
import com.gokhanakbas.isyeriegitimiandroidapp.util.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StudentEntryPageViewModel @Inject constructor(private val studentsRepository: StudentsRepository) :
    ViewModel() {

    private val _state = MutableStateFlow(StudentEntryPageState())
    val state = _state.asStateFlow()

    init {
        getStudents()
    }


    fun getStudents() {
        viewModelScope.launch {
            _state.update {
                it.copy(isLoading = true)
            }
            delay(3000)
            studentsRepository.getStudents()
                .onLeft { error ->
                    _state.update {
                        it.copy(error = error.error.message)
                    }
                    sendEvent(Event.Toast(error.error.message))
                }
                .onRight { list ->
                    _state.update {
                        it.copy(student_list = list)
                    }

                }
            _state.update {
                it.copy(isLoading = false)
            }
        }
    }
}