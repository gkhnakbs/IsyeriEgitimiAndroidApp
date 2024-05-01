package com.gokhanakbas.isyeriegitimiandroidapp.presentation.pages.studentsPage

import androidx.compose.runtime.mutableStateListOf
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Firm
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Skill
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Student

data class StudentPageState(
    var isLoading: Boolean = false,
    var student: Student = Student(
        "0", "", "", "", "", "", "", "", "", "", "", "",
        Firm("", "", "", "", "", "", "", ""), "", mutableStateListOf()
    ),
    var skillList: MutableList<Skill> = mutableStateListOf(),
    var error: String? = null
)