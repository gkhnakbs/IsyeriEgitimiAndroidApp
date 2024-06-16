package com.gokhanakbas.isyeriegitimiandroidapp.presentation.firm.workingStudentsPageForFirm

import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Student

data class WorkingStudentsPageForFirmState(
    var isLoading : Boolean = false,
    var workingStudents : MutableList<Student> = mutableListOf(),
    var error : String? = null,
)
