package com.gokhanakbas.isyeriegitimiandroidapp.presentation.util.pagecomponents.homePagePostFeed

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.gokhanakbas.isyeriegitimiandroidapp.R
import com.gokhanakbas.isyeriegitimiandroidapp.presentation.util.components.LoadingDialog
import com.gokhanakbas.isyeriegitimiandroidapp.presentation.util.components.postComponent.PostComponent
import com.gokhanakbas.isyeriegitimiandroidapp.util.Constants


@Composable
fun HomePagePostFeed(
    navController: NavController,
    paddingValues: PaddingValues,
    homePagePostFeedViewModel: HomePagePostFeedViewModel = hiltViewModel(),
) {

    LaunchedEffect(key1 = homePagePostFeedViewModel) {
        homePagePostFeedViewModel.getPosts()
        homePagePostFeedViewModel.getFavouritePosts(Constants.STUDENT_NO)
    }

    val state by homePagePostFeedViewModel.state.collectAsStateWithLifecycle()

    state.post.forEach{ post ->
        state.favouritePostList.forEach { favouritePost ->
            if (post.post_advert_id == favouritePost.post_advert_id) {
                post.post_isFavourite = true
            }
        }
    }

    HomePagePostFeedContent(
        navController = navController,
        paddingValues = paddingValues,
        homePagePostFeedState = state
    )

}

@Composable
fun HomePagePostFeedContent(
    navController: NavController,
    paddingValues: PaddingValues,
    homePagePostFeedState: HomePagePostFeedState
) {

    LoadingDialog(isLoading = homePagePostFeedState.isLoading)


    if (homePagePostFeedState.post.isNotEmpty()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues)
        ) {
            items(count = homePagePostFeedState.post.size,
                key = { homePagePostFeedState.post[it].id })
            { index ->
                PostComponent(
                    navController = navController,
                    post = homePagePostFeedState.post[index]

                )
            }
        }
    } else {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = stringResource(id = R.string.suan_icin_bos_gozukuyor), color = Color.LightGray, fontSize = 24.sp)
        }
    }


}