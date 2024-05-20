package com.gokhanakbas.isyeriegitimiandroidapp.domain.repository

import androidx.compose.runtime.MutableState
import arrow.core.Either
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Advert
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.NetworkError

interface AdvertsRepository {

    suspend fun getAdverts(): Either<NetworkError, List<Advert>>  // Burada öğrenci , izleyici ve komisyon firma ilanlarını çekilecek

    suspend fun getFirmsAdverts(firm_id:String): Either<NetworkError, MutableState<ArrayList<Advert>>>  // Burada firma kendi ilanlarını çekilecek

    suspend fun getAdvertsInformation(advert_id : String) : Either<NetworkError,Advert>

    suspend fun getAppliedAdverts(student_no:String) : Either<NetworkError,List<Advert>>

    suspend fun deleteFromAppliedAdvert(advert_id:String,student_no:String) : Either<NetworkError,Boolean>

    suspend fun applyToAdvert(advert_id:String,student_no:String) : Either<NetworkError,String>

    suspend fun createAdvert(firm_id:String,advert:Advert) : Either<NetworkError,Boolean>

    suspend fun updateAdvert(advert:Advert) : Either<NetworkError,Boolean>

    suspend fun deleteAdvert(advert_id: String) : Either<NetworkError,Boolean>

}