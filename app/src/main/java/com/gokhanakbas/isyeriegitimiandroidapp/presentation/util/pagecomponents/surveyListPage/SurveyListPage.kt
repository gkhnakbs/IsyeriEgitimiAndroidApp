package com.gokhanakbas.isyeriegitimiandroidapp.presentation.util.pagecomponents.surveyListPage

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.runtime.LaunchedEffect
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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.gokhanakbas.isyeriegitimiandroidapp.R
import com.gokhanakbas.isyeriegitimiandroidapp.presentation.navigation.Screen
import com.gokhanakbas.isyeriegitimiandroidapp.presentation.util.components.LoadingDialog
import com.gokhanakbas.isyeriegitimiandroidapp.ui.theme.GaziAcikMavi
import com.gokhanakbas.isyeriegitimiandroidapp.util.Constants

@Composable
fun SurveyListPage(
    navController: NavController,
    paddingValues: PaddingValues,
    surveyListPageViewModel: SurveyListPageViewModel = hiltViewModel(),
    surveyType:String
) {
    LaunchedEffect(key1 = surveyListPageViewModel) {
        when (surveyType) {

            Constants.STUDENT -> surveyListPageViewModel.getStudentsSurveys()
            Constants.FIRM -> surveyListPageViewModel.getFirmsSurveys()
            Constants.LECTURER -> surveyListPageViewModel.getLecturersSurveys()
        }
    }


    val surveyListPageState by surveyListPageViewModel.state.collectAsStateWithLifecycle()

    SurveyPageListContent(
        navController = navController,
        paddingValues = paddingValues,
        surveyListPageState = surveyListPageState
    )

}

@Composable
fun SurveyPageListContent(navController: NavController, paddingValues: PaddingValues,surveyListPageState: SurveyListPageState) {
    //Burada firmalarin oldugu bir liste olacak o da viewModel ve Repository uzerinden cekilecek

    LoadingDialog(isLoading = surveyListPageState.isLoading)
    
    val surveyListOf = surveyListPageState.surveyList

    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(3),
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .background(Color.White)
    ) {
        items(
            count = surveyListOf.count(),
            key = {
                surveyListOf[it].id
            },
            itemContent = {
                val survey_object = surveyListOf[it]
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
                            painter = painterResource(id = R.drawable.paper_icon),
                            contentDescription = "",
                            Modifier
                                .background(
                                    Color.White
                                )
                                .size(80.dp)
                        )
                        Text(text = survey_object.surveyName, fontSize = 20.sp)
                        OutlinedButton(
                            onClick = {
                                navController.navigate(Screen.SurveyPage.route)
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