package com.gokhanakbas.isyeriegitimiandroidapp.presentation.pages.formsPage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gokhanakbas.isyeriegitimiandroidapp.domain.repository.FirmsRepository
import com.gokhanakbas.isyeriegitimiandroidapp.domain.repository.FormsRepository
import com.gokhanakbas.isyeriegitimiandroidapp.presentation.pages.firmsPage.FirmPageState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FormPageViewModel @Inject constructor(
    private val formRepository: FormsRepository
) : ViewModel() {

    private val _state = MutableStateFlow(FormPageState())
    val state = _state.asStateFlow()

    fun getStudentForms() {
        viewModelScope.launch {
            _state.update {
                it.copy(isLoading = true)
            }

            formRepository.getStudentsForms()
                .onLeft {error ->
                    _state.update {
                        it.copy(error = error.error.message)
                    }
                }
                .onRight {forms ->
                    _state.update {
                        it.copy(form = forms)
                    }
                }

            _state.update {
                it.copy(isLoading = false)
            }

        }
    }
}