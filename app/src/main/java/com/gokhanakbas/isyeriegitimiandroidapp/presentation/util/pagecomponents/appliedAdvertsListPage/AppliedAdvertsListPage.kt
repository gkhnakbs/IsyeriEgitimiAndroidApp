package com.gokhanakbas.isyeriegitimiandroidapp.presentation.util.pagecomponents.appliedAdvertsListPage

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.gokhanakbas.isyeriegitimiandroidapp.presentation.util.components.AdvertComp
import com.gokhanakbas.isyeriegitimiandroidapp.presentation.util.components.LoadingDialog

@Composable
fun AppliedAdvertsListPage(
    navController: NavController,
    paddingValues: PaddingValues,
    viewModel: AppliedAdvertsListPageViewModel = hiltViewModel()
) {

    LaunchedEffect(key1 = viewModel) {
        viewModel.getAppliedAdverts()
    }

    val state by viewModel.state.collectAsStateWithLifecycle()

    AppliedAdvertsListPageContent(
        navController = navController,
        paddingValues = paddingValues,
        appliedAdvertsListPageState = state
    )

}

@Composable
fun AppliedAdvertsListPageContent(
    navController: NavController,
    paddingValues: PaddingValues,
    appliedAdvertsListPageState: AppliedAdvertsListPageState
) {

    LoadingDialog(isLoading = appliedAdvertsListPageState.isLoading)
    
    val lazyState = rememberLazyListState()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        contentPadding = paddingValues,
        state = lazyState
    ) {
        items(count = appliedAdvertsListPageState.advert_list.size,
            key = { appliedAdvertsListPageState.advert_list[it].id }) { index ->
            AdvertComp(
                navController = navController,
                advert = appliedAdvertsListPageState.advert_list[index]
            )
        }
    }

}