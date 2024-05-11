package com.gokhanakbas.isyeriegitimiandroidapp.presentation.util.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.gokhanakbas.isyeriegitimiandroidapp.R
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Student
import com.gokhanakbas.isyeriegitimiandroidapp.presentation.navigation.Screen
import com.gokhanakbas.isyeriegitimiandroidapp.ui.theme.GaziAcikMavi
import com.gokhanakbas.isyeriegitimiandroidapp.ui.theme.GaziKoyuMavi

@Composable
fun StudentListRowComp(navController: NavController,student: Student ) {
    Card(
        modifier = Modifier
            .padding(top = 5.dp, bottom = 5.dp)
            .heightIn(55.dp),
    ) {
        Row(modifier = Modifier
            .fillMaxSize()) {
            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(start = 5.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.student_thin_icon),
                    contentDescription = "", modifier = Modifier.size(30.dp)
                )
                Text(
                    text = student.student_name,
                    fontSize = 22.sp, modifier = Modifier.requiredWidth(190.dp)
                )
                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedButton(
                        onClick = {
                            navController.navigate(Screen.StudentPage.passNavigate(student.student_no))

                        }, elevation = ButtonDefaults.buttonElevation(
                            defaultElevation = 5.dp
                        ), colors = ButtonDefaults.elevatedButtonColors(
                            containerColor = GaziAcikMavi,
                            contentColor = GaziKoyuMavi
                        )
                    ) {
                        Text(
                            text = stringResource(id = R.string.ogrenciyi_goruntule),
                            fontSize = 14.sp,
                            textAlign = TextAlign.Center
                        )
                    }
                }

            }
        }

    }
}