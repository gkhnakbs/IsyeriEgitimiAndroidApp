package com.gokhanakbas.isyeriegitimiandroidapp.domain.model

data class NetworkError(
    val error: DatabaseError,
    val t: Throwable? = null  //Opsiyonel özellik bu
) { init {
    println("error : " + t!!.localizedMessage)
}
}

enum class DatabaseError(val message: String) {
    SQLError("SQL Error"),
    NetworkError("Network Error"),
    UnknownResponse("Unknown Response"),
    UnknownError("UnknownError")
}

