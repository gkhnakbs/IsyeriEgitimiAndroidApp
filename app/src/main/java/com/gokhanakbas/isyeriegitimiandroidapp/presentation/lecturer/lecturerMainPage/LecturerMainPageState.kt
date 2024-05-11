package com.gokhanakbas.isyeriegitimiandroidapp.presentation.lecturer.lecturerMainPage

import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Lecturer

data class LecturerMainPageState(
    var isLoading:Boolean = false,
    var lecturer : Lecturer= Lecturer("","","","","","","","",""),
    var error:String?=null
)