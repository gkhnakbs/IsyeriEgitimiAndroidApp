package com.gokhanakbas.isyeriegitimiandroidapp.domain.model

import androidx.compose.runtime.MutableState
import java.util.UUID

data class AppliedAdvert(
    var appliedAdvert_id: String,
    var appliedAdvert_title: String,
    var appliedAdvert_details: String,
    var appliedAdvert_startDate : String,
    var appliedAdvert_endDate : String,
    var appliedAdvert_firm_id: String,                     // İlan detayına gerek duymadığımız ve tıklandığında ilan detay sayfasında detayların çekilmesi için kullanıyoruz.
    var appliedAdvert_firm_name :String,                   //İlan detaylarına gerek duymadığımız sadece firma adının yazıldığı yerlerde çekiyoruz.
    var appliedAdvert_firm : Firm? =  null,                // İlan detaylarını çekerken kullanılacak olan kısım bu diğer yerlerde hep null tanımlanacak
    var appliedAdvert_interviewers: MutableState<ArrayList<Interviewer>>, // Firmanın sadece kendi ilanlarını görüntülediği yerlerde detay olamyan yerlerde çekiyoruz.
    var appliedAdvert_post_title : String,
    var appliedAdvert_applyDate : String,
    var id: String = UUID.randomUUID().toString()

)
