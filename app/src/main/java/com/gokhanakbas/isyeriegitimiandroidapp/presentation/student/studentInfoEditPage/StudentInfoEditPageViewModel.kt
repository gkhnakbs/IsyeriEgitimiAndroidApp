package com.gokhanakbas.isyeriegitimiandroidapp.presentation.student.studentInfoEditPage

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.Either
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Firm
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Skill
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Student
import com.gokhanakbas.isyeriegitimiandroidapp.domain.repository.StudentsRepository
import com.gokhanakbas.isyeriegitimiandroidapp.presentation.util.sendEvent
import com.gokhanakbas.isyeriegitimiandroidapp.util.Constants
import com.gokhanakbas.isyeriegitimiandroidapp.util.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StudentInfoEditPageViewModel @Inject constructor(private val studentsRepository: StudentsRepository)  : ViewModel() {

    private val _state= MutableStateFlow(StudentInfoEditPageState())
    val state=_state.asStateFlow()


    suspend fun getStudentInformation(student_no:String) {
        viewModelScope.launch {
            _state.update {
                it.copy(isLoading = true)
            }
            delay(2000)
            studentsRepository.getStudentInformation(student_no)
                .onRight { student->
                    _state.update {
                        it.copy(student = student)
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

    suspend fun saveStudentInformation(student: Student): Boolean {
        val result = viewModelScope.async {
            _state.update {
                it.copy(isLoading = true)
            }
            delay(2000)
            val saveResult = studentsRepository.saveStudentsInformation(student)
            _state.update {
                it.copy(isLoading = false)
            }
            when (saveResult) {
                is Either.Right -> {
                    _state.update {
                        it.copy(savedSuccessfully = true)
                    }
                    true
                }
                is Either.Left -> {
                    _state.update {
                        it.copy(savedSuccessfully = false)
                    }
                    sendEvent(Event.Toast(saveResult.value.error.message))
                    true
                }
            }
        }
        return result.await()
    }

    suspend fun saveStudentSkills(student_no: String,skillList : MutableList<Skill>): Boolean {
        val result = viewModelScope.async {
            _state.update { it.copy(isLoading = true) }
            delay(2000)

            val saveResult = studentsRepository.saveStudentsSkills(student_no, skillList)
            _state.update { it.copy(isLoading = false) }

            when (saveResult) {
                is Either.Right -> {
                    _state.update { it.copy(savedSkillsSuccessfully = true) }
                    true
                }
                is Either.Left -> {
                    _state.update { it.copy(savedSkillsSuccessfully = false) }
                    sendEvent(Event.Toast(saveResult.value.error.message))
                    true
                }
            }
        }

        return result.await()
    }



}