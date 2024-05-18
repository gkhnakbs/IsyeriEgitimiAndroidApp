package com.gokhanakbas.isyeriegitimiandroidapp.presentation.entry.firmEntryPage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.Either
import com.gokhanakbas.isyeriegitimiandroidapp.domain.repository.FirmsRepository
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
class FirmEntryPageViewModel @Inject constructor(private val firmsRepository: FirmsRepository) : ViewModel() {

    private val _state= MutableStateFlow(FirmEntryPageState())
    val state=_state.asStateFlow()


    suspend fun checkLoginCredentials(firm_id : String,firm_password: String) : Boolean{
        val resultState : Deferred<Boolean> =viewModelScope.async {
            _state.update {
                it.copy(isLoading = true)
            }
            delay(2000)
            val result=firmsRepository.login_firm(firm_id =firm_id, firm_password = firm_password)
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
                    if(result.value.firm_id!=""){
                        _state.update {
                            it.copy(loginSuccessfullyFirm = result.value)
                        }
                        true
                    }else{
                        false
                    }

                }
            }
        }
        return resultState.await()
    }

}