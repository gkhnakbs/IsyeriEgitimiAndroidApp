package com.gokhanakbas.isyeriegitimiandroidapp.presentation.util.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.gokhanakbas.isyeriegitimiandroidapp.ui.theme.GaziAcikMavi

@Composable
fun LoadingDialog(isLoading: Boolean) {

    if (isLoading) {
        Dialog(
            onDismissRequest = { /*TODO*/ },
            properties = DialogProperties(dismissOnClickOutside = false)
        ) {
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(15.dp))
                    .background(
                        GaziAcikMavi
                    ),
                contentAlignment = Alignment.Center
            ){
                CircularProgressIndicator(modifier=Modifier.padding(10.dp), color = Color.White)
            }
        }


    }
}