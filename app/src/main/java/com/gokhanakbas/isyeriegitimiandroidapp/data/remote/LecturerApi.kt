package com.gokhanakbas.isyeriegitimiandroidapp.data.remote

import androidx.compose.runtime.mutableStateListOf
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Firm
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Group
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Lecturer
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Student
import com.gokhanakbas.isyeriegitimiandroidapp.util.Constants
import javax.inject.Inject

class LecturerApi @Inject constructor(private val databaseConnection: DatabaseConnection) {

    val connection = databaseConnection.connection

    fun loginLecturer(lecturer_id: String, lecturer_password: String): Lecturer {
        val lecturer = Lecturer("","","","","","","","","")
        val statement = connection.prepareStatement("select *  from izleyici where izleyici_id= ?  AND izleyici_parola= ? ")
        statement.setBigDecimal(1,lecturer_id.toBigDecimal())
        statement.setString(2,lecturer_password)
        val result = statement.executeQuery()
        if (result.next()) {
            lecturer.lecturer_id=lecturer_id
            lecturer.lecturer_name=result.getString("izleyici_ad")+" "+result.getString("izleyici_soyad")
            lecturer.lecturer_faculty=result.getString("izleyici_fakulte")
            lecturer.lecturer_department=result.getString("izleyici_bolum")
            lecturer.lecturer_info=result.getString("izleyici_hakkinda") ?: ""
            lecturer.lecturer_password=result.getString("izleyici_parola")
            lecturer.lecturer_mail=result.getString("izleyici_eposta") ?: ""
            lecturer.lecturer_specificField=""
            lecturer.lecturer_degree=""
        }
        return lecturer
    }

    fun getLecturersInformation(lecturer_id:String): Lecturer {
        val lecturer = Lecturer("", "", "", "", "", "", "", "", "")
        val statement = connection.prepareStatement("select * from izleyici where izleyici_id= ? ")
        statement.setBigDecimal(1,lecturer_id.toBigDecimal())
        val result = statement.executeQuery()
        while (result.next()) {
            lecturer.lecturer_id = result.getString("izleyici_id")
            lecturer.lecturer_faculty = result.getString("izleyici_fakulte")
            lecturer.lecturer_department = result.getString("izleyici_bolum")
            lecturer.lecturer_name =
                result.getString("izleyici_ad") + " " + result.getString("izleyici_soyad")
            lecturer.lecturer_degree = ""
            lecturer.lecturer_info = result.getString("izleyici_hakkinda") ?: ""
            lecturer.lecturer_mail = result.getString("izleyici_eposta") ?: ""
            lecturer.lecturer_password = result.getString("izleyici_parola")
            lecturer.lecturer_specificField = "Spesifik Alan"
        }
        return lecturer
    }

    fun getLecturerGroups(lecturer_id: String): List<Group> {
        val groups = ArrayList<Group>()
        val statement=connection.prepareStatement("Select * FROM ogrenci_grup where izleyici_id= ? ")
        statement.setBigDecimal(1,lecturer_id.toBigDecimal())
        val result=statement.executeQuery()
        while (result.next()){
            groups.add(
                Group(
                    result.getBigDecimal("grup_id").toString(),
                    result.getString("grup_ad"),
                    getGroupStudents(result.getString("grup_id")),
                    result.getString("grup_olusturulmaTarihi")
                )
            )
        }
        return groups
    }

    private fun getGroupStudents(grup_id : String) : List<Student> {
        val ogrenciList = ArrayList<Student>()
        val statement = connection.prepareStatement("Select o.*,f.* from gruptaki_ogrenciler as go JOIN ogrenci as o ON o.ogrenci_no=go.ogrenci_no JOIN firma as f ON f.firma_id=o.firma_id where grup_id= ? ")
        statement.setBigDecimal(1,grup_id.toBigDecimal())
        val result=statement.executeQuery()
        while (result.next()){
            ogrenciList.add(
                Student(
                    result.getBigDecimal("ogrenci_no").toString(),
                    result.getString("ogrenci_ad") + " " + result.getString("ogrenci_soyad"),
                    result.getString("ogrenci_yas"),
                    result.getString("ogrenci_fakulte"),
                    result.getString("ogrenci_bolum"),
                    result.getString("ogrenci_kimlik_no"),
                    result.getString("ogrenci_sinif"),
                    result.getString("ogrenci_agno"),
                    result.getString("ogrenci_hakkinda") ?: "",
                    result.getString("ogrenci_adres") ?: "",
                    result.getString("ogrenci_tel_no") ?: "",
                    result.getString("ogrenci_eposta") ?: "",
                    Firm(
                        result.getBigDecimal("firma_id").toString(),
                        result.getString("firma_ad"),
                        result.getString("firma_sektor") ?: "",
                        result.getString("firma_hakkinda") ?: "",
                        result.getString("firma_logo") ?: "",
                        result.getString("firma_eposta") ?: "",
                        result.getString("firma_adres") ?: "",
                        "",
                        ""
                    ),
                    result.getString("ogrenci_parola"),
                    mutableStateListOf()
                )
            )
        }
        return ogrenciList
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
                    "",
                    result.getString("izleyici_eposta")  ?: "",
                    result.getString("izleyici_hakkinda")  ?: "",
                    result.getString("izleyici_parola")
                )
            )
        }
        return lecturerList
    }

    fun saveLecturerInformation(lecturer: Lecturer) : Boolean{
        val statement = connection.prepareStatement("update izleyici set izleyici_hakkinda= ?  , izleyici_eposta= ?   where izleyici_id= ? ")
        statement.setString(1,lecturer.lecturer_info)
        statement.setString(2,lecturer.lecturer_mail)
        statement.setBigDecimal(3,lecturer.lecturer_id.toBigDecimal())
        val result=statement.executeUpdate()
        return result>0
    }

    fun saveLecturerPassword(lecturer: Lecturer):Boolean{
        val statement = connection.prepareStatement("update izleyici set izleyici_parola= ? where izleyici_id= ? ")
        statement.setString(1,lecturer.lecturer_password)
        statement.setBigDecimal(2,lecturer.lecturer_id.toBigDecimal())
        val result=statement.executeUpdate()
        return result>0
    }

}