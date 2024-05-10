package com.gokhanakbas.isyeriegitimiandroidapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.gokhanakbas.isyeriegitimiandroidapp.presentation.commission.CommissionMainPage
import com.gokhanakbas.isyeriegitimiandroidapp.presentation.entry.EntryPage
import com.gokhanakbas.isyeriegitimiandroidapp.presentation.entry.commissionEntryPage.CommissionEntryPage
import com.gokhanakbas.isyeriegitimiandroidapp.presentation.entry.firmEntryPage.FirmEntryPage
import com.gokhanakbas.isyeriegitimiandroidapp.presentation.entry.lecturerEntryPage.LecturerEntryPage
import com.gokhanakbas.isyeriegitimiandroidapp.presentation.entry.studentEntryPage.StudentEntryPage
import com.gokhanakbas.isyeriegitimiandroidapp.presentation.firm.advertCreatingPage.AdvertCreatingPage
import com.gokhanakbas.isyeriegitimiandroidapp.presentation.firm.firmInfoEditPage.FirmInfoEditPage
import com.gokhanakbas.isyeriegitimiandroidapp.presentation.firm.firmMainPage.FirmMainPage
import com.gokhanakbas.isyeriegitimiandroidapp.presentation.lecturer.lecturerInfoEditPage.LecturerInfoEditPage
import com.gokhanakbas.isyeriegitimiandroidapp.presentation.lecturer.LecturerMainPage
import com.gokhanakbas.isyeriegitimiandroidapp.presentation.pages.lecturersPage.LecturerPage
import com.gokhanakbas.isyeriegitimiandroidapp.presentation.pages.SurveyPage
import com.gokhanakbas.isyeriegitimiandroidapp.presentation.pages.advertsPage.AdvertPage
import com.gokhanakbas.isyeriegitimiandroidapp.presentation.pages.firmsPage.FirmPage
import com.gokhanakbas.isyeriegitimiandroidapp.presentation.pages.formsPage.FormPage
import com.gokhanakbas.isyeriegitimiandroidapp.presentation.pages.reportsPage.ReportsPage
import com.gokhanakbas.isyeriegitimiandroidapp.presentation.pages.studentsPage.StudentPage
import com.gokhanakbas.isyeriegitimiandroidapp.presentation.student.studentInfoEditPage.StudentInfoEditPage
import com.gokhanakbas.isyeriegitimiandroidapp.presentation.student.studentMainPage.StudentMainPage


@Composable
fun AppNavGraph(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = Screen.EntryPage.route) {

        composable(Screen.EntryPage.route) {
            EntryPage(navController = navController)
        }

        composable(Screen.StudentEntryPage.route) {
            StudentEntryPage(navController = navController)
        }

        composable(Screen.FirmEntryPage.route) {
            FirmEntryPage(navController = navController)
        }

        composable(Screen.LecturerEntryPage.route) {
            LecturerEntryPage(navController = navController)
        }

        composable(Screen.CommissionEntryPage.route) {
            CommissionEntryPage(navController = navController)
        }

        composable(
            Screen.StudentMainPage.route,
            arguments = listOf(
                navArgument("student_no") {
                    type = NavType.StringType
                }
            )) {
            val student_no = it.arguments?.getString("student_no")
            StudentMainPage(navController = navController, student_no = student_no!!)

        }

        composable(Screen.FirmMainPage.route,
            arguments = listOf(
                navArgument("firm_id") {
                    type = NavType.StringType
                }
            )) {
            val firm_id = it.arguments?.getString("firm_id")
            FirmMainPage(navController = navController, firm_id = firm_id!!)
        }

        composable(
            Screen.LecturerMainPage.route, arguments = listOf(
                navArgument("lecturer_id") {
                    type = NavType.StringType
                }
            )
        ) {
            val lecturer_id = it.arguments?.getString("lecturer_id")!!
            LecturerMainPage(navController = navController, lecturer_id)
        }

        composable(
            Screen.CommissionMainPage.route,
            arguments = listOf(
                navArgument("commission_id") {
                    type = NavType.StringType
                }
            )
        ) {
            val commission_id = it.arguments?.getString("commission_id")!!
            CommissionMainPage(navController = navController, commission_id)
        }

        composable(
            Screen.FirmPage.route,
            arguments = listOf(
                navArgument("firm_id") {
                    type = NavType.StringType
                }
            )) {
            val firm_id = it.arguments?.getString("firm_id")
            FirmPage(navController = navController, firm_id = firm_id!!)
        }

        composable(
            Screen.StudentPage.route, arguments = listOf(
                navArgument("student_no") {
                    type = NavType.StringType
                }
            )) {
            val student_no = it.arguments?.getString("student_no")
            StudentPage(navController = navController, student_no!!)
        }

        composable(
            Screen.LecturerPage.route, arguments = listOf(
                navArgument("lecturer_id") {
                    type = NavType.StringType
                }
            )) {
            val lecturer_id = it.arguments?.getString("lecturer_id")!!
            LecturerPage(navController = navController,lecturer_id = lecturer_id)
        }

        composable(Screen.AdvertPage.route, arguments = listOf(
            navArgument("advert_id") {
                type = NavType.StringType
            }
        )) {
            val advert_id = it.arguments?.getString("advert_id")!!
            AdvertPage(navController = navController, advert_id = advert_id)
        }

        composable(Screen.ReportPage.route, arguments = listOf(
            navArgument("report_id") {
                type = NavType.StringType
            }, navArgument("editable"){
                type= NavType.BoolType
            }
        )) {
            val report_id = it.arguments?.getString("report_id")!!
            val editable = it.arguments?.getBoolean("editable")!!
            ReportsPage(navController = navController, report_id = report_id, editable = editable)
        }

        composable(Screen.FormPage.route) {
            FormPage(navController = navController)
        }

        composable(Screen.SurveyPage.route) {
            SurveyPage(navController = navController)
        }

        composable(Screen.AdvertCreatingPage.route) {
            AdvertCreatingPage(navController = navController)
        }

        composable(Screen.StudentInfoEditPage.route, arguments = listOf(
            navArgument("student_no"){
                type= NavType.StringType
            }
        )) {
            val student_no=it.arguments?.getString("student_no")!!
            StudentInfoEditPage(navController = navController, student_no = student_no)

        }

        composable(
            Screen.LecturerInfoEditPage.route, arguments = listOf(
                navArgument("lecturer_id") {
                    type = NavType.StringType
                }
            )) {
            val lecturer_id=it.arguments?.getString("lecturer_id")!!
            LecturerInfoEditPage(navController = navController, lecturer_id = lecturer_id)
        }

        composable(
            Screen.FirmInfoEditPage.route, arguments = listOf(
                navArgument("firm_id") {
                    type = NavType.StringType
                }
            )) {
            val firm_id = it.arguments?.getString("firm_id")!!
            FirmInfoEditPage(navController = navController, firm_id = firm_id)
        }

    }
}