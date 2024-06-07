package com.gokhanakbas.isyeriegitimiandroidapp.presentation.entry.studentEntryPage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.Either
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Student
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
class StudentEntryPageViewModel @Inject constructor(private val studentsRepository: StudentsRepository) : ViewModel() {

    private val _state = MutableStateFlow(StudentEntryPageState())
    val state = _state.asStateFlow()

    suspend fun checkLoginCredentials(student_no:String,student_password : String) : Deferred<Boolean> {
        val resultState : Deferred<Boolean> =viewModelScope.async {
            _state.update {
                it.copy(isLoading = true)
            }
            val result=studentsRepository.login_student(student_no = student_no, student_password = student_password)
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
                    if(result.value.student_no!=""){
                        _state.update {
                            it.copy(loginSuccesfullyStudent = result.value)
                        }
                        true
                    }else{
                        false
                    }
                }
            }

        }
        return resultState
    }
}