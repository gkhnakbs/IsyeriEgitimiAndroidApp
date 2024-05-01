package com.gokhanakbas.isyeriegitimiandroidapp.presentation.util.pagecomponents.studentListPage

import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Student

data class StudentListPageState(
    var isLoading : Boolean = false,
    var student : List<Student> = emptyList(),
    var error : String? = null
)
