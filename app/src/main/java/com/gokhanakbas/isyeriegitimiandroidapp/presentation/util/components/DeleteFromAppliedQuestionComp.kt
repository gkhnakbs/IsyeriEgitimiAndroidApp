package com.gokhanakbas.isyeriegitimiandroidapp.presentation.util.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.gokhanakbas.isyeriegitimiandroidapp.R
import com.gokhanakbas.isyeriegitimiandroidapp.presentation.util.pagecomponents.appliedAdvertsListPage.AppliedAdvertsListPageViewModel
import com.gokhanakbas.isyeriegitimiandroidapp.util.Constants

@Composable
fun DeleteFromAppliedQuestionComp(
    deleteChoiceQuestion: MutableState<Boolean>,
    advert_id:String,
    viewModel: AppliedAdvertsListPageViewModel= hiltViewModel()
)  {
    var decision = false
    if (deleteChoiceQuestion.value) {
        AlertDialog(onDismissRequest = { }
            , confirmButton = {
            OutlinedButton(onClick = {
                decision=true
            }) {
                Text(text = stringResource(id = R.string.sil))
            }
        }, dismissButton = {
            OutlinedButton(onClick = {
                decision = false
                deleteChoiceQuestion.value=false
            }) {
                Text(text = stringResource(id = R.string.iptal_et))
            }
        }, title = { Text(text = stringResource(id = R.string.basvuruyu_silmek_istedigine_emin_misin), fontSize = 24.sp)})
    }
    /*LaunchedEffect(key1 = decision) {
        viewModel.deleteAdvertFromApplied(advert_id,Constants.STUDENT_NO)
        deleteChoiceQuestion.value=false
    }*/

}