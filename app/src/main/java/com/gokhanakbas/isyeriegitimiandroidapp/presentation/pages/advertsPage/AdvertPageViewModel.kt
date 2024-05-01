package com.gokhanakbas.isyeriegitimiandroidapp.presentation.pages.advertsPage

import android.content.Context
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gokhanakbas.isyeriegitimiandroidapp.R
import com.gokhanakbas.isyeriegitimiandroidapp.domain.repository.AdvertsRepository
import com.gokhanakbas.isyeriegitimiandroidapp.presentation.util.sendEvent
import com.gokhanakbas.isyeriegitimiandroidapp.util.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AdvertPageViewModel @Inject constructor(private val advertsRepository: AdvertsRepository): ViewModel() {

    private val _state = MutableStateFlow(AdvertPageState())
    val state= _state.asStateFlow()


    fun getAdvertsInformation(advert_id:String){
        viewModelScope.launch {
            _state.update {
                it.copy(isLoading = true)
            }
            delay(1000)
            advertsRepository.getAdvertsInformation(advert_id)
                .onRight {advert ->
                    _state.update {
                        it.copy(advert = advert)
                    }
                }
                .onLeft {error->
                    _state.update {
                        it.copy(error = error.error.message)
                    }
                }
            _state.update {
                it.copy(isLoading = false)
            }
        }
    }

    fun applyToAdvert(context:Context,advert_id:String,student_no:String){
        viewModelScope.launch {
            _state.update {
                it.copy(isLoading = true)
            }
            delay(2000)
            advertsRepository.applyToAdvert(advert_id, student_no)
                .onRight {result->
                    //Success , Failed , Already Applied
                    _state.update {
                        it.copy(applyResult = result)
                    }
                    when (result) {
                        "Success" -> sendEvent(Event.Toast(context.getString(R.string.basvuru_basariyla_tamamlandi)))
                        "Failed" -> sendEvent(Event.Toast(context.getString(R.string.islem_gerceklestirilemedi)))
                        "Already Applied" -> sendEvent(Event.Toast(context.getString(R.string.basvuru_zaten_bulunmaktadır)))
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

}