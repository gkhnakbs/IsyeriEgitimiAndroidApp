package com.gokhanakbas.isyeriegitimiandroidapp.presentation.firm.workingStudentsPageForFirm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.Either
import com.gokhanakbas.isyeriegitimiandroidapp.domain.repository.StudentsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class WorkingStudentsPageForFirmViewModel @Inject constructor(private val studentsRepository: StudentsRepository) :
    ViewModel() {

    private val _state = MutableStateFlow(WorkingStudentsPageForFirmState())
    val state = _state.asStateFlow()


    suspend fun getWorkingStudents(firm_id: String): Deferred<Boolean> {
        val resultState = viewModelScope.async {
            _state.update {
                it.copy(
                    isLoading = true
                )
            }
            val result = studentsRepository.getWorkingStudents(firm_id)
            _state.update { it.copy(isLoading = false) }

            when (result) {
                is Either.Left -> {
                    _state.update {
                        it.copy(
                            error = result.value.error.message
                        )
                    }
                    false
                }
                is Either.Right -> {
                    _state.update {
                        it.copy(
                            workingStudents = result.value
                        )
                    }
                    true
                }
            }
        }
        return resultState
    }
}












