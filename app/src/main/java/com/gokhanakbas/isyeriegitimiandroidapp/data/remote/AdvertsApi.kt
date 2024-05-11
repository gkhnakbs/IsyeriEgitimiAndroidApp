package com.gokhanakbas.isyeriegitimiandroidapp.data.remote

import android.util.Log
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Advert
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Firm
import javax.inject.Inject

class AdvertsApi @Inject constructor(private val databaseConnection: DatabaseConnection) {

    val connection = databaseConnection.connection

    fun getAdverts(): List<Advert> {
        val advertList = arrayListOf<Advert>()
        val statement = connection.createStatement()
        val result =
            statement.executeQuery("select * from ilan as i join firma as f on f.firma_id=i.firma_id")
        while (result.next()) {
            advertList.add(
                Advert(
                    result.getBigDecimal("ilan_id").toString(),
                    result.getString("ilan_baslik"),
                    result.getString("ilan_aciklama"),
                    result.getString("baslangic_tarihi"),
                    result.getString("bitis_tarihi"),
                    Firm(
                        result.getBigDecimal("firma_id").toString(),
                        result.getString("firma_ad"),
                        result.getString("firma_sektor"),
                        result.getString("firma_hakkinda"),
                        "",
                        result.getString("firma_eposta"),
                        result.getString("firma_adres"),
                        "",
                        ""
                    ),
                    emptyList(),
                    result.getString("post_baslik")
                )
            )
        }
        return advertList
    }

    fun getFirmsAdverts(firm_id: String): List<Advert> {
        val advertList = arrayListOf<Advert>()
        val statement = connection.createStatement()
        val result =
            statement.executeQuery("select * from ilan as i join firma as f on f.firma_id=i.firma_id where f.firma_id=${firm_id.toBigDecimal()}")
        while (result.next()) {
            advertList.add(
                Advert(
                    result.getBigDecimal("ilan_id").toString(),
                    result.getString("ilan_baslik"),
                    result.getString("ilan_aciklama"),
                    result.getString("baslangic_tarihi"),
                    result.getString("bitis_tarihi"),
                    Firm(
                        result.getBigDecimal("firma_id").toString(),
                        result.getString("firma_ad"),
                        result.getString("firma_sektor"),
                        result.getString("firma_hakkinda"),
                        "",
                        result.getString("firma_eposta"),
                        result.getString("firma_adres"),
                        "",
                        ""
                    ),
                    emptyList(),
                    result.getString("post_baslik")
                )
            )
        }
        return advertList
    }

    fun getAdvertsInformation(advert_id: String): Advert {
        lateinit var advert: Advert
        val statement = connection.createStatement()
        val result =
            statement.executeQuery("select * from ilan as i join firma as f on f.firma_id=i.firma_id where i.ilan_id=${advert_id.toBigDecimal()}")
        while (result.next()) {
            advert = Advert(
                result.getBigDecimal("ilan_id").toString(),
                result.getString("ilan_baslik"),
                result.getString("ilan_aciklama"),
                result.getString("baslangic_tarihi"),
                result.getString("bitis_tarihi"),
                Firm(
                    result.getBigDecimal("firma_id").toString(),
                    result.getString("firma_ad"),
                    result.getString("firma_sektor"),
                    result.getString("firma_hakkinda"),
                    "",
                    result.getString("firma_eposta"),
                    result.getString("firma_adres"),
                    "",
                    ""
                ),
                emptyList(),
                result.getString("post_baslik")
            )

        }
        return advert
    }

    fun getAppliedAdverts(student_no: String): List<Advert> {
        val advertList = arrayListOf<Advert>()
        val statement = connection.createStatement()
        val result =
            statement.executeQuery("select i.*,f.* from basvuru as b join ilan as i on b.ilan_id=i.ilan_id join firma as f on i.firma_id=f.firma_id where b.ogrenci_no=${student_no.toBigDecimal()}")
        while (result.next()) {
            advertList.add(
                Advert(
                    result.getBigDecimal("ilan_id").toString(),
                    result.getString("ilan_baslik"),
                    result.getString("ilan_aciklama"),
                    result.getString("baslangic_tarihi"),
                    result.getString("bitis_tarihi"),
                    Firm(
                        result.getBigDecimal("firma_id").toString(),
                        result.getString("firma_ad"),
                        result.getString("firma_sektor"),
                        result.getString("firma_hakkinda"),
                        "",
                        result.getString("firma_eposta"),
                        result.getString("firma_adres"),
                        "",
                        ""
                    ),
                    emptyList(),
                    result.getString("post_baslik")
                )
            )
        }
        return advertList
    }

    fun deleteFromAppliedAdvert(advert_id: String, student_no: String): Boolean {
        val statement = connection.createStatement()
        val result =
            statement.executeUpdate("delete from basvuru where ogrenci_no=${student_no.toBigDecimal()} AND ilan_id=${advert_id.toBigDecimal()}")

        return result > 0
    }

    fun applyToAdvert(advert_id: String, student_no: String): String {
        val statement = connection.createStatement()
        var sonuc =""
        try {

            val result1 = statement.executeQuery("select count(*) from basvuru where ogrenci_no=$student_no AND ilan_id=$advert_id")

            while (result1.next()){
                sonuc = if (result1.getInt(1) == 0) {
                    val result =
                        statement.executeUpdate("Insert into basvuru (ilan_id,ogrenci_no) VALUES($advert_id,$student_no)")

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
        val statement = connection.createStatement()
        val result = statement.executeUpdate(
            "Insert into ilan (ilan_baslik,ilan_aciklama,baslangic_tarihi,bitis_tarihi,post_baslik,firma_id) " +
                    " VALUES('${advert.advert_title}','${advert.advert_details}','${advert.advert_startDate}','${advert.advert_endDate}','${advert.advert_post_title}',${firm_id.toBigDecimal()})"
        )
        return result > 0
    }


}