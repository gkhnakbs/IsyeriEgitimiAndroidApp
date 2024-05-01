package com.gokhanakbas.isyeriegitimiandroidapp.presentation.util.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.gokhanakbas.isyeriegitimiandroidapp.R
import com.gokhanakbas.isyeriegitimiandroidapp.ui.theme.GaziAcikMavi

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarComp(onMenuIconClick : () ->  Unit,scrollBehavior : TopAppBarScrollBehavior) {
    androidx.compose.material3.TopAppBar(title = {
        Text(
            text = stringResource(id = R.string.app_name),
            fontWeight = FontWeight.Bold
        )
    }, navigationIcon = {
        IconButton(onClick = onMenuIconClick) {
            Icon(
                painter = painterResource(id = R.drawable.three_line_icon),
                contentDescription = "navigationBar Icon",
                modifier = Modifier.size(30.dp)
            )
        }
    }, colors = TopAppBarDefaults.topAppBarColors(
        containerColor = GaziAcikMavi
    ), scrollBehavior = scrollBehavior
    )
}