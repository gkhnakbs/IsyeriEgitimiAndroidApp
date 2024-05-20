package com.gokhanakbas.isyeriegitimiandroidapp.presentation.firm.advertCreatingPage

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxColors
import androidx.compose.material3.CheckboxDefaults
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
import com.gokhanakbas.isyeriegitimiandroidapp.presentation.navigation.SharedViewModel
import com.gokhanakbas.isyeriegitimiandroidapp.presentation.util.components.LoadingDialog
import com.gokhanakbas.isyeriegitimiandroidapp.ui.theme.GaziAcikMavi
import com.gokhanakbas.isyeriegitimiandroidapp.ui.theme.GaziKoyuMavi
import com.gokhanakbas.isyeriegitimiandroidapp.util.Constants
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun AdvertCreateOrEditPage(
    navController: NavController,
    viewModel: AdvertCreateOrEditPageViewModel = hiltViewModel(),
    sharedViewModel: SharedViewModel
) {


    AdvertCreateOrEditPageContent(
        navController = navController,
        sharedViewModel = sharedViewModel,
        viewModel = viewModel
    )


}

@Composable
private fun AdvertCreateOrEditPageContent(
    navController: NavController,
    sharedViewModel: SharedViewModel,
    viewModel: AdvertCreateOrEditPageViewModel
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    state.advert = sharedViewModel.advert

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
    val postTitleError = remember {
        mutableStateOf(false)
    }
    val dateError = remember {
        mutableStateOf(false)
    }

    val dateError2 = remember {
        mutableStateOf(false)
    }

    val postCheck = remember {
        mutableStateOf(false)
    }

    var advert: Advert? = null

    if (state.advert != null) {
        advert = state.advert
        LaunchedEffect(key1 = viewModel.state) {
            tf_advert_title.value =
                advert!!.advert_title      //Burada null gelmeyeceğini gösterdikten sonra alttaki işlemler otomatik null gelmeyecek şekilde ayarlanıyor.
            tf_advert_description.value = advert.advert_details
            tf_advert_endDate.value = advert.advert_endDate
            tf_advert_startDate.value = advert.advert_startDate
            tf_advert_post_title.value = advert.advert_post_title
            postCheck.value=true
        }
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
                    dateError2.value = it.trim().isEmpty()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp)
                    .clickable {

                    },
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
                enabled = false,
                supportingText = { Text(text = stringResource(id = R.string.zorunlu_yazisi_tf)) }
            )
            Row(
                modifier = Modifier.padding(15.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Checkbox(
                    checked = postCheck.value, onCheckedChange = {
                        postCheck.value = it
                    }, colors = CheckboxColors(
                        uncheckedBorderColor = GaziKoyuMavi,
                        checkedBorderColor = GaziKoyuMavi,
                        checkedCheckmarkColor = GaziKoyuMavi,
                        uncheckedCheckmarkColor = Color.White,
                        checkedBoxColor = GaziAcikMavi,
                        uncheckedBoxColor = Color.White,
                        disabledBorderColor = GaziKoyuMavi,
                        disabledUncheckedBoxColor = Color.White,
                        disabledIndeterminateBorderColor = Color.White,
                        disabledCheckedBoxColor = Color.White,
                        disabledUncheckedBorderColor = GaziKoyuMavi,
                        disabledIndeterminateBoxColor = Color.White
                    ),
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = stringResource(id = R.string.ilani_post_olarak_paylas))
            }
            if (postCheck.value) {
                OutlinedTextField(
                    value = tf_advert_post_title.value,
                    onValueChange = {
                        tf_advert_post_title.value = it
                        postTitleError.value = it.trim().isEmpty()
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
                    label = { Text(text = stringResource(id = R.string.ilan_post_baslik)) },
                    isError = postTitleError.value,
                    supportingText = { Text(text = stringResource(id = R.string.zorunlu_yazisi_tf)) }

                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                if (advert == null) {
                    OutlinedButton(
                        onClick = {
                            //Ilan yayinlama islemleri gerceklestirilecek
                            if (postCheck.value) {
                                if (!titleError.value && !descriptionError.value && !dateError.value && !dateError2.value && !postTitleError.value) {
                                    val advert = Advert(
                                        advert_title = tf_advert_title.value.trim(),
                                        advert_details = tf_advert_description.value.trim(),
                                        advert_post_title = tf_advert_post_title.value.trim(),
                                        advert_startDate = tf_advert_startDate.value,
                                        advert_endDate = tf_advert_endDate.value,
                                        advert_interviewers = mutableStateOf(arrayListOf()),
                                        advert_firm = null,
                                        advert_firm_id = Constants.FIRM_ID,
                                        advert_firm_name = "",
                                        advert_id = ""
                                    )
                                    CoroutineScope(Dispatchers.IO).launch {

                                        val job = async {
                                            viewModel.createAdvert(
                                                firm_id = Constants.FIRM_ID,
                                                advert = advert
                                            )
                                        }
                                        if (job.await()) {
                                            withContext(Dispatchers.Main) {
                                                navController.popBackStack()
                                            }
                                        } else {
                                            withContext(Dispatchers.Main) {
                                                navController.popBackStack()
                                            }
                                        }
                                    }
                                }
                            } else {
                                if (!titleError.value && !descriptionError.value && !dateError.value && !dateError2.value) {
                                    val advert1 = Advert(
                                        advert_title = tf_advert_title.value.trim(),
                                        advert_details = tf_advert_description.value.trim(),
                                        advert_post_title = tf_advert_post_title.value.trim(),
                                        advert_startDate = tf_advert_startDate.value,
                                        advert_endDate = tf_advert_endDate.value,
                                        advert_interviewers = mutableStateOf(arrayListOf()),
                                        advert_firm = null,
                                        advert_firm_id = Constants.FIRM_ID,
                                        advert_firm_name = "",
                                        advert_id = ""
                                    )
                                    CoroutineScope(Dispatchers.IO).launch {

                                        val job = async {
                                            viewModel.createAdvert(
                                                firm_id = Constants.FIRM_ID,
                                                advert = advert1
                                            )
                                        }
                                        if (job.await()) {
                                            withContext(Dispatchers.Main) {
                                                navController.popBackStack()
                                            }
                                        } else {
                                            withContext(Dispatchers.Main) {
                                                navController.popBackStack()
                                            }
                                        }
                                    }
                                }


                            }


                        }, modifier = Modifier
                            .padding(10.dp),
                        colors = ButtonDefaults.outlinedButtonColors(
                            containerColor = GaziAcikMavi,
                            contentColor = GaziKoyuMavi
                        ),
                        border = BorderStroke(2.dp, GaziKoyuMavi),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Text(text = stringResource(id = R.string.ilani_yayinla))
                    }
                }
                else {
                    OutlinedButton(
                        onClick = {
                            //Ilan yayinlama islemleri gerceklestirilecek
                            if (postCheck.value) {
                                if (!titleError.value && !descriptionError.value && !dateError.value && !dateError2.value && !postTitleError.value) {
                                    val advert = Advert(
                                        advert_title = tf_advert_title.value.trim(),
                                        advert_details = tf_advert_description.value.trim(),
                                        advert_post_title = tf_advert_post_title.value.trim(),
                                        advert_startDate = tf_advert_startDate.value,
                                        advert_endDate = tf_advert_endDate.value,
                                        advert_interviewers = mutableStateOf(arrayListOf()),
                                        advert_firm = null,
                                        advert_firm_id = Constants.FIRM_ID,
                                        advert_firm_name = "",
                                        advert_id = ""
                                    )
                                    CoroutineScope(Dispatchers.IO).launch {

                                        val job = async {
                                            viewModel.updateAdvert(
                                                advert=advert
                                            )
                                        }
                                        if (job.await()) {
                                            withContext(Dispatchers.Main) {
                                                navController.popBackStack()
                                            }
                                        } else {
                                            withContext(Dispatchers.Main) {
                                                navController.popBackStack()
                                            }
                                        }
                                    }
                                }
                            } else {
                                if (!titleError.value && !descriptionError.value && !dateError.value && !dateError2.value) {
                                    val advert1 = Advert(
                                        advert_title = tf_advert_title.value.trim(),
                                        advert_details = tf_advert_description.value.trim(),
                                        advert_post_title = tf_advert_post_title.value.trim(),
                                        advert_startDate = tf_advert_startDate.value,
                                        advert_endDate = tf_advert_endDate.value,
                                        advert_interviewers = mutableStateOf(arrayListOf()),
                                        advert_firm = null,
                                        advert_firm_id = Constants.FIRM_ID,
                                        advert_firm_name = "",
                                        advert_id = ""
                                    )
                                    CoroutineScope(Dispatchers.IO).launch {

                                        val job = async {
                                            viewModel.createAdvert(
                                                firm_id = Constants.FIRM_ID,
                                                advert = advert1
                                            )
                                        }
                                        if (job.await()) {
                                            withContext(Dispatchers.Main) {
                                                navController.popBackStack()
                                            }
                                        } else {
                                            withContext(Dispatchers.Main) {
                                                navController.popBackStack()
                                            }
                                        }
                                    }
                                }


                            }


                        }, modifier = Modifier
                            .padding(10.dp),
                        colors = ButtonDefaults.outlinedButtonColors(
                            containerColor = GaziAcikMavi,
                            contentColor = GaziKoyuMavi
                        ),
                        border = BorderStroke(2.dp, GaziKoyuMavi),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Text(text = stringResource(id = R.string.ilani_guncelle))
                    }
                }
            }
        }
        LaunchedEffect(key1 = state.succesfullyCreated) {
            state.succesfullyCreated = false
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