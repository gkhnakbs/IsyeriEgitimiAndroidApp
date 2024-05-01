package com.gokhanakbas.isyeriegitimiandroidapp.presentation.util.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.gokhanakbas.isyeriegitimiandroidapp.R
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Skill
import com.gokhanakbas.isyeriegitimiandroidapp.ui.theme.GaziAcikMavi
import com.gokhanakbas.isyeriegitimiandroidapp.ui.theme.GaziKoyuMavi

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SkillEntryComp(
    skillEntryState: MutableState<Boolean>,
    skill: Skill
) {
    val singleChoiceSelected = remember {
        mutableIntStateOf(0)
    }

    val levelList = listOf(
        stringResource(id = R.string.baslangic_seviye),
        stringResource(id = R.string.orta_seviye),
        stringResource(id = R.string.ileri_seviye)
    )

    val tf_skillName = remember {
        mutableStateOf("")
    }

    val tf_skillLevel = remember {
        mutableStateOf("")
    }

    val tf_skillNameError = remember {
        mutableStateOf(false)
    }

    val tf_skillLevelError = remember {
        mutableStateOf(false)
    }

    Dialog(onDismissRequest = { skillEntryState.value = false }) {
        Box(modifier = Modifier.size(450.dp, 400.dp).background(Color.White, RoundedCornerShape(20.dp))) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(30.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(id = R.string.yetenek_ekle),
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Black,
                        textDecoration = TextDecoration.Underline
                    )
                }


                OutlinedTextField(
                    value = tf_skillName.value,
                    onValueChange = { tf_skillName.value = it },
                    modifier = Modifier.fillMaxWidth(),
                    isError = tf_skillNameError.value,
                    singleLine = true,
                    label = { Text(text = stringResource(id = R.string.yetenek_baslik)) }
                )

                Spacer(modifier = Modifier.height(5.dp))


                SingleChoiceSegmentedButtonRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.8f, fill = true)
                        .padding(start = 10.dp, end = 10.dp)
                ) {
                    repeat(levelList.size) { index ->
                        SegmentedButton(
                            selected = singleChoiceSelected.intValue == index,
                            onClick = {
                                singleChoiceSelected.intValue = index
                                tf_skillLevel.value = levelList[index]
                            },
                            shape = RoundedCornerShape(8.dp),
                            colors = SegmentedButtonDefaults.colors(
                                inactiveBorderColor = GaziAcikMavi,
                                activeBorderColor = GaziKoyuMavi,
                                activeContainerColor = GaziAcikMavi,
                                inactiveContainerColor = Color.White,
                                activeContentColor = Color.White,
                                inactiveContentColor = Color.DarkGray
                            )
                        ) {
                            Text(text = levelList[index], fontSize = 16.sp)
                        }
                    }

                }



                Spacer(modifier = Modifier.height(5.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End
                ) {
                    OutlinedButton(
                        onClick = {
                            skillEntryState.value = false
                        },
                        shape = RoundedCornerShape(20.dp),
                        colors = ButtonDefaults.outlinedButtonColors(
                            containerColor = Color.White
                        ),
                        border = BorderStroke(0.5.dp, Color.Red)
                    ) {
                        Text(text = stringResource(id = R.string.iptal_et), fontSize = 20.sp)
                    }
                    Spacer(modifier = Modifier.width(5.dp))
                    OutlinedButton(
                        onClick = {
                            if (tf_skillName.value.trim()
                                    .isEmpty()
                            ) {
                                tf_skillNameError.value = true
                            }else {
                                skill.skill_name = tf_skillName.value.trim()
                                if (tf_skillLevel.value=="Beginning" || tf_skillLevel.value== "Başlangıç") skill.skill_level = "Başlangıç"
                                else if (tf_skillLevel.value=="Intermediate" || tf_skillLevel.value== "Orta") skill.skill_level = "Orta"
                                else skill.skill_level = "İleri"
                                skillEntryState.value=false
                            }
                        },
                        shape = RoundedCornerShape(20.dp),
                        colors = ButtonDefaults.outlinedButtonColors(
                            containerColor = Color.White
                        ),
                        border = BorderStroke(0.5.dp, GaziKoyuMavi)
                    ) {
                        Text(text = stringResource(id = R.string.ekle), fontSize = 20.sp)
                    }
                }

            }
        }
    }
}