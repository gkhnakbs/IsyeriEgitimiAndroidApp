package com.gokhanakbas.isyeriegitimiandroidapp.presentation.student.studentMainPage

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.gokhanakbas.isyeriegitimiandroidapp.R
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.NavItem
import com.gokhanakbas.isyeriegitimiandroidapp.presentation.navigation.Screen
import com.gokhanakbas.isyeriegitimiandroidapp.presentation.util.components.ExitQuestionComp
import com.gokhanakbas.isyeriegitimiandroidapp.presentation.util.components.LogOutQuestionComp
import com.gokhanakbas.isyeriegitimiandroidapp.presentation.util.components.ScaffoldComp
import com.gokhanakbas.isyeriegitimiandroidapp.presentation.util.pagecomponents.advertListPage.AdvertListPage
import com.gokhanakbas.isyeriegitimiandroidapp.presentation.util.pagecomponents.appliedAdvertsListPage.AppliedAdvertsListPage
import com.gokhanakbas.isyeriegitimiandroidapp.presentation.util.pagecomponents.favouritePostListPage.FavouritePostListPage
import com.gokhanakbas.isyeriegitimiandroidapp.presentation.util.pagecomponents.firmListPage.FirmListPage
import com.gokhanakbas.isyeriegitimiandroidapp.presentation.util.pagecomponents.formListPage.FormListPage
import com.gokhanakbas.isyeriegitimiandroidapp.presentation.util.pagecomponents.homePagePostFeed.HomePagePostFeed
import com.gokhanakbas.isyeriegitimiandroidapp.presentation.util.pagecomponents.surveyListPage.SurveyListPage
import com.gokhanakbas.isyeriegitimiandroidapp.presentation.util.pagecomponents.weeklyReportListPage.WeeklyReportListPage
import com.gokhanakbas.isyeriegitimiandroidapp.ui.theme.GaziAcikMavi
import com.gokhanakbas.isyeriegitimiandroidapp.ui.theme.Mavi2
import com.gokhanakbas.isyeriegitimiandroidapp.util.Constants
import kotlinx.coroutines.launch

var lastPage = 1

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun StudentMainPage(
    navController: NavController,
    student_no: String,
    viewModel: StudentMainPageViewModel = hiltViewModel()
) {

    LaunchedEffect(viewModel) {
        viewModel.getStudentInformation(student_no)
    }

    //Constants tanımlamaları
    Constants.USER_TYPE=Constants.STUDENT
    Constants.STUDENT_NO = student_no

    val state by viewModel.state.collectAsStateWithLifecycle()

    StudentMainPageContent(navController = navController, studentMainPageState = state)

}

