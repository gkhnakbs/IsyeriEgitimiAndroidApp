package com.gokhanakbas.isyeriegitimiandroidapp.domain.model

import java.util.UUID

data class Advert(
    var advert_id: String = "",
    var advert_title: String,
    var advert_details: String,
    var advert_startDate : String,
    var advert_endDate : String,
    var advert_firm: Firm =Firm("","","","","","","",""),
    var advert_interviewers: List<Student> = emptyList(),
    var advert_post_title : String ,
    var id: String = UUID.randomUUID().toString()
)