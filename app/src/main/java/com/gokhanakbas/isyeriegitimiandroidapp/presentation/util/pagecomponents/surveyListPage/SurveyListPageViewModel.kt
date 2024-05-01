package com.gokhanakbas.isyeriegitimiandroidapp.presentation.util.pagecomponents.surveyListPage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gokhanakbas.isyeriegitimiandroidapp.domain.repository.SurveysRepository
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
class SurveyListPageViewModel @Inject constructor(private val surveysRepository: SurveysRepository) :
    ViewModel() {

    private val _state = MutableStateFlow(SurveyListPageState())
    val state = _state.asStateFlow()

    fun getStudentsSurveys() {
        viewModelScope.launch {
            _state.update {
                it.copy(isLoading = true)
            }
            delay(2000)
            surveysRepository.getStudentsSurvey()
                .onRight { surveyList ->
                    _state.update {
                        it.copy(surveyList = surveyList)
                    }
                }
                .onLeft { error ->
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

    fun getFirmsSurveys() {
        viewModelScope.launch {
            _state.update {
                it.copy(isLoading = true)
            }
            delay(2000)
            surveysRepository.getFirmsSurvey()
                .onRight { surveyList ->
                    _state.update {
                        it.copy(surveyList = surveyList)
                    }
                }
                .onLeft { error ->
                    _state.update {
                        it.copy(error = error.error.message)
                    }
                }
            _state.update {
                it.copy(isLoading = false)
            }

        }
    }

    fun getLecturersSurveys() {
        viewModelScope.launch {
            _state.update {
                it.copy(isLoading = true)
            }
            delay(2000)
            surveysRepository.getLecturersSurveys()
                .onRight { surveyList ->
                    _state.update {
                        it.copy(surveyList = surveyList)
                    }
                }
                .onLeft { error ->
                    _state.update {
                        it.copy(error = error.error.message)
                    }
                }
            _state.update {
                it.copy(isLoading = false)
            }

        }
    }

}