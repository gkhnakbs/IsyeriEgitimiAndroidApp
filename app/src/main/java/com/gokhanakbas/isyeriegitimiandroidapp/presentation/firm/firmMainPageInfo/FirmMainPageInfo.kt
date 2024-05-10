package com.gokhanakbas.isyeriegitimiandroidapp.presentation.firm.firmMainPageInfo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.gokhanakbas.isyeriegitimiandroidapp.presentation.util.components.LoadingDialog
import com.gokhanakbas.isyeriegitimiandroidapp.ui.theme.GaziAcikMavi
import com.gokhanakbas.isyeriegitimiandroidapp.ui.theme.GaziKoyuMavi
import com.gokhanakbas.isyeriegitimiandroidapp.ui.theme.GaziMavi

@Composable
fun FirmInformationForFirm(
    firm_id: String,
    viewModel: FirmMainPageInfoViewModel = hiltViewModel()
) {

    LaunchedEffect(key1 = viewModel) {
        viewModel.getFirmInformation(firm_id)
    }

    val state by viewModel.state.collectAsStateWithLifecycle()

    FirmInfoForFirmContent(state = state)
}

@Composable
fun FirmInfoForFirmContent(state: FirmMainPageInfoState) {

    val firm=state.firm

    LoadingDialog(isLoading = state.isLoading)

    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(65.dp)
    ) {
        Box(
            modifier = Modifier
                .size(300.dp, 150.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(
                    GaziAcikMavi
                )
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                Text(text = state.firm.firm_name, fontSize = 35.sp, textAlign = TextAlign.Center)
            }

        }
        Box(
            modifier = Modifier
                .size(300.dp, 150.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(
                    GaziKoyuMavi
                )
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                Text(text = state.firm.firm_info, fontSize = 35.sp, textAlign = TextAlign.Center)
            }
        }
        Box(
            modifier = Modifier
                .size(300.dp, 150.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(
                    GaziMavi
                )
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                Text(text = state.firm.firm_mail, fontSize = 35.sp, textAlign = TextAlign.Center)
            }
        }
    }
}