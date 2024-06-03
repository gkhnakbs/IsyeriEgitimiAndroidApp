package com.gokhanakbas.isyeriegitimiandroidapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.gokhanakbas.isyeriegitimiandroidapp.presentation.commission.commissionMainPage.CommissionMainPage
import com.gokhanakbas.isyeriegitimiandroidapp.presentation.entry.EntryPage
import com.gokhanakbas.isyeriegitimiandroidapp.presentation.entry.commissionEntryPage.CommissionEntryPage
import com.gokhanakbas.isyeriegitimiandroidapp.presentation.entry.firmEntryPage.FirmEntryPage
import com.gokhanakbas.isyeriegitimiandroidapp.presentation.entry.lecturerEntryPage.LecturerEntryPage
import com.gokhanakbas.isyeriegitimiandroidapp.presentation.entry.studentEntryPage.StudentEntryPage
import com.gokhanakbas.isyeriegitimiandroidapp.presentation.firm.advertCreatingPage.AdvertCreateOrEditPage
import com.gokhanakbas.isyeriegitimiandroidapp.presentation.firm.firmInfoEditPage.FirmInfoEditPage
import com.gokhanakbas.isyeriegitimiandroidapp.presentation.firm.firmMainPage.FirmMainPage
import com.gokhanakbas.isyeriegitimiandroidapp.presentation.lecturer.lecturerInfoEditPage.LecturerInfoEditPage
import com.gokhanakbas.isyeriegitimiandroidapp.presentation.lecturer.lecturerMainPage.LecturerMainPage
import com.gokhanakbas.isyeriegitimiandroidapp.presentation.pages.advertsPage.AdvertPage
import com.gokhanakbas.isyeriegitimiandroidapp.presentation.pages.firmsPage.FirmPage
import com.gokhanakbas.isyeriegitimiandroidapp.presentation.pages.formsPage.FormPage
import com.gokhanakbas.isyeriegitimiandroidapp.presentation.pages.lecturersPage.LecturerPage
import com.gokhanakbas.isyeriegitimiandroidapp.presentation.pages.reportsPage.ReportsPage
import com.gokhanakbas.isyeriegitimiandroidapp.presentation.pages.studentsPage.StudentPage
import com.gokhanakbas.isyeriegitimiandroidapp.presentation.pages.surveysPage.SurveyPage
import com.gokhanakbas.isyeriegitimiandroidapp.presentation.student.studentInfoEditPage.StudentInfoEditPage
import com.gokhanakbas.isyeriegitimiandroidapp.presentation.student.studentMainPage.StudentMainPage


@Composable
fun AppNavGraph(
    navController: NavHostController
) {
    val sharedViewModel=SharedViewModel()

    NavHost(navController = navController, startDestination = Screen.EntryPage.route) {

        composable(Screen.EntryPage.route) {
            EntryPage(navController = navController)
        }

        composable(Screen.StudentEntryPage.route) {
            StudentEntryPage(navController = navController, sharedViewModel = sharedViewModel)
        }

        composable(Screen.FirmEntryPage.route) {
            FirmEntryPage(navController = navController, sharedViewModel = sharedViewModel)
        }

        composable(Screen.LecturerEntryPage.route) {
            LecturerEntryPage(navController = navController, sharedViewModel = sharedViewModel)
        }

        composable(Screen.CommissionEntryPage.route) {
            CommissionEntryPage(navController = navController, sharedViewModel = sharedViewModel)
        }

        composable(
            Screen.StudentMainPage.route) {
            StudentMainPage(navController = navController, sharedViewModel = sharedViewModel)

        }

        composable(Screen.FirmMainPage.route) {
            FirmMainPage(navController = navController, sharedViewModel = sharedViewModel)
        }

        composable(Screen.LecturerMainPage.route) {
            LecturerMainPage(navController = navController, sharedViewModel = sharedViewModel)
        }

        composable(Screen.CommissionMainPage.route) {
            CommissionMainPage(navController = navController, sharedViewModel = sharedViewModel)
        }

        composable(
            Screen.FirmPage.route,
            arguments = listOf(
                navArgument("firm_id") {
                    type = NavType.StringType
                }
            )) {
            val firm_id = it.arguments?.getString("firm_id")
            FirmPage(navController = navController, firm_id = firm_id!!, sharedViewModel = sharedViewModel)
        }

        composable(
            Screen.StudentPage.route, arguments = listOf(
                navArgument("student_no") {
                    type = NavType.StringType
                }
            )) {
            val student_no = it.arguments?.getString("student_no")
            StudentPage(navController = navController, student_no!!, sharedViewModel = sharedViewModel)
        }

        composable(
            Screen.LecturerPage.route, arguments = listOf(
                navArgument("lecturer_id") {
                    type = NavType.StringType
                }
            )) {
            val lecturer_id = it.arguments?.getString("lecturer_id")!!
            LecturerPage(navController = navController,lecturer_id = lecturer_id, sharedViewModel = sharedViewModel)
        }

        composable(Screen.AdvertPage.route, arguments = listOf(
            navArgument("advert_id") {
                type = NavType.StringType
            }
        )) {
            val advert_id = it.arguments?.getString("advert_id")!!
            AdvertPage(navController = navController, advert_id = advert_id, sharedViewModel = sharedViewModel)
        }

        composable(Screen.ReportPage.route, arguments = listOf(
            navArgument("report_id") {
                type = NavType.StringType
            }, navArgument("reason"){
                type= NavType.StringType
            }
        )) {
            val report_id = it.arguments?.getString("report_id")!!
            val reason = it.arguments?.getString("reason")!!
            ReportsPage(navController = navController, report_id = report_id, reason=reason)
        }

        composable(Screen.FormPage.route) {
            FormPage(navController = navController)
        }

        composable(Screen.SurveyPage.route) {
            SurveyPage(navController = navController)
        }

        composable(Screen.AdvertCreateOrEditPage.route) {
            AdvertCreateOrEditPage(navController = navController, sharedViewModel = sharedViewModel)
        }

        composable(Screen.StudentInfoEditPage.route) {
            StudentInfoEditPage(navController = navController, sharedViewModel = sharedViewModel)

        }

        composable(
            Screen.LecturerInfoEditPage.route) {
            LecturerInfoEditPage(navController = navController, sharedViewModel = sharedViewModel)
        }

        composable(
            Screen.FirmInfoEditPage.route) {
            FirmInfoEditPage(navController = navController, sharedViewModel = sharedViewModel)
        }


    }
}