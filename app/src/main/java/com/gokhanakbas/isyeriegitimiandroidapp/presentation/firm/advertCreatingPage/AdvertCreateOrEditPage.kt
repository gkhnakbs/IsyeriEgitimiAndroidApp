package com.gokhanakbas.isyeriegitimiandroidapp.presentation.firm.advertCreatingPage

import android.annotation.SuppressLint
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxColors
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
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
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.ZoneId
import java.util.Date

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

@SuppressLint("MutableCollectionMutableState")
@OptIn(ExperimentalMaterial3Api::class)
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

    val tf_advert_criteria = remember {
        mutableStateOf("")
    }

    val tf_advert_criteria_list = remember {
        mutableStateListOf<String>()
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

    val datePickerOpeningState = remember {
        mutableStateOf(false)
    }
    val datePickerOpeningState1 = remember {
        mutableStateOf(false)
    }

    val datePickerState = rememberDatePickerState(yearRange = 2024..2024,
        initialDisplayMode = DisplayMode.Picker,
        selectableDates = object : SelectableDates {
            override fun isSelectableDate(utcTimeMillis: Long): Boolean {
                return utcTimeMillis >= LocalDate.now().atStartOfDay(ZoneId.systemDefault())
                    .toInstant().toEpochMilli()
            }
        })

    val tf_advert_startDate = remember {
        mutableStateOf("")
    }
    val tf_advert_endDate = remember {
        mutableStateOf("")
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
            tf_advert_criteria_list.addAll(advert.advert_criteriaList)
            postCheck.value = advert.advert_post_title.isNotEmpty()
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

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                OutlinedTextField(
                    value = tf_advert_criteria.value,
                    onValueChange = { tf_advert_criteria.value = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.8f),
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedContainerColor = Color.White,
                        focusedContainerColor = Color.White,
                        disabledContainerColor = Color.White,
                        unfocusedBorderColor = GaziKoyuMavi,
                        focusedBorderColor = GaziKoyuMavi,
                    ),
                    singleLine = true,
                    label = { Text(text = stringResource(id = R.string.ilan_kriteri)) },
                )
                IconButton(
                    onClick = {
                        if (tf_advert_criteria.value.isNotEmpty()) {
                            tf_advert_criteria_list.add(tf_advert_criteria.value.trim())
                            tf_advert_criteria.value = ""
                        }
                        focusManager.clearFocus()
                    },
                    colors = IconButtonDefaults.iconButtonColors(
                        contentColor = Color.White,
                        containerColor = GaziAcikMavi
                    ),
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "",
                        modifier = Modifier.weight(0.2f),
                        tint = GaziKoyuMavi
                    )
                }

            }

            Spacer(modifier = Modifier.height(10.dp))

            tf_advert_criteria_list.forEachIndexed { index, criteria ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White,
                    )
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = criteria,
                            modifier = Modifier
                                .padding(10.dp)
                                .weight(0.8f),
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1
                        )
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "",
                            tint = Color.Red,
                            modifier = Modifier
                                .size(20.dp)
                                .weight(0.2f)
                                .clickable {
                                    tf_advert_criteria_list.apply {
                                        removeAt(index)
                                    }
                                }
                        )

                    }

                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                IconButton(onClick = {
                    focusManager.clearFocus()
                    datePickerOpeningState.value = true
                }) {
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = "",
                        modifier = Modifier
                            .size(32.dp)
                            .align(Alignment.CenterVertically)
                    )
                }
                Spacer(modifier = Modifier.width(10.dp))
                OutlinedTextField(
                    value = tf_advert_startDate.value,
                    onValueChange = {
                        tf_advert_startDate.value = it
                        dateError.value = false
                    },
                    modifier = Modifier
                        .fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedContainerColor = Color.White,
                        focusedContainerColor = Color.White,
                        disabledContainerColor = Color.White,
                        unfocusedBorderColor = GaziKoyuMavi,
                        focusedBorderColor = GaziKoyuMavi,
                    ),
                    readOnly = true,
                    label = { Text(text = stringResource(id = R.string.ilan_baslangic_tarihi)) },
                    placeholder = { Text(text = stringResource(id = R.string.yil_ay_gun)) },
                    isError = dateError.value,
                    supportingText = { Text(text = stringResource(id = R.string.zorunlu_yazisi_tf)) }

                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                IconButton(onClick = {
                    focusManager.clearFocus()
                    datePickerOpeningState1.value = true
                }) {
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = "",
                        modifier = Modifier
                            .size(32.dp)
                            .align(Alignment.CenterVertically)
                    )
                }
                Spacer(modifier = Modifier.width(10.dp))
                OutlinedTextField(
                    value = tf_advert_endDate.value,
                    onValueChange = {
                        tf_advert_endDate.value = it
                        dateError2.value = false
                    },
                    modifier = Modifier
                        .fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedContainerColor = Color.White,
                        focusedContainerColor = Color.White,
                        disabledContainerColor = Color.White,
                        unfocusedBorderColor = GaziKoyuMavi,
                        focusedBorderColor = GaziKoyuMavi,
                    ),
                    readOnly = true,
                    label = { Text(text = stringResource(id = R.string.ilan_bitis_tarihi)) },
                    placeholder = { Text(text = stringResource(id = R.string.yil_ay_gun)) },
                    isError = dateError2.value,
                    supportingText = { Text(text = stringResource(id = R.string.zorunlu_yazisi_tf)) }
                )
            }

            Row(
                modifier = Modifier.padding(15.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Checkbox(
                    checked = postCheck.value,
                    onCheckedChange = {
                        postCheck.value = it
                    },
                    colors = CheckboxColors(
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
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done,
                        keyboardType = KeyboardType.Text
                    ),
                    keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
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
                            dateError.value = tf_advert_startDate.value.isEmpty()
                            dateError2.value = tf_advert_endDate.value.isEmpty()
                            titleError.value = tf_advert_title.value.isEmpty()
                            descriptionError.value = tf_advert_description.value.isEmpty()
                            //Ilan yayinlama islemleri gerceklestirilecek
                            if (postCheck.value) {

                                postTitleError.value = tf_advert_post_title.value.isEmpty()

                                if (!titleError.value && !descriptionError.value && !dateError.value && !dateError2.value && !postTitleError.value) {
                                    val advert1 = Advert(
                                        advert_title = tf_advert_title.value.trim(),
                                        advert_details = tf_advert_description.value.trim(),
                                        advert_criteriaList = tf_advert_criteria_list,
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
                            } else {
                                if (!titleError.value && !descriptionError.value && !dateError.value && !dateError2.value) {
                                    val advert1 = Advert(
                                        advert_title = tf_advert_title.value.trim(),
                                        advert_details = tf_advert_description.value.trim(),
                                        advert_criteriaList = tf_advert_criteria_list,
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
                } else {
                    OutlinedButton(
                        onClick = {
                            //Ilan yayinlama islemleri gerceklestirilecek

                            if (tf_advert_startDate.value.isEmpty()) {
                                dateError.value = true
                            } else {
                                dateError.value = false
                            }
                            if (tf_advert_endDate.value.isEmpty()) {
                                dateError2.value = true
                            } else {
                                dateError2.value = false
                            }
                            if (tf_advert_title.value.isEmpty()) {
                                titleError.value = true
                            } else {
                                titleError.value = false
                            }
                            if (tf_advert_description.value.isEmpty()) {
                                descriptionError.value = true
                            } else {
                                descriptionError.value = false
                            }

                            if (postCheck.value) {
                                if (tf_advert_post_title.value.isEmpty()) {
                                    postTitleError.value = true
                                } else {
                                    postTitleError.value = false
                                }
                                if (!titleError.value && !descriptionError.value && !dateError.value && !dateError2.value && !postTitleError.value) {
                                    val advert1 = Advert(
                                        advert_title = tf_advert_title.value.trim(),
                                        advert_details = tf_advert_description.value.trim(),
                                        advert_criteriaList = tf_advert_criteria_list,
                                        advert_post_title = tf_advert_post_title.value.trim(),
                                        advert_startDate = tf_advert_startDate.value,
                                        advert_endDate = tf_advert_endDate.value,
                                        advert_interviewers = mutableStateOf(arrayListOf()),
                                        advert_firm = null,
                                        advert_firm_id = Constants.FIRM_ID,
                                        advert_firm_name = "",
                                        advert_id = advert.advert_id
                                    )
                                    CoroutineScope(Dispatchers.IO).launch {

                                        val job = async {
                                            viewModel.updateAdvert(
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
                            } else {
                                if (!titleError.value && !descriptionError.value && !dateError.value && !dateError2.value) {
                                    val advert1 = Advert(
                                        advert_title = tf_advert_title.value.trim(),
                                        advert_details = tf_advert_description.value.trim(),
                                        advert_criteriaList = tf_advert_criteria_list,
                                        advert_post_title = tf_advert_post_title.value.trim(),
                                        advert_startDate = tf_advert_startDate.value,
                                        advert_endDate = tf_advert_endDate.value,
                                        advert_interviewers = mutableStateOf(arrayListOf()),
                                        advert_firm = null,
                                        advert_firm_id = Constants.FIRM_ID,
                                        advert_firm_name = "",
                                        advert_id = advert.advert_id
                                    )
                                    CoroutineScope(Dispatchers.IO).launch {

                                        val job = async {
                                            viewModel.updateAdvert(
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

    if (datePickerOpeningState.value) {
        DatePickerDialogOfStartDate(
            onDateSelected = { tf_advert_startDate.value = it },
            state = datePickerState,
            datePickerOpeningState = datePickerOpeningState,
            dateError = dateError
        )
    }

    if (datePickerOpeningState1.value) {
        DatePickerDialogOfEndDate(
            onDateSelected = { tf_advert_endDate.value = it },
            state = datePickerState,
            datePickerOpeningState = datePickerOpeningState1,
            dateError = dateError2
        )
    }


}

@SuppressLint("SimpleDateFormat")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DatePickerDialogOfStartDate(
    onDateSelected: (String) -> Unit,
    state: DatePickerState,
    datePickerOpeningState: MutableState<Boolean>,
    dateError: MutableState<Boolean>
) {
    val selectedDate = state.selectedDateMillis?.let {
        SimpleDateFormat("yyyy-MM-dd").format(Date(it))
    } ?: ""
    DatePickerDialog(
        onDismissRequest = { datePickerOpeningState.value = false },
        confirmButton = {
            OutlinedButton(
                onClick = {
                    datePickerOpeningState.value = false
                    onDateSelected(selectedDate)
                    dateError.value = false
                }, colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = Color.Black
                ), border = BorderStroke(0.5.dp, Color.Black)
            ) {
                Text(
                    text = stringResource(id = R.string.tamam)
                )
            }

        },
        dismissButton = {
            OutlinedButton(
                onClick = { datePickerOpeningState.value = false },
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = Color.Black
                ),
                border = BorderStroke(0.5.dp, Color.Black)
            ) {
                Text(
                    text = stringResource(id = R.string.iptal_et)
                )
            }
        }
    ) {
        DatePicker(
            state = state,
            showModeToggle = true
        )
    }
}

@SuppressLint("SimpleDateFormat")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DatePickerDialogOfEndDate(
    onDateSelected: (String) -> Unit,
    state: DatePickerState,
    datePickerOpeningState: MutableState<Boolean>,
    dateError: MutableState<Boolean>
) {
    val selectedDate = state.selectedDateMillis?.let {
        SimpleDateFormat("yyyy-MM-dd").format(Date(it))
    } ?: ""
    DatePickerDialog(
        onDismissRequest = { datePickerOpeningState.value = false },
        confirmButton = {
            OutlinedButton(
                onClick = {
                    datePickerOpeningState.value = false
                    onDateSelected(selectedDate)
                    dateError.value = false
                }, colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = Color.Black
                ), border = BorderStroke(0.5.dp, Color.Black)
            ) {
                Text(
                    text = stringResource(id = R.string.tamam)
                )
            }

        },
        dismissButton = {
            OutlinedButton(
                onClick = { datePickerOpeningState.value = false },
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = Color.Black
                ),
                border = BorderStroke(0.5.dp, Color.Black)
            ) {
                Text(
                    text = stringResource(id = R.string.iptal_et)
                )
            }
        }
    ) {
        DatePicker(
            state = state,
            showModeToggle = true
        )
    }
}
