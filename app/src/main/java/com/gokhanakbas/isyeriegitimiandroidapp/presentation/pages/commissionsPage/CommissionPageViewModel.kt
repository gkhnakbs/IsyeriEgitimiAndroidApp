package com.gokhanakbas.isyeriegitimiandroidapp.presentation.pages.commissionsPage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gokhanakbas.isyeriegitimiandroidapp.domain.repository.CommissionsRepository
import com.gokhanakbas.isyeriegitimiandroidapp.presentation.util.sendEvent
import com.gokhanakbas.isyeriegitimiandroidapp.util.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CommissionPageViewModel @Inject constructor(private val commissionsRepository: CommissionsRepository) : ViewModel() {

    private val _state = MutableStateFlow(CommissionPageState())
    val state=_state.asStateFlow()

    fun getCommissionInformation(commission_id:String){
        viewModelScope.launch {
            _state.update {
                it.copy(isLoading = true)
            }
            commissionsRepository.getCommissionInformation(commission_id)
                .onLeft {error->
                    _state.update {
                        it.copy(
                            error = error.error.message
                        )
                    }
                    sendEvent(Event.Toast(error.error.message))
                }
                .onRight { commission->
                    _state.update {
                        it.copy(
                            commission = commission
                        )
                    }
                }
            _state.update {
                it.copy(isLoading = false)
            }

        }
    }

}