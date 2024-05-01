package com.gokhanakbas.isyeriegitimiandroidapp.domain.model

import java.util.UUID

data class Post(
    val post_id: String,
    val post_label: String,
    val post_date: String,
    val post_photo:String,
    val post_advert_id: String,
    val post_firm : Firm = Firm("0","","","","","","",""),
    var post_isFavourite : Boolean,
    val id: String = UUID.randomUUID().toString()
)