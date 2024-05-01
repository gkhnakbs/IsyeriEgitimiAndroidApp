package com.gokhanakbas.isyeriegitimiandroidapp.domain.model

import java.util.UUID
import javax.annotation.concurrent.Immutable

@Immutable
data class Skill(
    var skill_id:String,
    var skill_name:String,
    var skill_level:String,
    var skill_additionDate:String,
    var id : String = UUID.randomUUID().toString()
)