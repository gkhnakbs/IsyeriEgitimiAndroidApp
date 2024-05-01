package com.gokhanakbas.isyeriegitimiandroidapp.presentation.entry.lecturerEntryPage

import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Lecturer

data class LecturerEntryPageState(
    var isLoading : Boolean =false,
    var lecturerValid : Boolean=true,
    var lecturerList : List<Lecturer> = emptyList() ,
    var error :String? = null
)