package com.gokhanakbas.isyeriegitimiandroidapp.presentation.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gokhanakbas.isyeriegitimiandroidapp.util.EventBus
import kotlinx.coroutines.launch

fun ViewModel.sendEvent(event:Any) {
    viewModelScope.launch {
        EventBus.sendEvent(event)
    }
}