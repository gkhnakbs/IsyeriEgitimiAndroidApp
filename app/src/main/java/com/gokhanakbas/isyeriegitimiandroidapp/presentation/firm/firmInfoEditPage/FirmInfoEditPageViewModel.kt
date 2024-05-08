package com.gokhanakbas.isyeriegitimiandroidapp.presentation.firm.firmInfoEditPage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gokhanakbas.isyeriegitimiandroidapp.domain.repository.FirmsRepository
import com.gokhanakbas.isyeriegitimiandroidapp.presentation.util.sendEvent
import com.gokhanakbas.isyeriegitimiandroidapp.util.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FirmInfoEditPageViewModel @Inject constructor(private val firmsRepository: FirmsRepository) : ViewModel() {

    private val _state= MutableStateFlow(FirmInfoEditPageState())
    val state=_state.asStateFlow()


    fun getFirmInformation(firm_id:String){
        viewModelScope.launch {

            _state.update {
                it.copy(isLoading = true)
            }

            firmsRepository.getFirmInformation(firm_id)
                .onRight {firm->
                    _state.update {
                        it.copy(firm = firm)
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

    fun saveFirmInformation(){

    }

}