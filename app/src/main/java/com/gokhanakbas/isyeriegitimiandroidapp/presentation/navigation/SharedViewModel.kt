package com.gokhanakbas.isyeriegitimiandroidapp.presentation.navigation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Advert
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Commission
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Firm
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Form
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Group
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Lecturer
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Report
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Student
import com.gokhanakbas.isyeriegitimiandroidapp.domain.repository.StudentsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import javax.inject.Singleton


class SharedViewModel  : ViewModel() {

    var student by mutableStateOf<Student?>(null)
        private set

    fun addStudent(newStudent: Student?){
        student=newStudent
    }

    var lecturer by mutableStateOf<Lecturer?>(null)
        private set

    fun addLecturer(newLecturer: Lecturer?){
        lecturer=newLecturer
    }


    var firm by mutableStateOf<Firm?>(null)
        private set

    fun addFirm(newFirm: Firm?){
        firm=newFirm
    }


    var commission by mutableStateOf<Commission?>(null)
    private set

    fun addCommission(newCommission: Commission?){
        commission=newCommission
    }


    var advert by mutableStateOf<Advert?>(null)
    private set

    fun addAdvert(newAdvert: Advert?){
        advert=newAdvert
    }


    var report by mutableStateOf<Report?>(null)
        private set

    fun addReport(newReport: Report?){
        report=newReport
    }


    var form by mutableStateOf<Form?>(null)
        private set

    fun addForm(newForm: Form?){
        form=newForm
    }


    var group by mutableStateOf<Group?>(null)
        private set

    fun addGroup(newGroup: Group?){
        group=newGroup
    }



}