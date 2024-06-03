package com.gokhanakbas.isyeriegitimiandroidapp.presentation.entry.studentEntryPage

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
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
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.gokhanakbas.isyeriegitimiandroidapp.R
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Student
import com.gokhanakbas.isyeriegitimiandroidapp.presentation.navigation.Screen
import com.gokhanakbas.isyeriegitimiandroidapp.presentation.navigation.SharedViewModel
import com.gokhanakbas.isyeriegitimiandroidapp.presentation.util.components.LoadingDialog
import com.gokhanakbas.isyeriegitimiandroidapp.ui.theme.GaziKoyuMavi
import com.gokhanakbas.isyeriegitimiandroidapp.util.Event
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


@Composable
fun StudentEntryPage(
    navController: NavController,
    viewModel: StudentEntryPageViewModel = hiltViewModel(),
    sharedViewModel: SharedViewModel
) {
    StudentEntryPageContent(
        navController = navController,
        viewModel=viewModel,
        sharedViewModel=sharedViewModel
    )
}

@Composable
private fun StudentEntryPageContent(
    navController: NavController,
    viewModel: StudentEntryPageViewModel,
    sharedViewModel: SharedViewModel
) {

    val studentEntryPageState by viewModel.state.collectAsStateWithLifecycle()

    val focusRequester= remember {
        FocusRequester()
    }
    val focusManager= LocalFocusManager.current

    LaunchedEffect(key1 = navController) {
        focusRequester.requestFocus()
    }

    val tf_studentNumber = remember { mutableStateOf("") }
    val tf_studentPassword = remember { mutableStateOf("") }
    val errorState = remember { mutableStateOf(false) }
    val errorState1 = remember { mutableStateOf(false) }

    LoadingDialog(isLoading = studentEntryPageState.isLoading)

    Column {
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.clickable {
                navController.navigate("entryPageSelection") {
                    popUpTo("studentEntryPage") {
                        inclusive = true
                    }
                }
            }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.back_button),
                contentDescription = "Go Back Button",
                modifier = Modifier.size(40.dp),
                tint = Color.Red
            )
            Text(text = stringResource(id = R.string.geri_don), color = Color.Red)
        }
        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.gazi_university_logo),
                contentDescription = "Gazi University Logo",
                modifier = Modifier.size(170.dp)
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(40.dp, Alignment.CenterVertically)
            ) {
                OutlinedTextField(
                    value = tf_studentNumber.value ,
                    onValueChange = {
                        tf_studentNumber.value=it.trim()
                        errorState.value = it.trim().length>11
                                    },
                    label = { Text(text = stringResource(id = R.string.ogrenci_no)) },
                    supportingText = {
                        if (errorState.value) {
                            Text(text =stringResource(id = R.string.en_fazla_11_karakter_olabilir))
                        } else {
                            Text(text =stringResource(id = R.string.zorunlu_yazisi_tf))
                        }

                    },
                    prefix = {
                        Icon(
                            painter = painterResource(id = R.drawable.student_thin_icon),
                            contentDescription = "password_icon",
                            modifier = Modifier.size(20.dp)
                        )
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    keyboardActions = KeyboardActions( onDone = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = GaziKoyuMavi,
                        unfocusedBorderColor = GaziKoyuMavi
                    ),
                    modifier = Modifier
                        .requiredWidth(300.dp)
                        .focusRequester(focusRequester = focusRequester),
                    isError = errorState.value,
                    singleLine = true
                )
                OutlinedTextField(
                    value = tf_studentPassword.value,
                    onValueChange = {
                        tf_studentPassword.value = it.trim()
                        errorState1.value = it.length < 5
                    },
                    label = { Text(text = stringResource(id = R.string.ogrenci_parola)) },
                    supportingText = {
                        if (errorState1.value) {
                            Text(text = stringResource(id = R.string.en_az_5_karakter_olmalidir))
                        } else {
                            Text(text = stringResource(id = R.string.zorunlu_yazisi_tf))
                        }
                    },
                    prefix = {
                        Icon(
                            painter = painterResource(id = R.drawable.password_icon),
                            contentDescription = "password_icon",
                            modifier = Modifier.size(20.dp)
                        )
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    visualTransformation = PasswordVisualTransformation(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = GaziKoyuMavi,
                        unfocusedBorderColor = GaziKoyuMavi
                    ),
                    isError = errorState1.value,
                    modifier = Modifier.requiredWidth(300.dp),
                    singleLine = true
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.width(300.dp)
            ) {
                OutlinedButton(
                    onClick = {
                        //Ogrenci Kayit Sayfasina yonlendirilecek
                    },
                    shape = RoundedCornerShape(15.dp),
                    modifier = Modifier.size(100.dp, 45.dp),
                    border = BorderStroke(2.dp, GaziKoyuMavi)
                ) {
                    Text(text = stringResource(id = R.string.kayit_ol), color = GaziKoyuMavi)
                }
                OutlinedButton(
                    onClick = {
                        if (tf_studentNumber.value.trim().isNotEmpty() || tf_studentPassword.value.trim().isNotEmpty()) {
                            if (!errorState.value && !errorState1.value) {

                                focusManager.clearFocus() //Herhangi bir hata alınırsa tekrar klavyeyi açmasın diye baştan kapattık.

                                CoroutineScope(Dispatchers.IO).launch {

                                    val job=viewModel.checkLoginCredentials(tf_studentNumber.value.trim(),tf_studentPassword.value.trim())

                                    if(job.await()){
                                            withContext(Dispatchers.Main){
                                                sharedViewModel.addStudent(studentEntryPageState.loginSuccesfullyStudent)
                                                navController.navigate(Screen.StudentMainPage.route)
                                            }
                                    }else{
                                        Event.Toast("Girilen Bilgiler Yanlış")
                                    }

                                }

                            }
                        } else {
                            errorState.value = true
                            errorState1.value = true
                        }

                    },
                    shape = RoundedCornerShape(15.dp),
                    modifier = Modifier.size(110.dp, 45.dp),
                    border = BorderStroke(2.dp, GaziKoyuMavi)
                ) {
                    Text(text = stringResource(id = R.string.giris_yap), color = GaziKoyuMavi)
                }
            }

        }
    }
}