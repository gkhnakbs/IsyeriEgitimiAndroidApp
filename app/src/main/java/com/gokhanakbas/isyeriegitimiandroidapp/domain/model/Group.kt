package com.gokhanakbas.isyeriegitimiandroidapp.domain.model

import androidx.compose.runtime.Immutable
import java.util.UUID

@Immutable
data class Group(
    var group_id: String,
    var group_name: String,
    var studentList: List<Student>,
    var group_creationDate: String,
    var id: String = UUID.randomUUID().toString()
) {
}