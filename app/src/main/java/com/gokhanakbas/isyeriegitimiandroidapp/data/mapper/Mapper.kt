package com.gokhanakbas.isyeriegitimiandroidapp.data.mapper

import android.net.http.HttpException
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.DatabaseError
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.NetworkError
import java.io.IOException
import java.sql.SQLException

fun Throwable.toNetworkError (): NetworkError {
    val error = when (this){
        is IOException -> DatabaseError.NetworkError
        is SQLException ->DatabaseError.SQLError
        is RuntimeException ->DatabaseError.UnknownResponse
        else -> DatabaseError.UnknownError
    }
    return NetworkError(
        error = error,
        t = this)
}
