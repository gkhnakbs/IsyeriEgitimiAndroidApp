package com.gokhanakbas.isyeriegitimiandroidapp.presentation.student.studentMainPage

import androidx.compose.runtime.mutableStateListOf
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Firm
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Student

data class StudentMainPageState(
    var isLoading: Boolean = false,
    var student: Student = Student(
        "0", "", "", "", "", "", "", "", "", "", "", "",
        Firm("", "", "", "", "", "", "", "",""), "", mutableStateListOf()
    ),
    var error: String? = null
)