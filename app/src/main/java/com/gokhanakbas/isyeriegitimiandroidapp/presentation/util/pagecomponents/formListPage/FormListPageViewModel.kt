package com.gokhanakbas.isyeriegitimiandroidapp.presentation.util.pagecomponents.formListPage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gokhanakbas.isyeriegitimiandroidapp.domain.repository.FormsRepository
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
class FormListPageViewModel
@Inject
constructor(private val formsRepository: FormsRepository) : ViewModel() {

    val _state= MutableStateFlow(FormListPageState())
    val state=_state.asStateFlow()

    fun getStudentsForms(){
        viewModelScope.launch {
            _state.update {
                it.copy(isLoading = true)
            }
            delay(2000)
            formsRepository.getStudentsForms()
                .onRight { formList ->
                    _state.update {
                        it.copy(formList = formList)
                    }
                }
                .onLeft {error ->
                    _state.update {
                        it.copy(error= error.error.message)
                    }
                    sendEvent(Event.Toast(error.error.message))
                }
            _state.update {
                it.copy(isLoading = false)
            }

        }
    }

    fun getCommonForms(){
        viewModelScope.launch {
            _state.update {
                it.copy(isLoading = true)
            }
            delay(2000)
            formsRepository.getCommonForms()
                .onRight { formList ->
                    _state.update {
                        it.copy(formList = formList)
                    }
                }
                .onLeft {error ->
                    _state.update {
                        it.copy(error= error.error.message)
                    }
                }
            _state.update {
                it.copy(isLoading = false)
            }

        }
    }

    fun getLecturerForms(){
        viewModelScope.launch {
            _state.update {
                it.copy(isLoading = true)
            }
            delay(2000)
            formsRepository.getLecturerForms()
                .onRight { formList ->
                    _state.update {
                        it.copy(formList = formList)
                    }
                }
                .onLeft {error ->
                    _state.update {
                        it.copy(error= error.error.message)
                    }
                }
            _state.update {
                it.copy(isLoading = false)
            }

        }
    }

    fun getFirmForms(){
        viewModelScope.launch {
            _state.update {
                it.copy(isLoading = true)
            }
            delay(2000)
            formsRepository.getFirmForms()
                .onRight { formList ->
                    _state.update {
                        it.copy(formList = formList)
                    }
                }
                .onLeft {error ->
                    _state.update {
                        it.copy(error= error.error.message)
                    }
                }
            _state.update {
                it.copy(isLoading = false)
            }

        }
    }
}