package com.gokhanakbas.isyeriegitimiandroidapp.presentation.lecturer.lecturerGroupsPage

import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Group

data class LecturerGroupsPageState(
    var isLoading:Boolean=false,
    var groupList:List<Group> = emptyList(),
    var error : String?=null
)
