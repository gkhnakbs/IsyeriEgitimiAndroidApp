package com.gokhanakbas.isyeriegitimiandroidapp.presentation.navigation

sealed class Screen(var route: String) {

    data object EntryPage : Screen("entryPageSelection")

    data object StudentEntryPage : Screen("studentEntryPage")
    data object FirmEntryPage : Screen("firmEntryPage")
    data object LecturerEntryPage : Screen("lecturerEntryPage")
    data object CommissionEntryPage : Screen("commissionEntryPage")

    data object StudentMainPage : Screen("studentMainPage")

    object FirmMainPage : Screen("firmMainPage/{firm_id}") {
        fun passNavigate(firm_id: String): String {
            route = "firmMainPage/$firm_id"
            return route
        }
    }

    object LecturerMainPage : Screen("lecturerMainPage/{lecturer_id}") {
        fun passNavigate(lecturer_id: String): String {
            route = "lecturerMainPage/$lecturer_id"
            return route
        }
    }

    object CommissionMainPage : Screen("commissionMainPage/{commission_id}") {
        fun passNavigate(commission_id: String): String {
            route = "commissionMainPage/$commission_id"
            return route
        }
    }

    object FirmPage : Screen("firmPage/{firm_id}") {
        fun passNavigate(firm_id: String): String {
            route = "firmPage/$firm_id"
            return route
        }
    }

    object StudentPage : Screen("studentPage/{student_no}") {
        fun passNavigate(student_no: String): String {
            route = "studentPage/$student_no"
            return route
        }
    }

    object LecturerPage : Screen("lecturerPage/{lecturer_id}") {
        fun passNavigate(lecturer_id: String): String {
            route = "lecturerPage/$lecturer_id"
            return route
        }
    }

    object StudentInfoEditPage : Screen("studentInfoEditPage/{student_no}") {
        fun passNavigate(student_no: String): String {
            route = "studentInfoEditPage/$student_no"
            return route
        }
    }

    object LecturerInfoEditPage : Screen("lecturerInfoEditPage/{lecturer_id}") {
        fun passNavigate(lecturer_id: String): String {
            route = "lecturerInfoEditPage/$lecturer_id"
            return route
        }
    }

    object FirmInfoEditPage : Screen("firmInfoEditPage/{firm_id}"){
        fun passNavigate(firm_id: String): String {
            route = "firmInfoEditPage/$firm_id"
            return route
        }
    }

    object AdvertPage : Screen("advertPage/{advert_id}") {
        fun passNavigate(advert_id: String): String {
            route = "advertPage/$advert_id"
            return route
        }
    }

    object AdvertCreatingPage : Screen("advertCreatingPage")

    object FormPage : Screen("formPage/{form_id}") {
        fun passNavigate(form_id: String): String {
            route = "formPage/$form_id"
            return route
        }
    }

    object SurveyPage : Screen("surveyPage/{survey_id}") {
        fun passNavigate(survey_id: String): String {
            route = "surveyPage/$survey_id"
            return route
        }
    }

    object ReportPage : Screen("reportPage/{report_id}/{editable}") {
        fun passNavigate(report_id: String,editable : Boolean): String {
            route = "reportPage/$report_id/$editable"
            return route
        }
    }
}