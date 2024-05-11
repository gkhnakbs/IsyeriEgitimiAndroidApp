package com.gokhanakbas.isyeriegitimiandroidapp.data.remote

import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Firm
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Student
import javax.inject.Inject

class FirmsApi @Inject constructor(private val databaseconnection: DatabaseConnection) {
    // Burada gerçekten bir database bağlantısı yapıp firmanın bilgilerini çekeceğimiz fonksiyon zorunluluğu yer alıyor.
    // Burada artık Either kullanmaya gerek yok.

    val connection = databaseconnection.connection

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
                    result.getString("firma_hakkinda"),
                    result.getString("firma_logo"),
                    result.getString("firma_eposta"),
                    result.getString("firma_adres"),
                    "",
                    result.getString("firma_parola")
                )
            )
        }
        return firmList
    }

    fun getFirmsInformation(firm_id: String): Firm {
        val firmObject = Firm(firm_id, "", "","","", "", "", "","")
        val statement = connection.createStatement()
        try {
            val result = statement.executeQuery("select * from firma where firma_id=$firm_id")
            while (result.next()) {
                firmObject.firm_name = result.getString("firma_ad")
                firmObject.firm_sector = result.getString("firma_sektor")
                firmObject.firm_info = result.getString("firma_hakkinda")
                firmObject.firm_logo = result.getString("firma_logo")
                firmObject.firm_mail = result.getString("firma_eposta")
                firmObject.firm_address = result.getString("firma_adres")
                firmObject.firm_password= result.getString("firma_parola")
                firmObject.firm_phone = ""
            }
        }catch (e:Exception){
            e.printStackTrace()
        }

        return firmObject
    }


    fun saveFirmInformation(firm:Firm) : Boolean {
        val statement = connection.createStatement()
        val result=statement.executeUpdate("update firma SET firma_eposta='${firm.firm_mail}', firma_adres='${firm.firm_address}' , firma_hakkinda='${firm.firm_info}' where firma_id=${firm.firm_id.toBigDecimal()}")
        return result>0
        }

    fun saveFirmPassword(firm:Firm) : Boolean {
        val statement = connection.createStatement()
        val result=statement.executeUpdate("update firma set firma_parola='${firm.firm_password}' where firma_id=${firm.firm_id.toBigDecimal()}")
        return result>0
    }

    fun getFirmsWorkingStudents(): List<Student> {
        val connection = databaseconnection.connection
        return emptyList<Student>()
    }
}