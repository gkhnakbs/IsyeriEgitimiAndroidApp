package com.gokhanakbas.isyeriegitimiandroidapp.data.remote

import androidx.compose.runtime.mutableStateListOf
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Commission
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Firm
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Student
import com.gokhanakbas.isyeriegitimiandroidapp.util.Constants
import javax.inject.Inject

class CommissionApi @Inject constructor(private val databaseConnection: DatabaseConnection){

    // Burada gerçekten bir database bağlantısı yapıp komisyonun bilgilerini çekeceğimiz fonksiyon zorunluluğu yer alıyor.

    val connection=databaseConnection.connection

    fun getCommissionsInformation(commission_id:String) : Commission{
        val commission=Commission(Constants.COMMISION_ID,"","","","")
        val statement = connection.createStatement()
        val result=statement.executeQuery("select * from komisyon where komisyon_id=${commission_id}")
        while (result.next()){
            commission.commission_department=result.getString("komisyon_bolum")
            commission.commission_faculty=result.getString("komisyon_fakulte")
            commission.commission_mail=result.getString("komisyon_eposta")
            commission.commission_name=result.getString("komisyon_ad")+" "+result.getString("komisyon_soyad")
        }
        return commission
    }

    fun getCommissions() : List<Commission>{
        val commissionList = ArrayList<Commission>()
        val statement = connection.createStatement()
        val result = statement.executeQuery("select * from komisyon")
        while(result.next()){
            commissionList.add(
                Commission(
                    commission_id = result.getBigDecimal("komisyon_id").toString(),
                    commission_name = result.getString("komisyon_ad")+ " " + result.getString("komisyon_soyad"),
                    commission_mail = result.getString("komisyon_eposta"),
                    commission_department = result.getString("komisyon_bolum"),
                    commission_faculty = result.getString("komisyon_fakulte")
                )
            )
        }
        return commissionList
    }

    fun loginCommission(commission_id: String, commission_password: String): Commission {
        val commissionObject = Commission("","","","","")
        val statement = connection.createStatement()
        val result =
            statement.executeQuery("select * from komisyon where komisyon_id='$commission_id' AND komisyon_parola='$commission_password'")
        if (result.next()) {
            commissionObject.commission_id=result.getBigDecimal("komisyon_id").toString()
            commissionObject.commission_name=result.getString("komisyon_ad") + " " + result.getString("komisyon_soyad")
            commissionObject.commission_mail=result.getString("komisyon_eposta")
            commissionObject.commission_faculty=result.getString("komisyon_fakulte")
            commissionObject.commission_department=result.getString("komisyon_bolum")
        }
        return commissionObject
    }

    fun saveCommissionInformation(commission: Commission) : Boolean{
        val statement = connection.createStatement()
        val result = statement.executeUpdate("update komisyon SET komisyon_eposta= '${commission.commission_mail}'  where komisyon_id = '${commission.commission_id}'")
        return result>0
    }

    fun saveCommissionPassword(commission_password: String,commission_id: String) : Boolean{
        val statement = connection.createStatement()
        val result = statement.executeUpdate("update komisyon SET komisyon_parola= '$commission_password'  where komisyon_id = '$commission_id'")
        return result>0
    }

}