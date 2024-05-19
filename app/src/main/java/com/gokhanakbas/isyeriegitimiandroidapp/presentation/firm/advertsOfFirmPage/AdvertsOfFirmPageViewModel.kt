package com.gokhanakbas.isyeriegitimiandroidapp.presentation.firm.advertsOfFirmPage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.Either
import com.gokhanakbas.isyeriegitimiandroidapp.domain.repository.AdvertsRepository
import com.gokhanakbas.isyeriegitimiandroidapp.domain.repository.FirmsRepository
import com.gokhanakbas.isyeriegitimiandroidapp.presentation.util.sendEvent
import com.gokhanakbas.isyeriegitimiandroidapp.util.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class AdvertsOfFirmPageViewModel @Inject constructor(private val advertsRepository: AdvertsRepository) : ViewModel() {

    private val _state= MutableStateFlow(AdvertsOfFirmPageState())
    val state=_state.asStateFlow()


    suspend fun getFirmAdverts(firm_id : String) : Boolean {
        val resultState = viewModelScope.async {
            _state.update {
                it.copy(isLoading = true)
            }
            delay(3000)
            val result=advertsRepository.getFirmsAdverts(firm_id = firm_id)
            _state.update {
                it.copy(isLoading = false)
            }
            when(result){
                is Either.Right ->{
                    if(result.value.component1().isNotEmpty()){
                        _state.update {
                            it.copy(advertList = result.value)
                        }
                        true
                    }else{
                        false
                    }
                }
                is Either.Left ->{
                    _state.update {
                        it.copy(error = result.value.error.message)
                    }
                    sendEvent(Event.Toast(result.value.error.message))
                    false
                }
            }

        }

        return resultState.await()
    }


}
