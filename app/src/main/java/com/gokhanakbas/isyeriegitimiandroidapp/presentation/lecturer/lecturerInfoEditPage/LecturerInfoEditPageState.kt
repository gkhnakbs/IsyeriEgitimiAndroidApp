package com.gokhanakbas.isyeriegitimiandroidapp.presentation.lecturer.lecturerInfoEditPage

import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Lecturer

data class LecturerInfoEditPageState (
    var isLoading: Boolean=false,
    var lecturer : Lecturer = Lecturer("","","","","","","","",""),
    var error : String?=null
)