package com.gokhanakbas.isyeriegitimiandroidapp.domain.model

import java.util.UUID

data class Commission(
    var commission_id: String,
    var commission_name: String,
    var commission_faculty: String,
    var commission_department: String,
    var commission_specificField : String,
    var commission_degree: String,
    var commission_mail : String,
    var commission_info:String,
    var id : String= UUID.randomUUID().toString()
)