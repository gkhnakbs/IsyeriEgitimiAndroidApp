package com.gokhanakbas.isyeriegitimiandroidapp.data.remote

import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Firm
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Post
import javax.inject.Inject

class PostsApi @Inject constructor(private val databaseConnection: DatabaseConnection) {

    val connection = databaseConnection.connection

    fun getPosts(): List<Post> {
        val postList = arrayListOf<Post>()
        val statement = connection.createStatement()
        val result =
            statement.executeQuery("select a.ilan_id,a.post_baslik,b.* from ilan AS a JOIN firma AS b ON b.firma_id=a.firma_id ORDER BY a.ilan_id DESC")
        while (result.next()) {
            postList.add(
                Post(
                    "0",
                    result.getString("post_baslik"),
                    "22.03.2024",
                    "",
                    result.getBigDecimal("ilan_id").toString(),
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
                    post_isFavourite = false
                )
            )
        }
        return postList
    }

    fun getFavouritePosts(student_no: String): List<Post> {
        val postList = arrayListOf<Post>()

        val statement = connection.prepareStatement("select i.*,f.* from favori_ilan as fi JOIN ilan as i ON fi.ilan_id=i.ilan_id JOIN firma as f ON f.firma_id=i.firma_id where fi.ogrenci_no= ? ")
        statement.setBigDecimal(1,student_no.toBigDecimal())
        val result = statement.executeQuery()

        while (result.next()) {
            postList.add(
                Post(
                    post_id =  "0",
                    post_label = result.getString("post_baslik"),
                    post_date = "22.03.2024",
                    post_photo = "",
                    post_advert_id =  result.getBigDecimal("ilan_id").toString(),
                    post_firm =  Firm(
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
                    post_isFavourite = true
                )
            )
        }

        return postList
    }

    fun addPostToFavourite(student_no: String, post_id : String): Boolean {
        val statement = connection.prepareStatement("INSERT INTO favori_ilan (ilan_id,ogrenci_no) VALUES (?,?)")
        statement.setBigDecimal(1,post_id.toBigDecimal())
        statement.setBigDecimal(2,student_no.toBigDecimal())
        val result=statement.executeUpdate()
        return result>0
    }

    fun removeFromFavourite(student_no: String, post_id:String): Boolean {
        val statement = connection.prepareStatement("delete from favori_ilan where ilan_id= ? AND ogrenci_no= ?")
        statement.setBigDecimal(1,post_id.toBigDecimal())
        statement.setBigDecimal(2,student_no.toBigDecimal())
        val result=statement.executeUpdate()
        return result>0
    }
}