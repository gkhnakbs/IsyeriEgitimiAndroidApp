package com.gokhanakbas.isyeriegitimiandroidapp.presentation.util.pagecomponents.favouritePostListPage

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.gokhanakbas.isyeriegitimiandroidapp.presentation.util.components.LoadingDialog
import com.gokhanakbas.isyeriegitimiandroidapp.presentation.util.components.PostComponent
import com.gokhanakbas.isyeriegitimiandroidapp.presentation.util.pagecomponents.firmListPage.FirmCardContent

@Composable
fun FavouritePostListPage(
    navController: NavController,
    paddingValues: PaddingValues,
    student_no: String,
    viewModel: FavouritePostListPageViewModel = hiltViewModel()
) {

    LaunchedEffect(key1 = viewModel) {
        viewModel.getFavouritePosts(student_no)
    }

    val state by viewModel.state.collectAsStateWithLifecycle()

    FavouritePostListPageContent(
        navController = navController,
        paddingValues = paddingValues,
        favouritePostListPageState = state
    )

}

@Composable
fun FavouritePostListPageContent(navController: NavController,paddingValues: PaddingValues,favouritePostListPageState: FavouritePostListPageState) {

    val postList=favouritePostListPageState.postList

    LoadingDialog(isLoading = favouritePostListPageState.isLoading)

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(
            count = postList.count(),
            key = {
                postList[it].id
            },
            itemContent = {
                val postObject = postList[it]
                PostComponent(navController = navController, post = postObject)
            }
        )
    }
}