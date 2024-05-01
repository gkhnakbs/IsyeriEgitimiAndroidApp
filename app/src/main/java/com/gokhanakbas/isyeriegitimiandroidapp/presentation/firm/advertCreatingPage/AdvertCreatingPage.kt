package com.gokhanakbas.isyeriegitimiandroidapp.presentation.firm.advertCreatingPage

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.gokhanakbas.isyeriegitimiandroidapp.R
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Advert
import com.gokhanakbas.isyeriegitimiandroidapp.presentation.util.components.LoadingDialog
import com.gokhanakbas.isyeriegitimiandroidapp.ui.theme.GaziAcikMavi
import com.gokhanakbas.isyeriegitimiandroidapp.ui.theme.GaziKoyuMavi
import com.gokhanakbas.isyeriegitimiandroidapp.util.Constants

@Composable
fun AdvertCreatingPage(
    navController: NavController,
    viewModel: AdvertCreatingPageViewModel = hiltViewModel()
) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    AdvertCreatingPageContent(navController = navController, state = state, viewModel)

}

@Composable
fun AdvertCreatingPageContent(
    navController: NavController,
    state: AdvertCreatingPageState,
    viewModel: AdvertCreatingPageViewModel
) {

    LoadingDialog(isLoading = state.isLoading)

    val scrollState = rememberScrollState()

    val tf_advert_title = remember {
        mutableStateOf("")
    }
    val tf_advert_description = remember {
        mutableStateOf("")
    }
    val tf_advert_post_title = remember {
        mutableStateOf("")
    }
    val tf_advert_startDate = remember {
        mutableStateOf("")
    }
    val tf_advert_endDate = remember {
        mutableStateOf("")
    }

    val titleError = remember {
        mutableStateOf(false)
    }
    val descriptionError = remember {
        mutableStateOf(false)
    }
    val departmentError = remember {
        mutableStateOf(false)
    }
    val dateError = remember {
        mutableStateOf(false)
    }


    val focusManager = LocalFocusManager.current

    BackHandler {
        focusManager.clearFocus()
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(GaziAcikMavi)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(GaziAcikMavi)
                .verticalScroll(scrollState)
                .padding(10.dp)
                .clip(RoundedCornerShape(20.dp))
        ) {
            Spacer(
                modifier = Modifier
                    .height(50.dp)
                    .fillMaxWidth()
            )
            OutlinedTextField(
                value = tf_advert_title.value,
                onValueChange = {
                    tf_advert_title.value = it
                    titleError.value = it.trim().isEmpty()
                },
                label = { Text(text = stringResource(id = R.string.ilan_baslik)) },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 15.dp),
                isError = titleError.value,
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedContainerColor = Color.White,
                    focusedContainerColor = Color.White,
                    disabledContainerColor = Color.White,
                    unfocusedBorderColor = GaziKoyuMavi,
                    focusedBorderColor = GaziKoyuMavi,
                ),
                supportingText = { Text(text = stringResource(id = R.string.zorunlu_yazisi_tf)) }
            )
            OutlinedTextField(
                value = tf_advert_description.value,
                onValueChange = {
                    tf_advert_description.value = it
                    descriptionError.value = it.trim().isEmpty()
                },
                label = { Text(text = stringResource(id = R.string.ilan_aciklama)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp),
                minLines = 5,
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedContainerColor = Color.White,
                    focusedContainerColor = Color.White,
                    disabledContainerColor = Color.White,
                    unfocusedBorderColor = GaziKoyuMavi,
                    focusedBorderColor = GaziKoyuMavi,
                ),
                isError = descriptionError.value,
                supportingText = { Text(text = stringResource(id = R.string.zorunlu_yazisi_tf)) }
            )
            OutlinedTextField(
                value = tf_advert_post_title.value,
                onValueChange = {
                    tf_advert_post_title.value = it
                    departmentError.value = it.trim().isEmpty()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedContainerColor = Color.White,
                    focusedContainerColor = Color.White,
                    disabledContainerColor = Color.White,
                    unfocusedBorderColor = GaziKoyuMavi,
                    focusedBorderColor = GaziKoyuMavi,
                ),
                label = { Text(text = stringResource(id = R.string.ilan_bolum)) },
                isError = departmentError.value,
                supportingText = { Text(text = stringResource(id = R.string.zorunlu_yazisi_tf)) }

            )
            OutlinedTextField(
                value = tf_advert_startDate.value,
                onValueChange = {
                    tf_advert_startDate.value = it
                    dateError.value = it.trim().isEmpty()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedContainerColor = Color.White,
                    focusedContainerColor = Color.White,
                    disabledContainerColor = Color.White,
                    unfocusedBorderColor = GaziKoyuMavi,
                    focusedBorderColor = GaziKoyuMavi,
                ),
                label = { Text(text = stringResource(id = R.string.ilan_baslangic_tarihi)) },
                placeholder = { Text(text = stringResource(id = R.string.gun_ay_yil)) },
                isError = dateError.value,
                supportingText = { Text(text = stringResource(id = R.string.zorunlu_yazisi_tf)) }

            )
            OutlinedTextField(
                value = tf_advert_endDate.value,
                onValueChange = {
                    tf_advert_endDate.value = it
                    dateError.value = it.trim().isEmpty()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedContainerColor = Color.White,
                    focusedContainerColor = Color.White,
                    disabledContainerColor = Color.White,
                    unfocusedBorderColor = GaziKoyuMavi,
                    focusedBorderColor = GaziKoyuMavi,
                ),
                label = { Text(text = stringResource(id = R.string.ilan_bitis_tarihi)) },
                placeholder = { Text(text = stringResource(id = R.string.gun_ay_yil)) },
                isError = dateError.value,
                supportingText = { Text(text = stringResource(id = R.string.zorunlu_yazisi_tf)) }

            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                OutlinedButton(
                    onClick = {
                        //Ilan yayinlama islemleri gerceklestirilecek
                        val advert = Advert(
                            advert_title = tf_advert_title.value.trim(),
                            advert_details = tf_advert_description.value.trim(),
                            advert_post_title = tf_advert_post_title.value.trim(),
                            advert_startDate = tf_advert_startDate.value,
                            advert_endDate = tf_advert_endDate.value,
                            advert_interviewers = emptyList()
                        )
                        viewModel.createAdvert(firm_id = Constants.FIRM_ID, advert =advert)

                    }, modifier = Modifier
                        .padding(10.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = GaziAcikMavi,
                        contentColor = GaziKoyuMavi
                    ),
                    border = BorderStroke(2.dp, GaziKoyuMavi),
                    shape = RoundedCornerShape(10.dp),
                    enabled = ((tf_advert_title.value.trim() != "")
                            && (tf_advert_description.value.trim() != "")
                            && (tf_advert_post_title.value.trim() != "")
                            && (tf_advert_startDate.value != "")
                            && (tf_advert_endDate.value != ""))
                ) {
                    Text(text = stringResource(id = R.string.ilani_yayinla))
                }
            }
        }
        LaunchedEffect(key1 = state.succesfullyCreated) {
            state.succesfullyCreated=false
        }

        OutlinedButton(
            onClick = {
                navController.popBackStack()
            },
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(10.dp),
            colors = ButtonDefaults.outlinedButtonColors(
                containerColor = GaziKoyuMavi,
                contentColor = Color.White
            ),
            border = BorderStroke(2.dp, GaziKoyuMavi),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(text = stringResource(id = R.string.geri_don))
        }
    }
}