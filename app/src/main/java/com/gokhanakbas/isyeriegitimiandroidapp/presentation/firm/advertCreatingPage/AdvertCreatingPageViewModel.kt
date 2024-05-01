package com.gokhanakbas.isyeriegitimiandroidapp.presentation.firm.advertCreatingPage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gokhanakbas.isyeriegitimiandroidapp.R
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Advert
import com.gokhanakbas.isyeriegitimiandroidapp.domain.repository.AdvertsRepository
import com.gokhanakbas.isyeriegitimiandroidapp.presentation.util.sendEvent
import com.gokhanakbas.isyeriegitimiandroidapp.util.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AdvertCreatingPageViewModel @Inject constructor(private val advertsRepository: AdvertsRepository): ViewModel() {

    private val _state = MutableStateFlow(AdvertCreatingPageState())
    val state=_state.asStateFlow()


    fun createAdvert(firm_id:String,advert:Advert){
        viewModelScope.launch {
            _state.update {
                it.copy(isLoading = true)
            }

            advertsRepository.createAdvert(firm_id = firm_id, advert = advert)
                .onRight {
                    _state.update {
                        it.copy(succesfullyCreated = true)
                    }
                    sendEvent(Event.Toast(R.string.ilan_basariyla_yayinlandi.toString()))
                }
                .onLeft {error->
                    _state.update {
                        it.copy(succesfullyCreated = false, error = error.error.message)
                    }
                    sendEvent(Event.Toast(error.error.message))
                }

            _state.update {
                it.copy(isLoading = true)
            }
        }

    }

}