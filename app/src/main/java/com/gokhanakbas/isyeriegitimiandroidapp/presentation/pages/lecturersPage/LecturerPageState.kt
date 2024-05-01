package com.gokhanakbas.isyeriegitimiandroidapp.presentation.pages.lecturersPage

import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Lecturer

data class LecturerPageState(
    var isLoading : Boolean =false,
    var lecturer : Lecturer = Lecturer("","","","","","","","",""),
    var error:String?=null
)