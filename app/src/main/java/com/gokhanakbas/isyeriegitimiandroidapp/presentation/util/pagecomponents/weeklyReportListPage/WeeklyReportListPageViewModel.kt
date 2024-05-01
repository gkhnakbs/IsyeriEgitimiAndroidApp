package com.gokhanakbas.isyeriegitimiandroidapp.presentation.util.pagecomponents.weeklyReportListPage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Report
import com.gokhanakbas.isyeriegitimiandroidapp.domain.repository.ReportsRepository
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
class WeeklyReportListPageViewModel @Inject constructor(private val reportsRepository: ReportsRepository) : ViewModel() {

    private val _state= MutableStateFlow(WeeklyReportListPageState())
    val state=_state.asStateFlow()

    fun getReports(student_no:String){
        viewModelScope.launch {
            _state.update {
                it.copy(isLoading = true)
            }
            delay(2000)
            reportsRepository.getReports(student_no)
                .onLeft {error->
                    _state.update {
                        it.copy(error = error.error.message)
                    }
                    sendEvent(Event.Toast(error.error.message))
                }
                .onRight {reportList->
                    _state.update {
                        it.copy(report_list = reportList)
                    }
                }

            _state.update {
                it.copy(isLoading = false)
            }
        }
    }

}