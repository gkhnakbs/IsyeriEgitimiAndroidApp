package com.gokhanakbas.isyeriegitimiandroidapp.presentation.lecturer

import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Lecturer

data class LecturerMainPageState(
    var isLoading:Boolean = false,
    var lecturer : Lecturer= Lecturer("","","","","","","","",""),
    var error:String?=null
)