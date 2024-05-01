package com.gokhanakbas.isyeriegitimiandroidapp.presentation.entry.studentEntryPage

import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Student

data class StudentEntryPageState(
    var isLoading : Boolean =false,
    var student_list : List<Student>? = emptyList(),
    var error :String? = null
)