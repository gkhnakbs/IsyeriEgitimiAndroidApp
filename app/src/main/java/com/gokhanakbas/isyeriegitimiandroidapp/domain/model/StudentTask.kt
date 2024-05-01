package com.gokhanakbas.isyeriegitimiandroidapp.domain.model

import java.util.UUID

data class StudentTask(
    var task_id: Int,
    var taskLabel: String,
    var taskState: String,
    var task_startDate: String,
    var task_endDate: String,
    val id: String = UUID.randomUUID().toString()
) {
}