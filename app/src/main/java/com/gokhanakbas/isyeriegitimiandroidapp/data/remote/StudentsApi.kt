package com.gokhanakbas.isyeriegitimiandroidapp.data.remote

import androidx.compose.runtime.mutableStateListOf
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Firm
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Lecturer
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
                    result.getString("ogrenci_hakkinda") ?: "",
                    result.getString("ogrenci_adres") ?: "",
                    result.getString("ogrenci_tel_no") ?: "",
                    result.getString("ogrenci_eposta") ?: "",
                    Firm(
                        result.getBigDecimal("firma_id")?.toString() ?: "",
                        result.getString("firma_ad")  ?: "",
                        result.getString("firma_sektor")  ?: "",
                        result.getString("firma_hakkinda")  ?: "",
                        result.getString("firma_logo")  ?: "",
                        result.getString("firma_eposta")  ?: "",
                        result.getString("firma_adres")  ?: "",
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
            val statement = connection.prepareStatement("select * from ogrenci as o full join firma as f on f.firma_id=o.firma_id where ogrenci_no= ? ")
            statement.setBigDecimal(1,studentNo.toBigDecimal())
            val result=statement.executeQuery()
            while (result.next()) {
                studentObject.student_name =
                    result.getString("ogrenci_ad") + " " + result.getString("ogrenci_soyad")
                studentObject.student_age = result.getString("ogrenci_yas")
                studentObject.student_faculty = result.getString("ogrenci_fakulte")
                studentObject.student_department = "Bilgisayar Mühendisliği Default"
                studentObject.student_ID = result.getString("ogrenci_kimlik_no")
                studentObject.student_classLevel = result.getString("ogrenci_sinif")
                studentObject.student_AGNO = result.getString("ogrenci_agno")
                studentObject.student_info = result.getString("ogrenci_hakkinda") ?: ""
                studentObject.student_address = result.getString("ogrenci_adres") ?: ""
                studentObject.student_phone = result.getString("ogrenci_tel_no") ?: ""
                studentObject.student_mail=result.getString("ogrenci_eposta") ?: ""
                studentObject.student_password=result.getString("ogrenci_parola")
                studentObject.student_workPlace =
                    Firm(
                        result.getBigDecimal("firma_id")?.toString()  ?: "",
                        result.getString("firma_ad")  ?: "",
                        result.getString("firma_sektor")  ?: "",
                        result.getString("firma_hakkinda")  ?: "",
                        result.getString("firma_logo")  ?: "",
                        result.getString("firma_eposta")  ?: "",
                        result.getString("firma_adres")  ?: "",
                        "",
                        ""
                    )
            }
        return studentObject
    }

    fun loginStudent(student_no: String, student_password: String): Student {
        val studentObject =Student(
            "", "", "", "", "", "", "", "", "", "", "", "",
            Firm("", "", "", "", "", "", "", "",""),
            "", mutableStateListOf()
        )
        val statement = connection.prepareStatement("select * from ogrenci as o FULL JOIN firma as f ON o.firma_id=f.firma_id where o.ogrenci_no= ? AND o.ogrenci_parola= ? ")
        statement.setBigDecimal(1,student_no.toBigDecimal())
        statement.setString(2,student_password)
        val result = statement.executeQuery()
        if (result.next()) {
            studentObject.student_no=student_no
            studentObject.student_name =result.getString("ogrenci_ad") + " " + result.getString("ogrenci_soyad")
            studentObject.student_age = result.getString("ogrenci_yas")
            studentObject.student_faculty = result.getString("ogrenci_fakulte")
            studentObject.student_department = result.getString("ogrenci_bolum")
            studentObject.student_ID = result.getString("ogrenci_kimlik_no")
            studentObject.student_classLevel = result.getString("ogrenci_sinif")
            studentObject.student_AGNO = result.getString("ogrenci_agno")
            studentObject.student_info = result.getString("ogrenci_hakkinda") ?: ""
            studentObject.student_address = result.getString("ogrenci_adres") ?: ""
            studentObject.student_phone = result.getString("ogrenci_tel_no")  ?: ""
            studentObject.student_mail=result.getString("ogrenci_eposta") ?: ""
            studentObject.student_password=result.getString("ogrenci_parola")
            studentObject.student_workPlace =
                Firm(
                    result.getBigDecimal("firma_id")?.toString()  ?: "",
                    result.getString("firma_ad")  ?: "",
                    result.getString("firma_sektor")  ?: "",
                    result.getString("firma_hakkinda")  ?: "",
                    result.getString("firma_logo")  ?: "",
                    result.getString("firma_eposta")  ?: "",
                    result.getString("firma_adres")  ?: "",
                    "",
                    ""
                )
        }
        return studentObject
    }

    fun getSkills(ogrenci_no:String) : MutableList<Skill>{
        val skillList= mutableStateListOf<Skill>()
        val statement = connection.prepareStatement("select * from yetenek where ogrenci_no= ? ")
        statement.setBigDecimal(1,ogrenci_no.toBigDecimal())
        val result=statement.executeQuery()
        while (result.next()){
            skillList.add(
                Skill(
                    result.getBigDecimal("yetenek_id").toString(),
                    result.getString("yetenek_baslik"),
                    result.getString("yetenek_seviye")
                )
            )
        }
        return skillList
    }

    fun getGroupsLecturerInformation(student_no : String) : Lecturer{
        val groupsLecturer = Lecturer("","","","","","","","","")
        val statement=connection.prepareStatement("select i.* from izleyici as i JOIN ogrenci_grup as og ON og.izleyici_id=i.izleyici_id JOIN gruptaki_ogrenciler as go ON go.grup_id=og.grup_id where go.ogrenci_no= ? ")
        statement.setBigDecimal(1,student_no.toBigDecimal())
        val result=statement.executeQuery()
        while (result.next()) {
            groupsLecturer.lecturer_id=result.getBigDecimal("izleyici_id").toString()
            groupsLecturer.lecturer_name=result.getString("izleyici_ad") +" "+result.getString("izleyici_soyad")
            //Öğrenci için sadece başta izleyicinin adı ve soyadı gösterileceği için bu kadarı yeter
        }
        return groupsLecturer
    }

    fun saveStudentInformation(student: Student) : Boolean {
        val statement = connection.prepareStatement("update ogrenci SET ogrenci_hakkinda= ? , ogrenci_eposta= ? , ogrenci_tel_no= ? , ogrenci_adres = ? where ogrenci_no = ? ")
        statement.setString(1,student.student_info)
        statement.setString(2,student.student_mail)
        statement.setString(3,student.student_phone)
        statement.setString(4,student.student_address)
        statement.setBigDecimal(5,student.student_no.toBigDecimal())
        val result=statement.executeUpdate()
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
        val statement = connection.prepareStatement("update ogrenci SET ogrenci_parola= ? where ogrenci_no = ?")
        statement.setString(1,student.student_password)
        statement.setBigDecimal(2,student.student_no.toBigDecimal())
        val result = statement.executeUpdate()
        return result>0
    }

}