package com.gokhanakbas.isyeriegitimiandroidapp.presentation.lecturer.lecturerInfoEditPage

import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Lecturer

data class LecturerInfoEditPageState (
    var isLoading: Boolean=false,
    var lecturer : Lecturer = Lecturer("","","","","","","","",""),
    var savedSuccesfully : Boolean = false,
    var savedPasswordSuccesfully : Boolean = false ,
    var error : String?=null
)