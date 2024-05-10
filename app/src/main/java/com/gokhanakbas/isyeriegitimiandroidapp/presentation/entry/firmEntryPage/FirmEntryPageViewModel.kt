package com.gokhanakbas.isyeriegitimiandroidapp.presentation.entry.firmEntryPage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gokhanakbas.isyeriegitimiandroidapp.domain.repository.FirmsRepository
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
class FirmEntryPageViewModel @Inject constructor(private val firmsRepository: FirmsRepository) : ViewModel() {

    private val _state= MutableStateFlow(FirmEntryPageState())
    val state=_state.asStateFlow()

    init {
        getFirms()
    }

    fun getFirms(){
        viewModelScope.launch {
            _state.update {
                it.copy(isLoading = true)
            }
            delay(2000)
            firmsRepository.getFirms()
                .onRight {firmList->
                    _state.update {
                        it.copy(firmList = firmList)
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