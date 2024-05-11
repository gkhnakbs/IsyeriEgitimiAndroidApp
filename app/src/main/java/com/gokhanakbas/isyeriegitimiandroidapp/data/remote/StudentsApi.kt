package com.gokhanakbas.isyeriegitimiandroidapp.data.remote

import androidx.compose.runtime.mutableStateListOf
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Firm
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Skill
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Student
import javax.inject.Inject

class StudentsApi @Inject constructor(private var databaseconnection: DatabaseConnection) {

    // Burada gerçekten bir database bağlantısını kullanıp öğrencinin/öğrencilerin bilgilerini çekeceğimiz fonksiyonlarımız yer alıyor.

    val connection = databaseconnection.connection

    fun getStudents(): List<Student> {
        val studentList = arrayListOf<Student>()
        val statement = connection.createStatement()
        val result = statement.executeQuery("select * from ogrenci as o join firma as f on f.firma_id=o.firma_id")
        while (result.next()) {
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
                    result.getString("ogrenci_hakkinda"),
                    result.getString("ogrenci_adres"),
                    result.getString("ogrenci_tel_no"),
                    result.getString("ogrenci_eposta"),
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
                    result.getString("ogrenci_parola"),
                    mutableStateListOf()
                )
            )
        }
        return studentList
    }

    fun getStudentsInformation(studentNo: String): Student {
        val studentObject =
            Student(
                studentNo, "", "", "", "", "", "", "", "", "", "", "",
                Firm("", "", "", "", "", "", "", "",""),
                "", mutableStateListOf()
            )
        val statement = connection.createStatement()

            val result =
                statement.executeQuery("select * from ogrenci as o join firma as f on f.firma_id=o.firma_id where ogrenci_no='$studentNo'")
            while (result.next()) {
                studentObject.student_name =
                    result.getString("ogrenci_ad") + " " + result.getString("ogrenci_soyad")
                studentObject.student_age = result.getString("ogrenci_yas")
                studentObject.student_faculty = result.getString("ogrenci_fakulte")
                studentObject.student_department = "Bilgisayar Mühendisliği Default"
                studentObject.student_ID = result.getString("ogrenci_kimlik_no")
                studentObject.student_classLevel = result.getString("ogrenci_sinif")
                studentObject.student_AGNO = result.getString("ogrenci_agno")
                studentObject.student_info = result.getString("ogrenci_hakkinda")
                studentObject.student_address = result.getString("ogrenci_adres")
                studentObject.student_phone = result.getString("ogrenci_tel_no")
                studentObject.student_mail=result.getString("ogrenci_eposta")
                studentObject.student_password=result.getString("ogrenci_parola")
                studentObject.student_workPlace =
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
                    )


            }
        return studentObject
    }

    fun login_student(student_no: String, student_password: String): Boolean {
        val statement = connection.createStatement()
        val result =
            statement.executeQuery("select COUNT(*) from ogrenci where ogrenci_no='$student_no' AND ogrenci_parola='$student_password'")
        if (result.next()) {
            return result.getInt(1) > 0
        }
        return false
    }

    fun getSkills(ogrenci_no:String) : MutableList<Skill>{
        val skillList= mutableStateListOf<Skill>()
        val statement = connection.createStatement()
        val result=statement.executeQuery("select * from yetenek where ogrenci_no=${ogrenci_no.toBigDecimal()}")

        while (result.next()){
            skillList.add(
                Skill(
                    result.getBigDecimal("yetenek_id").toString(),
                    result.getString("yetenek_baslik"),
                    result.getString("yetenek_seviye"),
                    result.getString("yetenek_eklenmeTarihi")
                )
            )
        }
        return skillList
    }

    fun saveStudentInformation(student: Student) : Boolean {
        val statement = connection.createStatement()
        val result = statement.executeUpdate("update ogrenci SET ogrenci_hakkinda= '${student.student_info}' , ogrenci_eposta= '${student.student_mail}' where ogrenci_no = ${student.student_no.toBigDecimal()}")
        return result>0
    }

    fun saveStudentSkills(student_no: String,skillList: MutableList<Skill>) : Boolean{
        val statement=connection.createStatement()
        val deleteOldSkill=statement.executeUpdate("delete from yetenek where ogrenci_no=$student_no")
        var resultCount=0
        skillList.forEachIndexed{ index , skill ->
            val addSkill=statement.executeUpdate("insert into yetenek(yetenek_baslik,yetenek_seviye,ogrenci_no) VALUES('${skill.skill_name}','${skill.skill_level}',$student_no)")
             if(addSkill>0) resultCount+=1
        }
        return resultCount==skillList.size && deleteOldSkill>0
    }

    fun saveStudentPassword(student: Student) : Boolean {
        val statement = connection.createStatement()
        val result = statement.executeUpdate("update ogrenci SET ogrenci_parola= '${student.student_password}' where ogrenci_no = ${student.student_no.toBigDecimal()}")
        return result>0
    }

}