@Composable
fun StudentMainPageContent(
    navController: NavController,
    studentMainPageState: StudentMainPageState
) {

    val navDrawerState1= remember {
        DrawerState(DrawerValue.Closed)
    }

    val navDrawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val navScope = rememberCoroutineScope()
    val navDrawerSecilen = remember { mutableIntStateOf(lastPage) }
    val log_out_choice = remember { mutableStateOf(false) }
    val exit_choice = remember { mutableStateOf(false) }

    val studentObject = studentMainPageState.student


    val liste = listOf(
        NavItem(
            1,
            stringResource(id = R.string.ana_sayfa),
            painterResource(id = R.drawable.home_icon)
        ),
        NavItem(
            2,
            stringResource(id = R.string.ilanlar),
            painterResource(id = R.drawable.advertisement_icon)
        ),
        NavItem(
            3,
            stringResource(id = R.string.firma),
            painterResource(id = R.drawable.workplace_icon)
        ),
        NavItem(
            4,
            stringResource(id = R.string.basvurulmus_ilanlar),
            painterResource(id = R.drawable.apply_icon)
        ),
        NavItem(
            5,
            stringResource(id = R.string.form),
            painterResource(id = R.drawable.form_icon)
        ),
        NavItem(
            6,
            stringResource(id = R.string.anket),
            painterResource(id = R.drawable.document_icon)
        ),
        NavItem(
            7,
            stringResource(id = R.string.haftalik_rapor),
            painterResource(id = R.drawable.report_icon)
        ),
        NavItem(
            8,
            stringResource(id = R.string.favori_ilanlar),
            painterResource(id = R.drawable.bookmark_icon)
        )
    )

    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(250.dp),
                drawerContainerColor = Mavi2,
                drawerShape = RoundedCornerShape(0.dp, 10.dp, 10.dp, 0.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(250.dp, 100.dp)
                        .clip(RoundedCornerShape(20.dp, 20.dp, 0.dp, 0.dp))
                        .clickable {
                            navController.navigate(Screen.StudentPage.passNavigate(studentObject.student_no))
                        }
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.gazi_rektorluk),
                        contentDescription = "",
                        modifier =
                        Modifier
                            .alpha(0.5f)
                            .fillMaxSize()
                            .aspectRatio(1.4f)
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start, modifier = Modifier
                            .align(Alignment.Center)
                            .fillMaxSize()
                    ) {
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.fotoyeni),
                                contentDescription = "Student Photo",
                                alignment = Alignment.Center,
                                modifier = Modifier
                                    .size(130.dp),
                                contentScale = ContentScale.FillBounds
                            )
                        }

                        Spacer(modifier = Modifier.size(5.dp, 0.dp))
                        Text(
                            text = studentObject.student_name,
                            fontSize = 18.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            textDecoration = TextDecoration.Underline
                        )
                    }
                }
                Column(modifier=Modifier.verticalScroll(rememberScrollState())){
                    liste.forEachIndexed { index, icerik ->
                        NavigationDrawerItem(
                            label = { Text(text = icerik.nav_name) },
                            selected = navDrawerSecilen.intValue == index + 1,
                            onClick = {
                                navDrawerSecilen.intValue = index + 1
                                lastPage = index + 1
                                navScope.launch { navDrawerState.close() }
                            }, modifier = Modifier.padding(top = 5.dp, bottom = 5.dp),
                            icon = {
                                Icon(
                                    painter = icerik.nav_icon,
                                    contentDescription = "",
                                    modifier = Modifier.size(20.dp)
                                )
                            },
                            colors = NavigationDrawerItemDefaults.colors(
                                selectedContainerColor = GaziAcikMavi
                            )
                        )
                    }
                    NavigationDrawerItem(
                        label = { Text(text = stringResource(id = R.string.cikis_yap)) },
                        selected = false,
                        onClick = {
                            navScope.launch { navDrawerState.close() }
                            log_out_choice.value = true
                        }, modifier = Modifier.padding(top = 5.dp, bottom = 5.dp),
                        icon = {
                            Icon(
                                painter = painterResource(id = R.drawable.logout_icon),
                                contentDescription = "",
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    )
                }
            }
        },
        drawerState = navDrawerState,
        gesturesEnabled = navDrawerState.isOpen
    ) {
        when (navDrawerSecilen.intValue) {

            1 -> ScaffoldComp(onMenuIconClick = { navScope.launch { navDrawerState.open() } }) {
                HomePagePostFeed(navController = navController, paddingValues = it)
            }

            2 -> ScaffoldComp(onMenuIconClick = { navScope.launch { navDrawerState.open() } }) {
                AdvertListPage(navController = navController, paddingValues = it)
            }

            3 -> ScaffoldComp(onMenuIconClick = { navScope.launch { navDrawerState.open() } }) {

                FirmListPage(navController = navController, paddingValues = it)
            }

            4 -> ScaffoldComp(onMenuIconClick = { navScope.launch { navDrawerState.open() } }) {

                AppliedAdvertsListPage(navController = navController, paddingValues = it)

            }

            5 -> ScaffoldComp(onMenuIconClick = { navScope.launch { navDrawerState.open() } }) {
                FormListPage(
                    navController = navController,
                    paddingValues = it,
                    formType = Constants.STUDENT
                )
            }

            6 -> ScaffoldComp(onMenuIconClick = { navScope.launch { navDrawerState.open() } }) {
                SurveyListPage(
                    navController = navController,
                    paddingValues = it,
                    surveyType = Constants.STUDENT
                )
            }

            7 -> ScaffoldComp(onMenuIconClick = { navScope.launch { navDrawerState.open() } }) {
                WeeklyReportListPage(navController = navController, paddingValues = it)
            }

            8 -> ScaffoldComp(onMenuIconClick = { navScope.launch { navDrawerState.open() } }) {
                FavouritePostListPage(
                    navController = navController,
                    paddingValues = it,
                    student_no = studentObject.student_no
                )
            }
        }
    }

    BackHandler(onBack = {
        exit_choice.value = true
    })
    if (exit_choice.value) {
        ExitQuestionComp(exit_choice_question = exit_choice)
    }
    if (log_out_choice.value) {
        LogOutQuestionComp(
            log_out_choice = log_out_choice,
            navController = navController,
            popUpScreen = Screen.StudentMainPage
        )
    }

}




