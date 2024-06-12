package com.gokhanakbas.isyeriegitimiandroidapp.data.remote

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import arrow.core.prependTo
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Advert
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.AppliedAdvert
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Firm
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Interviewer
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Student
import java.sql.Date
import javax.inject.Inject

class AdvertsApi @Inject constructor(private val databaseConnection: DatabaseConnection) {

    val connection = databaseConnection.connection

    @SuppressLint("MutableCollectionMutableState")
    fun getAdverts(): List<Advert> {
        val advertList = arrayListOf<Advert>()
        val statement = connection.createStatement()
        val result =
            statement.executeQuery("select * from ilan as i JOIN firma as f ON f.firma_id=i.firma_id where i.baslangic_tarihi < CURRENT_TIMESTAMP AND i.bitis_tarihi > CURRENT_TIMESTAMP")
        while (result.next()) {
            advertList.add(
                Advert(
                    result.getBigDecimal("ilan_id").toString(),
                    result.getString("baslik"),
                    result.getString("aciklama"),
                    result.getString("baslangic_tarihi"),
                    result.getString("bitis_tarihi"),
                    result.getString("firma_id"),
                    result.getString("firma_ad"),
                    null,
                    mutableStateOf(arrayListOf()),
                    result.getString("post_baslik")
                )
            )
        }
        return advertList
    }

    @SuppressLint("MutableCollectionMutableState")
    fun getFirmsAdverts(firm_id: String): MutableState<ArrayList<Advert>> {
        val advertList = mutableStateOf(arrayListOf<Advert>())
        val statement = connection.prepareStatement("select i.*,f.* from ilan as i JOIN firma as f ON f.firma_id=i.firma_id where f.firma_id=  ? ")
        statement.setBigDecimal(1,firm_id.toBigDecimal())
        val result = statement.executeQuery()
        while (result.next()) {
            advertList.value.add(
                Advert(
                    result.getBigDecimal("ilan_id").toString(),
                    result.getString("baslik"),
                    result.getString("aciklama"),
                    result.getString("baslangic_tarihi"),
                    result.getString("bitis_tarihi"),
                    result.getString("firma_id"),
                    result.getString("firma_ad"),
                    null,
                    getFirmAdvertsInterviewers(result.getBigDecimal("ilan_id").toString()),
                    result.getString("post_baslik")
                )
            )
        }
        return advertList
    }

    private fun getFirmAdvertsInterviewers(advert_id: String) : MutableState<ArrayList<Interviewer>>{
        val interviewerList= mutableStateOf(arrayListOf<Interviewer>())
        val statement = connection.prepareStatement("select * from ogrenci as o JOIN basvuru as b ON b.ogrenci_no=o.ogrenci_no where b.ilan_id= ? ")
        statement.setBigDecimal(1,advert_id.toBigDecimal())
        val result=statement.executeQuery()
        while (result.next()){
            interviewerList.value.add(
                Interviewer(
                    interviewer_id = result.getBigDecimal("basvuru_id").toString(),
                    interviewer_student_id = result.getString("ogrenci_no"),
                    interviewer_student_name = result.getString("ogrenci_ad") +" "+result.getString("ogrenci_soyad"),
                    interviewed_advert_id = result.getString("ilan_id"),
                    interviewed_date = result.getString("basvuru_tarihi"),
                    interview_state = result.getString("basvuru_durum")
                )
            )
        }
        return interviewerList
    }

    @SuppressLint("MutableCollectionMutableState")
    fun getAdvertsInformation(advert_id: String): Advert {
        lateinit var advert: Advert
        val statement = connection.prepareStatement("select * from ilan as i JOIN firma as f ON f.firma_id=i.firma_id where i.ilan_id= ? ")
        statement.setBigDecimal(1,advert_id.toBigDecimal())
        val result = statement.executeQuery()
        while (result.next()) {
            advert = Advert(
                result.getBigDecimal("ilan_id").toString(),
                result.getString("baslik"),
                result.getString("aciklama"),
                result.getString("baslangic_tarihi"),
                result.getString("bitis_tarihi"),
                result.getString("firma_id"),
                result.getString("firma_ad"),
                Firm(
                    result.getBigDecimal("firma_id").toString(),
                    result.getString("firma_ad"),
                    result.getString("firma_sektor"),
                    result.getString("firma_hakkinda") ?: "",
                    result.getString("firma_logo") ?: "",
                    result.getString("firma_eposta") ?: "",
                    result.getString("firma_adres") ?: "",
                    "",
                    ""
                ),
                mutableStateOf(arrayListOf()),
                result.getString("post_baslik")
            )

        }
        return advert
    }

