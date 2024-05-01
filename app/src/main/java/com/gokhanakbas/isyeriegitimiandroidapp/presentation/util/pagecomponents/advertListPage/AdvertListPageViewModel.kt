package com.gokhanakbas.isyeriegitimiandroidapp.presentation.util.pagecomponents.advertListPage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gokhanakbas.isyeriegitimiandroidapp.domain.repository.AdvertsRepository
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
class AdvertListPageViewModel @Inject constructor( private val advertsRepository: AdvertsRepository) : ViewModel() {

    private val _state= MutableStateFlow(AdvertListPageState())
    val state = _state.asStateFlow()


    suspend fun getAdverts() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    isLoading = true
                )
            }
            delay(2000)
            advertsRepository.getAdverts()
                .onRight {advertList->
                    _state.update {
                        it.copy(
                            advert_list = advertList
                        )
                    }
                }
                .onLeft {error->
                    _state.update {
                        it.copy(
                            error = error.error.message
                        )
                    }
                    sendEvent(Event.Toast(error.error.message))
                }

            _state.update {
                it.copy(
                    isLoading = false
                )
            }

        }
    }


}