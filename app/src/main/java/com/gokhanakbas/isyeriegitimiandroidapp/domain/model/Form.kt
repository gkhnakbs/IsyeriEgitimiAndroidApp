package com.gokhanakbas.isyeriegitimiandroidapp.domain.model

import androidx.compose.runtime.Immutable
import java.util.UUID

@Immutable
data class Form(
    var form_id: Int,
    var form_name: String,
    var id: String = UUID.randomUUID().toString()
) {
}