    @SuppressLint("MutableCollectionMutableState")
    fun getAppliedAdverts(student_no: String): List<AppliedAdvert> {
        val advertList = arrayListOf<AppliedAdvert>()
        val statement = connection.prepareStatement("select i.*,f.*,b.* from basvuru as b join ilan as i on b.ilan_id=i.ilan_id join firma as f on i.firma_id=f.firma_id where b.ogrenci_no= ? ")
        statement.setBigDecimal(1,student_no.toBigDecimal())
        val result = statement.executeQuery()
        while (result.next()) {
            advertList.add(
                AppliedAdvert(
                    result.getBigDecimal("ilan_id").toString(),
                    result.getString("baslik"),
                    result.getString("aciklama"),
                    result.getString("baslangic_tarihi"),
                    result.getString("bitis_tarihi"),
                    result.getString("firma_id"),
                    result.getString("firma_ad"),
                    null,
                    mutableStateOf(arrayListOf()),
                    result.getString("post_baslik") ?: "",
                    result.getString("basvuru_tarihi")
                )
            )
        }
        return advertList
    }

    fun deleteFromAppliedAdvert(advert_id: String, student_no: String): Boolean {
        val statement = connection.prepareStatement("delete from basvuru where ogrenci_no= ? AND ilan_id= ? ")
        statement.setBigDecimal(1,student_no.toBigDecimal())
        statement.setBigDecimal(2,advert_id.toBigDecimal())
        val result = statement.executeUpdate()
        return result > 0
    }

    fun applyToAdvert(advert_id: String, student_no: String): String {
        val statement = connection.prepareStatement("select count(*) from basvuru where ogrenci_no= ?  AND ilan_id= ? ")
        var sonuc =""
        statement.setBigDecimal(1,student_no.toBigDecimal())
        statement.setBigDecimal(2,advert_id.toBigDecimal())
        try {

            val result1 = statement.executeQuery()

            while (result1.next()){
                sonuc = if (result1.getInt(1) == 0) {
                    val statement1 = connection.prepareStatement("Insert into basvuru (ilan_id,ogrenci_no) VALUES(?,?)")
                    statement1.setBigDecimal(1,advert_id.toBigDecimal())
                    statement1.setBigDecimal(2,student_no.toBigDecimal())
                    val result = statement.executeUpdate()
                    if (result > 0) "Success" else "Failed"
                } else {
                    "Already Applied"
                }
            }



        }catch (e:Exception){
            Log.d("basvuru",e.localizedMessage!!)
        }
        return sonuc
    }

    fun createAdvert(firm_id: String, advert: Advert): Boolean {
        val statement = connection.prepareStatement("Insert into ilan (baslik,aciklama,baslangic_tarihi,bitis_tarihi,post_baslik,firma_id) VALUES(?,?,?,?,?,?)")
        statement.setString(1,advert.advert_title)
        statement.setString(2,advert.advert_details)
        statement.setDate(3,Date.valueOf(advert.advert_startDate))
        statement.setDate(4,Date.valueOf(advert.advert_endDate))
        statement.setString(5,advert.advert_post_title)
        statement.setBigDecimal(6,firm_id.toBigDecimal())
        val result = statement.executeUpdate()
        return result > 0
    }


    fun updateAdvert(advert: Advert) : Boolean{
        val statement=connection.prepareStatement("update ilan set baslik= ? , aciklama= ?  ,post_baslik= ? , baslangic_tarihi= ? , bitis_tarihi= ? where ilan_id= ? ")
        statement.setString(1,advert.advert_title)
        statement.setString(2,advert.advert_details)
        statement.setString(3,advert.advert_post_title)
        statement.setDate(4,Date.valueOf(advert.advert_startDate))
        statement.setDate(5,Date.valueOf(advert.advert_endDate))
        statement.setBigDecimal(6,advert.advert_id.toBigDecimal())
        val result=statement.executeUpdate()
        return result>0
    }

    fun deleteAdvert(advert_id :String) : Boolean{
        val statement=connection.prepareStatement("delete from ilan where ilan_id= ? ")
        statement.setBigDecimal(1,advert_id.toBigDecimal())
        val result=statement.executeUpdate()
        return result>0
    }

}