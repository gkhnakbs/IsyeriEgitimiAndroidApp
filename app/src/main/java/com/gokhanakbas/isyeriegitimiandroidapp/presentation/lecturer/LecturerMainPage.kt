package com.gokhanakbas.isyeriegitimiandroidapp.presentation.lecturer

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.gokhanakbas.isyeriegitimiandroidapp.R
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.NavItem
import com.gokhanakbas.isyeriegitimiandroidapp.presentation.navigation.Screen
import com.gokhanakbas.isyeriegitimiandroidapp.presentation.util.components.ExitQuestionComp
import com.gokhanakbas.isyeriegitimiandroidapp.presentation.util.components.LoadingDialog
import com.gokhanakbas.isyeriegitimiandroidapp.presentation.util.components.LogOutQuestionComp
import com.gokhanakbas.isyeriegitimiandroidapp.presentation.util.components.ScaffoldComp
import com.gokhanakbas.isyeriegitimiandroidapp.presentation.util.pagecomponents.firmListPage.FirmListPage
import com.gokhanakbas.isyeriegitimiandroidapp.presentation.util.pagecomponents.formListPage.FormListPage
import com.gokhanakbas.isyeriegitimiandroidapp.presentation.util.pagecomponents.homePagePostFeed.HomePagePostFeed
import com.gokhanakbas.isyeriegitimiandroidapp.presentation.util.pagecomponents.studentListPage.StudentListPage
import com.gokhanakbas.isyeriegitimiandroidapp.presentation.util.pagecomponents.surveyListPage.SurveyListPage
import com.gokhanakbas.isyeriegitimiandroidapp.ui.theme.Mavi2
import com.gokhanakbas.isyeriegitimiandroidapp.util.Constants
import kotlinx.coroutines.launch

var lastPage = 1

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LecturerMainPage(navController: NavController, lecturer_id: String,viewModel: LecturerMainPageViewModel= hiltViewModel()) {

    Constants.LECTURER_ID=lecturer_id

    LaunchedEffect(key1 = viewModel) {
        viewModel.getLecturersInformation(lecturer_id)
    }

    val state by viewModel.state.collectAsStateWithLifecycle()

    LecturerMainPageContent(navController = navController, lecturerMainPageState = state)

}

@Composable
fun LecturerMainPageContent(navController: NavController,lecturerMainPageState: LecturerMainPageState) {

    LoadingDialog(isLoading = lecturerMainPageState.isLoading)

    val lecturerObject= lecturerMainPageState.lecturer

    val navDrawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val navScope = rememberCoroutineScope()
    val navDrawerSecilen = remember {
        mutableIntStateOf(lastPage)
    }
    val log_out_choice = remember { mutableStateOf(false) }
    val exit_choice = remember { mutableStateOf(false) }


    val liste = listOf(
        NavItem(
            1,
            stringResource(id = R.string.ana_sayfa),
            painterResource(id = R.drawable.home_icon)
        ),
        NavItem(
            2,
            stringResource(id = R.string.ogrenci),
            painterResource(id = R.drawable.student_thin_icon)
        ),
        NavItem(
            3,
            stringResource(id = R.string.firma),
            painterResource(id = R.drawable.workplace_icon)
        ),
        NavItem(
            4,
            stringResource(id = R.string.gruplar),
            painterResource(id = R.drawable.group_icon)
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
                            //Izleyicinin kendi sayfasina gidecek
                            navController.navigate(Screen.LecturerPage.passNavigate(lecturer_id = lecturerObject.lecturer_id))
                        }
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.gazi_rektorluk),
                        contentDescription = "",
                        modifier =
                        Modifier
                            .alpha(0.6f)
                            .fillMaxSize()
                            .aspectRatio(1.4f)
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start, modifier = Modifier
                            .align(Alignment.Center)
                            .fillMaxSize()
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.aydin_resim),
                            contentDescription = "Student Photo",
                            modifier = Modifier
                                .clip(
                                    RoundedCornerShape(40.dp)
                                )
                                .border(4.dp, Color.White)
                                .size(40.dp), contentScale = ContentScale.FillBounds
                        )
                        Spacer(modifier = Modifier.size(5.dp, 0.dp))
                        Text(
                            text = lecturerObject.lecturer_degree + lecturerObject.lecturer_name,
                            fontSize = 18.sp,
                            color = Color.White
                        )
                    }

                }
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
                            unselectedContainerColor = Color.Transparent,
                            selectedContainerColor = Color.White,
                            selectedTextColor = Color.Black,
                            unselectedTextColor = Color.White,
                            selectedIconColor = Color.Black,
                            unselectedIconColor = Color.White

                        )
                    )
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(Color.White)
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
                    }, colors = NavigationDrawerItemDefaults.colors(
                        unselectedContainerColor = Color.Transparent,
                        selectedContainerColor = Color.White,
                        selectedTextColor = Color.Black,
                        unselectedTextColor = Color.White,
                        selectedIconColor = Color.Black,
                        unselectedIconColor = Color.White
                    )
                )
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(Color.White)
                )
            }
        }, drawerState = navDrawerState, gesturesEnabled = navDrawerState.isOpen
    ) {
        when (navDrawerSecilen.intValue) {
            1 -> ScaffoldComp(onMenuIconClick = { navScope.launch { navDrawerState.open() } }) {
                HomePagePostFeed(navController = navController, paddingValues = it)
            }

            2 -> ScaffoldComp(onMenuIconClick = { navScope.launch { navDrawerState.open() } }) {
                StudentListPage(navController = navController, paddingValues = it)
            }

            3 -> ScaffoldComp(onMenuIconClick = { navScope.launch { navDrawerState.open() } }) {
                FirmListPage(navController = navController, paddingValues = it)
            }

            4 -> ScaffoldComp(onMenuIconClick = { navScope.launch { navDrawerState.open() } }) {
                GroupsPageForLecturer(navController = navController, paddingValues = it)
            }

            5 -> ScaffoldComp(onMenuIconClick = { navScope.launch { navDrawerState.open() } }) {
                FormListPage(
                    navController = navController,
                    paddingValues = it,
                    formType = Constants.LECTURER
                )
            }

            6 -> ScaffoldComp(onMenuIconClick = { navScope.launch { navDrawerState.open() } }) {
                SurveyListPage(
                    navController = navController,
                    paddingValues = it,
                    surveyType = Constants.LECTURER
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
            popUpScreen = Screen.LecturerMainPage
        )
    }
}

