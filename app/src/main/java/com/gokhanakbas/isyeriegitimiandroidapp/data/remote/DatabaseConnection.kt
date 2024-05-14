package com.gokhanakbas.isyeriegitimiandroidapp.data.remote

import android.util.Log
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.android.scopes.ViewScoped
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.sql.Connection
import java.sql.DriverManager
import javax.inject.Inject


class DatabaseConnection {

    var connection : Connection

    init {
        connection = createConnection()
    }
    private fun createConnection(): Connection {
            try {
                Class.forName("org.postgresql.Driver")
                connection= DriverManager.getConnection(
                    "jdbc:postgresql://10.0.2.2:5432/gaziOnline1",
                    "postgres",
                    "admin"
                )
                Log.i("databaseState :","Başarılı")
            } catch (e: Exception) {
                Log.d("databaseState : ", "Sorun : "+ e.localizedMessage!!.toString())
            }

        return connection
    }

}
