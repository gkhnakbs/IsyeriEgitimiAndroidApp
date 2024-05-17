package com.gokhanakbas.isyeriegitimiandroidapp.presentation.navigation

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Advert
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Commission
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Firm
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Form
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Group
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Lecturer
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Report
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Student


class SharedViewModel : ViewModel() {

    var student : Student = Student("","","","","","","","","","","","", Firm("","","","","","","","",""),"",
        mutableStateListOf() )
        private set

    fun addStudent(newStudent: Student){
        student=newStudent
    }

    var lecturer : Lecturer =Lecturer("","","","","","","","","")
        private set

    fun addLecturer(newLecturer: Lecturer){
        lecturer=newLecturer
    }


    var firm : Firm = Firm("","","","","","","","","")
        private set

    fun addFirm(newFirm: Firm){
        firm=newFirm
    }


    var commission : Commission = Commission("","","","","","","","")
        private set

    fun addCommission(newCommission: Commission){
        commission=newCommission
    }


    var advert : Advert = Advert("","","","","",Firm("","","","","","","","",""), emptyList(),"")
        private set

    fun addAdvert(newAdvert: Advert){
        advert=newAdvert
    }


    var report : Report = Report("","","","")
        private set

    fun addReport(newReport: Report){
        report=newReport
    }


    var form : Form = Form("","")
        private set

    fun addForm(newForm: Form){
        form=newForm
    }


    var group : Group = Group("","", emptyList(),"")
        private set

    fun addGroup(newGroup: Group){
        group=newGroup
    }



}