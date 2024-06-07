package com.gokhanakbas.isyeriegitimiandroidapp.presentation.util.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.gokhanakbas.isyeriegitimiandroidapp.R
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Advert
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.AppliedAdvert
import com.gokhanakbas.isyeriegitimiandroidapp.presentation.navigation.Screen
import com.gokhanakbas.isyeriegitimiandroidapp.presentation.util.pagecomponents.appliedAdvertsListPage.AppliedAdvertsListPageViewModel
import com.gokhanakbas.isyeriegitimiandroidapp.util.Constants

@Composable
fun AdvertComp(
    navController: NavController,
    appliedAdvert: AppliedAdvert,
    viewModel: AppliedAdvertsListPageViewModel = hiltViewModel()
) {


    val state by viewModel.state.collectAsStateWithLifecycle()

    val deleteChoiceQuestion = remember {
        mutableStateOf(false)
    }

    LoadingDialog(isLoading = state.isLoading)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .shadow(5.dp)
            .background(shape = RoundedCornerShape(15.dp), color = Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(5.dp, Alignment.Top)
        ) {

            Text(
                text = appliedAdvert.appliedAdvert_title,
                style = MaterialTheme.typography.titleLarge,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis

            )
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
                    .height(1.dp)
                    .background(Color.LightGray)
            )

            Text(text = appliedAdvert.appliedAdvert_firm_name, style = MaterialTheme.typography.titleMedium)

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
                    .height(1.dp)
                    .background(Color.LightGray)
            )

            Text(
                text = appliedAdvert.appliedAdvert_applyDate,
                style = MaterialTheme.typography.labelMedium.copy(
                    Color.Gray
                )
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.End)
            ) {
                OutlinedButton(onClick = {
                    navController.navigate(Screen.AdvertPage.passNavigate(appliedAdvert.appliedAdvert_id))
                }) {
                    Text(text = stringResource(id = R.string.ilana_git))
                }
            }
        }
    }
}