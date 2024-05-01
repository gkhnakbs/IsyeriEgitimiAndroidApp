package com.gokhanakbas.isyeriegitimiandroidapp.presentation.util.pagecomponents.studentListPage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gokhanakbas.isyeriegitimiandroidapp.domain.repository.StudentsRepository
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
class StudentListPageViewModel @Inject constructor(
    private val studentsRepository: StudentsRepository
) : ViewModel() {

    private val _state = MutableStateFlow(StudentListPageState())
    val state = _state.asStateFlow()

    init {
        getStudentList()
    }

    fun getStudentList() {
        viewModelScope.launch {
            _state.update {
                it.copy(isLoading = true)
            }
            delay(2000)
            studentsRepository.getStudents()
                .onLeft { error ->
                    _state.update {
                        it.copy(error = error.error.message)
                    }
                    sendEvent(Event.Toast(error.error.message))
                }
                .onRight { student ->
                    _state.update {
                        it.copy(student = student)
                    }
                }
            _state.update {
                it.copy(isLoading = false)
            }
        }
    }
}