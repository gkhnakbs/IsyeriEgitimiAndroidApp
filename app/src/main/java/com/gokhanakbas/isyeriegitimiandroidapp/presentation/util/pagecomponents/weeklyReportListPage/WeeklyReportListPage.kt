package com.gokhanakbas.isyeriegitimiandroidapp.presentation.util.pagecomponents.weeklyReportListPage

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.gokhanakbas.isyeriegitimiandroidapp.R
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Report
import com.gokhanakbas.isyeriegitimiandroidapp.presentation.navigation.Screen
import com.gokhanakbas.isyeriegitimiandroidapp.presentation.util.components.LoadingDialog
import com.gokhanakbas.isyeriegitimiandroidapp.ui.theme.GaziKoyuMavi
import com.gokhanakbas.isyeriegitimiandroidapp.util.Constants

@Composable
fun WeeklyReportListPage(
    navController: NavController,
    paddingValues: PaddingValues,
    viewModel: WeeklyReportListPageViewModel = hiltViewModel()
) {

    LaunchedEffect(key1 = viewModel) {
        viewModel.getReports(Constants.STUDENT_NO)
    }

    val state by viewModel.state.collectAsStateWithLifecycle()

    WeeklyReportListPageContent(
        navController = navController,
        paddingValues = paddingValues,
        state = state
    )

}


@Composable
fun WeeklyReportListPageContent(
    navController: NavController,
    paddingValues: PaddingValues,
    state: WeeklyReportListPageState
) {

    LoadingDialog(isLoading = state.isLoading)

    val report_list = state.report_list

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(
                count = report_list.count(),
                key = {
                    report_list[it].id
                },
                itemContent = {
                    val report_object = report_list[it]
                    WeeklyReportCardContent(
                        navController = navController,
                        report = report_object
                    )
                }
            )
        }

        FloatingActionButton(
            onClick = {
                //Burada yeni bir rapor oluşturmak için rapor sayfasına yönlendiriyoruz.
                navController.navigate(Screen.ReportPage.passNavigate("0",true))
            },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom=50.dp,end=30.dp),
            shape = CircleShape,
            containerColor = Color.White
            ) {
                Icon(imageVector = Icons.Rounded.Add, contentDescription = "", tint = Color.Black)
        }
    }
}


@Composable
fun WeeklyReportCardContent(navController: NavController, report: Report) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        shape = RoundedCornerShape(5.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 5.dp
        ),
        border = BorderStroke(0.8.dp, GaziKoyuMavi),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp), verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(0.7f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = report.report_description,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.padding(5.dp)
                )
                Spacer(
                    modifier = Modifier
                        .height(0.5.dp)
                        .fillMaxWidth()
                        .background(Color.LightGray)
                )
                Row(
                    modifier = Modifier
                        .padding(5.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Icon(
                        painter = painterResource(id = R.drawable.date_icon),
                        contentDescription = "",
                        modifier = Modifier.size(17.dp)
                    )

                    Spacer(modifier = Modifier.width(5.dp))

                    Text(text = report.report_date, fontSize = 17.sp)
                }

            }
            Row(
                modifier = Modifier.weight(0.3f),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedButton(
                    onClick = {
                        //Rapor ekranına gidecek ama düzenleyemecek ve silemeyecek , sadece görüntüleyecek
                        navController.navigate(
                            Screen.ReportPage.passNavigate(
                                report_id = report.report_id,
                                editable = false
                            )
                        )
                    }, colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = Color.White,
                        contentColor = Color.Black
                    ), border = BorderStroke(0.5.dp, GaziKoyuMavi)
                ) {
                    Text(text = stringResource(id = R.string.raporu_goruntule), fontSize = 18.sp)
                }
            }

        }
    }

}