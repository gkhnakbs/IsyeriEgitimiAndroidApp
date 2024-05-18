package com.gokhanakbas.isyeriegitimiandroidapp.presentation.entry.studentEntryPage

import androidx.compose.runtime.mutableStateListOf
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Firm
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Student

data class StudentEntryPageState(
    var isLoading : Boolean =false,
    var student_list : List<Student>? = emptyList(),
    var loginSuccesfullyStudent : Student =Student(
        "", "", "", "", "", "", "", "", "", "", "", "",
        Firm("", "", "", "", "", "", "", "",""),
        "", mutableStateListOf()
    ),
    var error :String? = null
)