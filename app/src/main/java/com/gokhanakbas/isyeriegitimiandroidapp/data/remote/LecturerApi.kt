package com.gokhanakbas.isyeriegitimiandroidapp.data.remote

import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Group
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Lecturer
import com.gokhanakbas.isyeriegitimiandroidapp.util.Constants
import javax.inject.Inject

class LecturerApi @Inject constructor(private val databaseConnection: DatabaseConnection) {

    val connection = databaseConnection.connection

    fun getLecturersInformation(lecturer_id:String): Lecturer {
        val lecturer = Lecturer("", "", "", "", "", "", "", "", "")
        val statement = connection.createStatement()
        val result = statement.executeQuery("select * from izleyici where izleyici_id=${lecturer_id.toBigDecimal()}")
        while (result.next()) {
            lecturer.lecturer_id = result.getString("izleyici_id")
            lecturer.lecturer_faculty = result.getString("izleyici_fakulte")
            lecturer.lecturer_department = result.getString("izleyici_bolum")
            lecturer.lecturer_name =
                result.getString("izleyici_ad") + " " + result.getString("izleyici_soyad")
            lecturer.lecturer_degree = "Derece Prof."
            lecturer.lecturer_info = result.getString("izleyici_hakkinda")
            lecturer.lecturer_mail = result.getString("izleyici_eposta")
            lecturer.lecturer_specificField = "Spesifik Alan"
        }
        return lecturer
    }

    fun getLecturerGroups(): List<Group> {

        return emptyList()
    }

    fun getLecturers(): List<Lecturer> {
        val lecturerList = ArrayList<Lecturer>()
        val statement = connection.createStatement()
        val result =
            statement.executeQuery("select * from izleyici")
        while (result.next()) {
            lecturerList.add(
                Lecturer(
                    result.getString("izleyici_id"),
                    result.getString("izleyici_ad") + " " + result.getString("izleyici_soyad"),
                    result.getString("izleyici_fakulte"),
                    result.getString("izleyici_bolum"),
                    "Spesifik Alan",
                    "Derece Prof.",
                    result.getString("izleyici_eposta"),
                    result.getString("izleyici_hakkinda"),
                    result.getString("izleyici_parola")
                )
            )
        }
        return lecturerList
    }

    fun saveLecturerInformation(lecturer: Lecturer) : Boolean{
        val statement = connection.createStatement()
        val result=statement.executeUpdate("update izleyici set izleyici_hakkinda='${lecturer.lecturer_info}' , izleyici_eposta='${lecturer.lecturer_mail}'  where lecturer_id=${lecturer.lecturer_id.toBigDecimal()}")
        return result>0
    }

    fun saveLecturerPassword(lecturer: Lecturer):Boolean{
        val statement = connection.createStatement()
        val result=statement.executeUpdate("update izleyici set izleyici_parola='${lecturer.lecturer_password}' where lecturer_id=${lecturer.lecturer_id.toBigDecimal()}")
        return result>0
    }

}