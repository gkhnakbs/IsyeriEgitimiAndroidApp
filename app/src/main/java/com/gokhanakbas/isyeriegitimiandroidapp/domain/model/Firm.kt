package com.gokhanakbas.isyeriegitimiandroidapp.domain.model

import androidx.compose.runtime.Immutable
import java.util.UUID

@Immutable
data class Firm(
    var firm_id: String,
    var firm_name: String,
    var firm_sector : String,
    var firm_info : String,
    var firm_logo : String,
    var firm_mail : String,
    var firm_address : String,
    var firm_phone : String,
    var id: String = UUID.randomUUID().toString()
)