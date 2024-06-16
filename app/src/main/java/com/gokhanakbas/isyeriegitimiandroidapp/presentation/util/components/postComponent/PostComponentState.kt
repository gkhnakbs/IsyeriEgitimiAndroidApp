package com.gokhanakbas.isyeriegitimiandroidapp.presentation.util.components.postComponent

data class PostComponentState(
    var successfullySaved : Boolean = false,
    var succesfullyRemoved : Boolean = false,
    var error : String? = null
)