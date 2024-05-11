package com.gokhanakbas.isyeriegitimiandroidapp.presentation.firm.firmInfoEditPage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.Either
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Firm
import com.gokhanakbas.isyeriegitimiandroidapp.domain.repository.FirmsRepository
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
class FirmInfoEditPageViewModel @Inject constructor(private val firmsRepository: FirmsRepository) :
    ViewModel() {

    private val _state = MutableStateFlow(FirmInfoEditPageState())
    val state = _state.asStateFlow()


    suspend fun getFirmInformation(firm_id: String) : Boolean {
        val result=viewModelScope.async {

            _state.update {
                it.copy(isLoading = true)
            }
            delay(2000)
            val saveResult=firmsRepository.getFirmInformation(firm_id)
            _state.update {
                it.copy(isLoading = false)
            }
            when (saveResult) {
                is Either.Right -> {
                    _state.update {
                        it.copy(firm =saveResult.value)
                    }
                    true
                }

                is Either.Left -> {
                    _state.update {
                        it.copy(error = saveResult.value.error.message)
                    }
                    sendEvent(Event.Toast(saveResult.value.error.message))
                    false
                }
            }

        }
        return result.await()
    }

    suspend fun saveFirmInformation(firm: Firm): Boolean {
        val result = viewModelScope.async {
            _state.update {
                it.copy(isLoading = true)
            }
            delay(2000)
            val saveResult = firmsRepository.saveFirmInformation(firm)
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
                    false
                }
            }
        }
        return result.await()
    }


    suspend fun saveFirmPassword(firm: Firm): Boolean {
        val result = viewModelScope.async {
            _state.update {
                it.copy(isLoading = true)
            }
            delay(2000)
            val saveResult = firmsRepository.saveFirmPassword(firm)
            _state.update {
                it.copy(isLoading = false)
            }
            when (saveResult) {
                is Either.Right -> {
                    _state.update {
                        it.copy(savedPasswordSuccessfully = true)
                    }
                    true
                }

                is Either.Left -> {
                    _state.update {
                        it.copy(savedPasswordSuccessfully = false)
                    }
                    sendEvent(Event.Toast(saveResult.value.error.message))
                    false
                }
            }
        }
        return result.await()
    }
}
