package com.gokhanakbas.isyeriegitimiandroidapp.domain.model

import java.util.UUID

data class Commission(
    var commission_id: String,
    var commission_name: String,
    var commission_faculty: String,
    var commission_department: String,
    var commission_mail : String,
    var id : String= UUID.randomUUID().toString()
)