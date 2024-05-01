package com.gokhanakbas.isyeriegitimiandroidapp.presentation.util.pagecomponents.formListPage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.gokhanakbas.isyeriegitimiandroidapp.R
import com.gokhanakbas.isyeriegitimiandroidapp.presentation.navigation.Screen
import com.gokhanakbas.isyeriegitimiandroidapp.presentation.util.components.LoadingDialog
import com.gokhanakbas.isyeriegitimiandroidapp.ui.theme.GaziAcikMavi
import com.gokhanakbas.isyeriegitimiandroidapp.util.Constants

@Composable
fun FormListPage(
    navController: NavController,
    paddingValues: PaddingValues,
    formListPageViewModel: FormListPageViewModel = hiltViewModel(),
    formType: String
) {

    LaunchedEffect(key1 = formListPageViewModel) {
        when (formType) {
            Constants.STUDENT -> formListPageViewModel.getStudentsForms()
            Constants.FIRM -> formListPageViewModel.getFirmForms()
            Constants.LECTURER -> formListPageViewModel.getLecturerForms()
        }
    }

    val formListPageState by formListPageViewModel.state.collectAsState()

    FormListPageContent(
        navController = navController,
        paddingValues = paddingValues,
        formListPageState = formListPageState
    )
}


@Composable
fun FormListPageContent(
    navController: NavController,
    paddingValues: PaddingValues,
    formListPageState: FormListPageState
) {
    val formListOf = formListPageState.formList
    
    LoadingDialog(isLoading = formListPageState.isLoading)

    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(3),
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .background(Color.White)
    ) {
        items(
            count = formListOf.count(),
            key = {
                formListOf[it].id
            },
            itemContent = {
                val form_object = formListOf[it]
                Card(
                    modifier = Modifier
                        .padding(all = 5.dp)
                        .background(Color.White)
                        .clip(RoundedCornerShape(10.dp))
                ) {
                    Column(
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.background(Color.White)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.form_icon),
                            contentDescription = "",
                            Modifier
                                .background(
                                    Color.White
                                )
                                .size(80.dp)
                        )
                        Text(text = form_object.form_name, fontSize = 20.sp)
                        OutlinedButton(
                            onClick = {
                                navController.navigate(Screen.FormPage.route)
                            }, colors = ButtonDefaults.outlinedButtonColors(
                                containerColor = GaziAcikMavi
                            ), modifier = Modifier.width(150.dp)
                        ) {
                            Text(text = stringResource(id = R.string.goster))
                        }
                        OutlinedButton(
                            onClick = {

                            }, colors = ButtonDefaults.outlinedButtonColors(
                                containerColor = GaziAcikMavi
                            ), modifier = Modifier.width(150.dp)
                        ) {
                            Text(text = stringResource(id = R.string.doldur))
                        }
                        OutlinedButton(
                            onClick = {

                            }, colors = ButtonDefaults.outlinedButtonColors(
                                containerColor = GaziAcikMavi
                            ), modifier = Modifier.width(150.dp)
                        ) {
                            Text(
                                text = stringResource(id = R.string.yazdirIndir),
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }
        )
    }
}
