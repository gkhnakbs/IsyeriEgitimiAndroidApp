package com.gokhanakbas.isyeriegitimiandroidapp.presentation.util.pagecomponents.studentListPage

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.gokhanakbas.isyeriegitimiandroidapp.R
import com.gokhanakbas.isyeriegitimiandroidapp.presentation.util.components.LoadingDialog
import com.gokhanakbas.isyeriegitimiandroidapp.presentation.util.components.StudentListRowComp

@Composable
internal fun StudentListPage(
    navController: NavController,
    paddingValues: PaddingValues,
    viewModel: StudentListPageViewModel = hiltViewModel()
) {

    LaunchedEffect(key1 = viewModel) {
        viewModel.getStudentList()
    }

    val state by viewModel.state.collectAsStateWithLifecycle()

    StudentListPageContent(
        navController = navController,
        paddingValues = paddingValues,
        studentListPageState = state
    )
}

@Composable
fun StudentListPageContent(
    navController: NavController,
    paddingValues: PaddingValues,
    studentListPageState: StudentListPageState
) {
    println("isLoading : " + studentListPageState.isLoading)
    LoadingDialog(isLoading = studentListPageState.isLoading)

    val tf_student_name = remember {
        mutableStateOf("")
    }

    val focus = LocalFocusManager.current
    val studentListOf = studentListPageState.student

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
        ) {
            OutlinedTextField(
                value = tf_student_name.value,
                onValueChange = {
                    tf_student_name.value = it
                    //Bu bolumde viewModel den fonksiyon calisitirilacak o da repository den calisitirp veritabanindan veri cekip liste halinde verecek
                    //o listede tekrar LazyColumn da gosterilecek.
                },
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.search_icon),
                        contentDescription = "Search Icon For Firm", Modifier.size(18.dp)
                    )
                },
                trailingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.cross_icon),
                        contentDescription = "Clear icon For Firm Search",
                        modifier = Modifier
                            .size(18.dp)
                            .clickable {
                                tf_student_name.value = ""
                                focus.clearFocus()
                            })
                },
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.ogrenci),
                        //fontSize = 18.sp,
                        color = Color.Gray
                    )
                },
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.White,
                    focusedContainerColor = Color.White
                ),
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
                singleLine = true,
                textStyle = TextStyle(fontSize = 18.sp),
                shape = RoundedCornerShape(8.dp)
            )
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(
                count = studentListOf.count(),
                key = {
                    studentListOf[it].id
                },
                itemContent = {
                    val studentObject = studentListOf[it]
                    StudentListRowComp(navController = navController, student = studentObject)
                }
            )
        }
    }
}
