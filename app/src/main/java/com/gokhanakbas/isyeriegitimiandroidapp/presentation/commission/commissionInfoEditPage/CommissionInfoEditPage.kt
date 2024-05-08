package com.gokhanakbas.isyeriegitimiandroidapp.presentation.commission.commissionInfoEditPage

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.gokhanakbas.isyeriegitimiandroidapp.presentation.util.components.LoadingDialog

@Composable
fun CommissionInfoEditPage(navController: NavController,commission_id:String,viewModel: CommissionInfoEditPageViewModel= hiltViewModel()) {

    LaunchedEffect(key1 = viewModel) {
        viewModel.getCommissionsInformation(commission_id)
    }

    val state by viewModel.state.collectAsStateWithLifecycle()

    CommissionInfoEditPageContent(
        navController = navController,
        commissionInfoEditPageState = state,
        commissionInfoEditPageViewModel = viewModel
    )

}

@Composable
fun CommissionInfoEditPageContent(navController: NavController,commissionInfoEditPageState: CommissionInfoEditPageState,commissionInfoEditPageViewModel: CommissionInfoEditPageViewModel) {

    LoadingDialog(isLoading = commissionInfoEditPageState.isLoading)





}