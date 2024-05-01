package com.gokhanakbas.isyeriegitimiandroidapp.presentation.util.pagecomponents.advertListPage

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.gokhanakbas.isyeriegitimiandroidapp.R
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Advert
import com.gokhanakbas.isyeriegitimiandroidapp.presentation.navigation.Screen
import com.gokhanakbas.isyeriegitimiandroidapp.presentation.util.components.LoadingDialog
import com.gokhanakbas.isyeriegitimiandroidapp.ui.theme.GaziAcikMavi
import com.gokhanakbas.isyeriegitimiandroidapp.ui.theme.GaziKoyuMavi

@Composable
fun AdvertListPage(
    navController: NavController,
    paddingValues: PaddingValues,
    viewModel: AdvertListPageViewModel = hiltViewModel()
) {

    LaunchedEffect(key1 = viewModel) {
        viewModel.getAdverts()
    }

    val state by viewModel.state.collectAsStateWithLifecycle()

    AdvertListPageContent(
        navController = navController,
        paddingValues = paddingValues,
        state = state
    )

}


@Composable
fun AdvertListPageContent(
    navController: NavController,
    paddingValues: PaddingValues,
    state: AdvertListPageState
) {

    val tf_advert_label = remember {
        mutableStateOf("")
    }

    val advertList = state.advert_list

    val focus = LocalFocusManager.current

    LoadingDialog(isLoading = state.isLoading)

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
                value = tf_advert_label.value,
                onValueChange = {
                    tf_advert_label.value = it
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
                                tf_advert_label.value = ""
                                focus.clearFocus()
                            })
                },
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.ilan),
                        color = Color.Gray
                    )
                },
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.White,
                    focusedContainerColor = Color.White
                ),
                modifier = Modifier.fillMaxWidth(),
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
                count = advertList.count(),
                key = {
                    advertList[it].id
                },
                itemContent = {
                    val advert_object = advertList[it]
                    AdvertCardContent(navController = navController, advert_object = advert_object)
                }
            )
        }
    }
}


@Composable
fun AdvertCardContent(navController: NavController, advert_object: Advert) {
    Card(
        modifier = Modifier
            .padding(top = 5.dp, bottom = 5.dp)
            .heightIn(80.dp),
    ) {
        Row(modifier = Modifier
            .fillMaxSize()
            .clickable {

            }) {
            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(10.dp)
            ) {
                Column(modifier=Modifier.weight(0.7f)){
                    Text(
                        text = advert_object.advert_title,
                        fontSize = 22.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.requiredWidth(190.dp)
                    )
                    Spacer(
                        modifier = Modifier
                            .height(0.5.dp)
                            .background(Color.LightGray)
                    )
                    Text(
                        text = advert_object.advert_firm.firm_name,
                        fontSize = 18.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.requiredWidth(190.dp)
                    )
                }
                Row(
                    modifier=Modifier.weight(0.3f),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedButton(
                        onClick = {
                            navController.navigate(Screen.AdvertPage.passNavigate(advert_object.advert_id))
                        }, elevation = ButtonDefaults.buttonElevation(
                            defaultElevation = 5.dp
                        ), colors = ButtonDefaults.elevatedButtonColors(
                            containerColor = GaziAcikMavi,
                            contentColor = GaziKoyuMavi
                        )
                    ) {
                        Text(
                            text = stringResource(id = R.string.ilana_git),
                            fontSize = 14.sp,
                            textAlign = TextAlign.Center
                        )
                    }
                }

            }
        }

    }
}