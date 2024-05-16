package com.gokhanakbas.isyeriegitimiandroidapp.presentation.pages.studentsPage


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.Either
import com.gokhanakbas.isyeriegitimiandroidapp.domain.repository.StudentsRepository
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
class StudentPageViewModel @Inject constructor(
    private val studentsRepository: StudentsRepository
) : ViewModel() {

    private val _state = MutableStateFlow(StudentPageState())
    val state = _state.asStateFlow()


    suspend fun getStudentInformation(student_no: String) {
        viewModelScope.launch {
            _state.update {
                it.copy(isLoading = true)
            }
            delay(2000)
            studentsRepository.getStudentInformation(student_no)
                .onLeft { error ->
                    _state.update {
                        it.copy(error = error.error.message)
                    }
                    sendEvent(Event.Toast(error.error.message))
                }
                .onRight { studentObject ->
                    _state.update {
                        it.copy(student = studentObject)
                    }
                }
            _state.update {
                it.copy(isLoading = false)
            }
        }
    }

    suspend fun getGroupsLecturerInformation(student_no: String) : Boolean {
        val resultState=viewModelScope.async {
            _state.update {
                it.copy(isLoading = true)
            }
            delay(2000)
            val result=studentsRepository.getGroupsLecturerInformation(student_no)
            _state.update {
                it.copy(isLoading = false)
            }
            when(result){
                is Either.Left ->{
                    _state.update {
                        it.copy(error = result.value.error.message)
                    }
                    sendEvent(Event.Toast(result.value.error.message))
                    false
                }
                is Either.Right ->{
                    _state.update {
                        it.copy(groupsLecturer = result.value)
                    }
                    true
                }
            }
        }
        return resultState.await()
    }

    suspend fun getSkills(student_no: String) {
        viewModelScope.launch {
            _state.update {
                it.copy(isLoading = true)
            }
            delay(2000)
            studentsRepository.getSkills(student_no)
                .onLeft { error ->
                    _state.update {
                        it.copy(error = error.error.message)
                    }
                    sendEvent(Event.Toast(error.error.message))
                }
                .onRight { skillList ->
                    _state.update {
                        it.copy(skillList = skillList)
                    }
                }
            _state.update {
                it.copy(isLoading = false)
            }
        }
    }

}