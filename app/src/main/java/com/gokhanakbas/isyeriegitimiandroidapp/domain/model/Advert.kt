package com.gokhanakbas.isyeriegitimiandroidapp.domain.model

import androidx.compose.runtime.MutableState
import java.util.UUID

data class Advert(
    var advert_id: String,
    var advert_title: String,
    var advert_details: String,
    var advert_criteriaList : MutableList<String>,
    var advert_startDate : String,
    var advert_endDate : String,
    var advert_firm_id: String,                     // İlan detayına gerek duymadığımız ve tıklandığında ilan detay sayfasında detayların çekilmesi için kullanıyoruz.
    var advert_firm_name :String,                   //İlan detaylarına gerek duymadığımız sadece firma adının yazıldığı yerlerde çekiyoruz.
    var advert_firm : Firm? =  null,                // İlan detaylarını çekerken kullanılacak olan kısım bu diğer yerlerde hep null tanımlanacak
    var advert_interviewers: MutableState<ArrayList<Interviewer>>, // Firmanın sadece kendi ilanlarını görüntülediği yerlerde detay olamyan yerlerde çekiyoruz.
    var advert_post_title : String,
    var id: String = UUID.randomUUID().toString()
)