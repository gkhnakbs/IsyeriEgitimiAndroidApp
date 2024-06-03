package com.gokhanakbas.isyeriegitimiandroidapp.presentation.entry.commissionEntryPage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.Either
import com.gokhanakbas.isyeriegitimiandroidapp.domain.repository.CommissionsRepository
import com.gokhanakbas.isyeriegitimiandroidapp.presentation.util.sendEvent
import com.gokhanakbas.isyeriegitimiandroidapp.util.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CommissionEntryPageViewModel @Inject constructor(private val commissionsRepository: CommissionsRepository) : ViewModel() {

    private val _state= MutableStateFlow(CommissionEntryPageState())
    val state=_state.asStateFlow()

    suspend fun checkLoginCredentials(commission_no:String,commission_password : String) : Deferred<Boolean> {
        val resultState : Deferred<Boolean> =viewModelScope.async {
            _state.update {
                it.copy(isLoading = true)
            }
            delay(1500)
            val result=commissionsRepository.loginCommission(commission_no = commission_no, commission_password = commission_password)
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
                    if(result.value.commission_id!=""){
                        _state.update {
                            it.copy(loginSuccessfullyCommission = result.value)
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