package com.gokhanakbas.isyeriegitimiandroidapp.presentation.firm.advertCreatingPage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.Either
import com.gokhanakbas.isyeriegitimiandroidapp.R
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Advert
import com.gokhanakbas.isyeriegitimiandroidapp.domain.repository.AdvertsRepository
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
class AdvertCreateOrEditPageViewModel @Inject constructor(private val advertsRepository: AdvertsRepository): ViewModel() {

    private val _state = MutableStateFlow(AdvertCreateOrEditPageState())
    val state=_state.asStateFlow()


    suspend fun createAdvert(firm_id:String,advert:Advert) : Boolean{
        val resultState=viewModelScope.async {
            _state.update {
                it.copy(isLoading = true)
            }
            delay(2000)
            val result=advertsRepository.createAdvert(firm_id = firm_id, advert = advert)
            _state.update {
                it.copy(isLoading = false)
            }
            when(result){
                is Either.Left ->{
                    _state.update {
                        it.copy(succesfullyCreated = false, error = result.value.error.message)
                    }
                    sendEvent(Event.Toast(result.value.error.message))
                    false
                }
                is Either.Right->{
                    if(result.value){
                        _state.update {
                            it.copy(succesfullyCreated = false)
                        }
                        sendEvent(Event.Toast(R.string.ilan_basariyla_yayinlandi.toString()))
                        true
                    }else{
                        _state.update {
                            it.copy(succesfullyCreated = false)
                        }
                        false
                    }
                }
            }

        }
        return resultState.await()
    }

    suspend fun updateAdvert(advert : Advert) : Boolean{
        val resultState=viewModelScope.async {
            _state.update {
                it.copy(
                    isLoading = true
                )
            }
            val result=advertsRepository.updateAdvert(advert)
            _state.update {
                it.copy(
                    isLoading = false
                )
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
                    if(result.value){
                        _state.update {
                            it.copy(succesfullyCreated = true)
                        }
                        sendEvent(Event.Toast(R.string.ilan_basariyla_yayinlandi.toString()))
                        true
                    }else{
                        _state.update {
                            it.copy(succesfullyCreated = false)
                        }
                        sendEvent(Event.Toast(R.string.bir_hata_olustu.toString()))
                        false
                    }


                }
            }

        }
        return resultState.await()
    }

}