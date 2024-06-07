package com.gokhanakbas.isyeriegitimiandroidapp.data.remote

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Advert
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Firm
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Student
import javax.inject.Inject

class FirmsApi @Inject constructor(private val databaseconnection: DatabaseConnection) {
    // Burada gerçekten bir database bağlantısı yapıp firmanın bilgilerini çekeceğimiz fonksiyon zorunluluğu yer alıyor.
    // Burada artık Either kullanmaya gerek yok.

    val connection = databaseconnection.connection

    fun loginFirm(firm_id: String, firm_password: String): Firm {
        val firmObject=Firm("","","","","","","","","")
        val statement = connection.prepareStatement("select * from firma where firma_id= ? AND firma_parola= ? ")
        statement.setBigDecimal(1,firm_id.toBigDecimal())
        statement.setString(2,firm_password)
        val result = statement.executeQuery()
        if (result.next()) {
            firmObject.firm_id=firm_id
            firmObject.firm_name = result.getString("firma_ad")
            firmObject.firm_sector = result.getString("firma_sektor")
            firmObject.firm_info = result.getString("firma_hakkinda") ?: ""
            firmObject.firm_logo = result.getString("firma_logo") ?: ""
            firmObject.firm_mail = result.getString("firma_eposta") ?: ""
            firmObject.firm_address = result.getString("firma_adres") ?: ""
            firmObject.firm_password= result.getString("firma_parola")
            firmObject.firm_phone = ""
        }
        return firmObject
    }


    fun getFirms(): List<Firm> {

        val firmList = arrayListOf<Firm>()
        val statement = connection.createStatement()
        val result = statement.executeQuery("select * from firma")

        while (result.next()) {
            firmList.add(
                Firm(
                    result.getBigDecimal("firma_id").toString(),
                    result.getString("firma_ad"),
                    result.getString("firma_sektor"),
                    result.getString("firma_hakkinda") ?: "",
                    result.getString("firma_logo") ?: "",
                    result.getString("firma_eposta") ?: "",
                    result.getString("firma_adres") ?: "",
                    "",
                    result.getString("firma_parola")
                )
            )
        }
        return firmList
    }

    fun getFirmsInformation(firm_id: String): Firm {
        val firmObject = Firm(firm_id, "", "","","", "", "", "","")
        val statement = connection.prepareStatement("select * from firma where firma_id= ? ")
        statement.setBigDecimal(1,firm_id.toBigDecimal())
        try {
            val result = statement.executeQuery()
            while (result.next()) {
                firmObject.firm_name = result.getString("firma_ad")
                firmObject.firm_sector = result.getString("firma_sektor")
                firmObject.firm_info = result.getString("firma_hakkinda") ?: ""
                firmObject.firm_logo = result.getString("firma_logo") ?: ""
                firmObject.firm_mail = result.getString("firma_eposta") ?: ""
                firmObject.firm_address = result.getString("firma_adres") ?: ""
                firmObject.firm_password= result.getString("firma_parola")
                firmObject.firm_phone = ""
            }
        }catch (e:Exception){
            e.printStackTrace()
        }

        return firmObject
    }

    fun getFirmsWorkingStudents(firm_id: String): List<Student> {
        val studentList = ArrayList<Student>()
        val connection = databaseconnection.connection
        val statement = connection.prepareStatement("select * from ogrenci as o JOIN firma as f ON f.firma_id=o.firma_id where f.firma_id= ? ")
        statement.setBigDecimal(1,firm_id.toBigDecimal())
        val result=statement.executeQuery()

        while (result.next()){
            studentList.add(
                Student(
                    result.getBigDecimal("ogrenci_no").toString(),
                    result.getString("ogrenci_ad") + " " + result.getString("ogrenci_soyad"),
                    "20Default",
                    result.getString("ogrenci_fakulte"),
                    "Bilgisayar Mühendisliği Default",
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
                        result.getString("firma_sektor"),
                        result.getString("firma_hakkinda"),
                        result.getString("firma_logo"),
                        result.getString("firma_eposta"),
                        result.getString("firma_adres"),
                        "",
                        ""
                    ),
                    "",
                    mutableStateListOf()
                )
            )
        }

        return studentList
    }

    fun saveFirmInformation(firm:Firm) : Boolean {
        val statement = connection.prepareStatement("update firma SET firma_eposta= ? , firma_adres= ?  , firma_hakkinda= ?  where firma_id= ? ")
        statement.setString(1,firm.firm_mail)
        statement.setString(2,firm.firm_address)
        statement.setString(3,firm.firm_info)
        statement.setBigDecimal(4,firm.firm_id.toBigDecimal())
        val result=statement.executeUpdate()
        return result>0
        }

    fun saveFirmPassword(firm:Firm) : Boolean {
        val statement = connection.prepareStatement("update firma set firma_parola= ?  where firma_id= ? ")
        statement.setString(1,firm.firm_password)
        statement.setBigDecimal(2,firm.firm_id.toBigDecimal())
        val result=statement.executeUpdate()
        return result>0
    }


}