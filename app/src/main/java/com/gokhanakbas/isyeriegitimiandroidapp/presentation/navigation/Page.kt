package com.gokhanakbas.isyeriegitimiandroidapp.presentation.navigation


import kotlinx.serialization.Serializable

sealed interface Page {

    @Serializable
    data object StudentEntryPage

    @Serializable
    data object FirmEntryPage

    @Serializable
    data object LecturerEntryPage

    @Serializable
    data object CommissionEntryPage

    @Serializable
    data object EntryPage

    @Serializable
    data class StudentMainPage(val student_no:String)

    @Serializable
    data class LecturerMainPage(val lecturer_id: String)

    @Serializable
    data class FirmMainPage( val firm_id: String)

    @Serializable
    data class CommissionMainPage(val commission_id: String)

    @Serializable
    data class StudentsPage(val student_no: String)

    @Serializable
    data class LecturersPage(val lecturer_id: String)

    @Serializable
    data class FirmsPage(val firm_id: String)

    @Serializable
    data class CommissionsPage(val commission_id: String)

    @Serializable
    data class StudentInfoEditPage(val student_no: String)

    @Serializable
    data class LecturerInfoEditPage(val lecturer_id: String)

    @Serializable
    data class FirmInfoEditPage(val firm_id: String)

    @Serializable
    data class CommissionInfoEditPage(val commission_id: String)

    @Serializable
    data class AdvertsPage(val advert_id: String)

    @Serializable
    data class FormsPage(val form_id: String)

    @Serializable
    data class SurveysPage(val survey_id: String)

    @Serializable
    data class ReportsPage(val report_id: String , val editable : Boolean)

    @Serializable
    data object AdvertCreatingPage

}