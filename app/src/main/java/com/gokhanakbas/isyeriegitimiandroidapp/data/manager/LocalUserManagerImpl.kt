package com.gokhanakbas.isyeriegitimiandroidapp.data.manager

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.gokhanakbas.isyeriegitimiandroidapp.domain.manager.LocalUserManager
import com.gokhanakbas.isyeriegitimiandroidapp.util.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocalUserManagerImpl(
    private val context: Context
) : LocalUserManager {

    //Burada context aracılığı ile bilgileri kaydediyoruz.
    override suspend fun saveUserEntryInfo(user_mail: String, user_password: String) {
        context.dataStore.edit { mail ->
            mail[PreferencesKeys.USER_MAIL] = user_mail
        }
        context.dataStore.edit { password ->
            password[PreferencesKeys.USER_PASSWORD] = user_password
        }
    }

    //Burada maili ve parolayı çekiyoruz ancak çalışıyor mu bilinmiyor.
    override suspend fun readUserEntryMailAndPassword(): Flow<String> {
        return context.dataStore.data.map { preferences ->
            preferences[PreferencesKeys.USER_MAIL]
                ?: "userMail"    //Değer gelmezse userMail dönderecek
            preferences[PreferencesKeys.USER_PASSWORD]
                ?: "userPassword" //Değer gelmezse userPassword dönderecek
        }
    }

    //Burada maili çekiyoruz.
    override suspend fun readUserEntryMail(): Flow<String> {
        return context.dataStore.data.map { preferences ->
            preferences[PreferencesKeys.USER_MAIL] ?: "userMail"
        }
    }


    //Burada parolayı çekiyoruz.
    override suspend fun readUserEntryPassword(): Flow<String> {
        return context.dataStore.data.map { preferences ->
            preferences[PreferencesKeys.USER_PASSWORD] ?: "userPassword"
        }
    }
}

//Burada binevi database oluşturuyoruz.Database imizin değişkeni artık dataStore adı da "user_info"
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = Constants.USER_INFO)

//Burada ise kolonları oluşturuyoruz.
private object PreferencesKeys {
    val USER_MAIL = stringPreferencesKey(name = Constants.USER_MAIL)
    val USER_PASSWORD = stringPreferencesKey(name = Constants.USER_PASSWORD)
}