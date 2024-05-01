package com.gokhanakbas.isyeriegitimiandroidapp.presentation.pages.firmsPage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Firm
import com.gokhanakbas.isyeriegitimiandroidapp.domain.repository.FirmsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FirmPageViewModel @Inject constructor(
   private val firmRepository: FirmsRepository
) : ViewModel() {

    private val _state= MutableStateFlow(FirmPageState())
    val state=_state.asStateFlow()

    fun getFirmInformation(firm_id:String) {
        viewModelScope.launch {
            _state.update {
                it.copy(isLoading = true)
            }
            firmRepository.getFirmInformation(firm_id)
                .onLeft { error ->
                    _state.update {
                        it.copy(error = error.error.message)
                    }
                }
                .onRight { firm ->
                    _state.update {
                        it.copy(firm = firm)
                    }
                }
            _state.update {
                it.copy(isLoading = false)
            }
        }
    }

}