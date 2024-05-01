package com.gokhanakbas.isyeriegitimiandroidapp.presentation.pages.reportsPage

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.Either
import com.gokhanakbas.isyeriegitimiandroidapp.R
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Report
import com.gokhanakbas.isyeriegitimiandroidapp.domain.repository.ReportsRepository
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
class ReportsPageViewModel @Inject constructor(private val reportsRepository: ReportsRepository) :
    ViewModel() {

    private val _state = MutableStateFlow(ReportsPageState())
    val state = _state.asStateFlow()


    fun getReportsInformation(report_id: String) {
        viewModelScope.launch {
            _state.update {
                it.copy(isLoading = true)
            }
            delay(2000)
            reportsRepository.getReportsInformation(report_id)
                .onRight { report ->
                    _state.update {
                        it.copy(report = report)
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

    suspend fun addWeeklyReports(context: Context, report: Report, student_no: String): Boolean {
        val result = viewModelScope.async {
            _state.update {
                it.copy(isLoading = true)
            }
            delay(2000)
            val result = reportsRepository.addWeeklyReports(report, student_no)
            _state.update {
                it.copy(isLoading = false)
            }
            when (result) {
                is Either.Right -> {
                    val rightResult = result.value
                    _state.update {
                        it.copy(savedSuccessfully = rightResult)
                    }
                    if(rightResult) sendEvent(Event.Toast(context.getString(R.string.rapor_basariyla_kaydedildi)))
                    else sendEvent(Event.Toast(context.getString(R.string.islem_gerceklestirilemedi)))
                    true
                }

                is Either.Left -> {
                    val leftResult = result.value
                    _state.update {
                        it.copy(savedSuccessfully = false)
                    }
                    sendEvent(Event.Toast(leftResult.error.message))
                    false
                }
            }
        }
        return result.await()
    }
}