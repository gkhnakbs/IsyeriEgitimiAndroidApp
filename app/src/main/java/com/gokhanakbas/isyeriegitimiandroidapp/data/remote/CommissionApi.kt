package com.gokhanakbas.isyeriegitimiandroidapp.data.remote

import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Commission
import com.gokhanakbas.isyeriegitimiandroidapp.util.Constants
import javax.inject.Inject

class CommissionApi @Inject constructor(private val databaseConnection: DatabaseConnection){

    // Burada gerçekten bir database bağlantısı yapıp komisyonun bilgilerini çekeceğimiz fonksiyon zorunluluğu yer alıyor.

    val connection=databaseConnection.connection

    fun getCommissionsInformation(commission_id:String) : Commission{
        val commission=Commission(Constants.COMMISION_ID,"","","","","","","")
        val statement = connection.createStatement()
        val result=statement.executeQuery("select * from komisyon where komisyon_id=${commission_id}")
        while (result.next()){
            commission.commission_degree=""
            commission.commission_department=result.getString("komisyon_bolum")
            commission.commission_faculty=result.getString("komisyon_fakulte")
            commission.commission_info=result.getString("komisyon_hakkinda")
            commission.commission_mail=result.getString("komisyon_eposta")
            commission.commission_name=result.getString("komisyon_ad")+" "+result.getString("komisyon_soyad")
            commission.commission_specificField=""
        }
        return commission
    }

    fun getCommissions() : List<Commission>{
        return emptyList()
    }

}