package com.gokhanakbas.isyeriegitimiandroidapp.presentation.student.studentInfoEditPage

import androidx.compose.runtime.mutableStateListOf
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Firm
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Skill
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Student

data class StudentInfoEditPageState(
    var isLoading:Boolean=false,
    var student : Student = Student("","","","","","","","","","","","",
        Firm("","","","","","","","",""),"", mutableStateListOf()
    ),
    var skillList : MutableList<Skill> = mutableStateListOf(),
    var savedSuccessfully : Boolean=false,
    var savedSkillsSuccessfully : Boolean=false,
    var savedPasswordSuccessfully : Boolean = false,
    var error:String?=null
)