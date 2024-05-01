package com.gokhanakbas.isyeriegitimiandroidapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class WorkPlaceEducationApplication : Application() {
    //Bu bağımlılığı çalıştıracak olan class.Bunun adını AndroidManifest.xml dosyasında android:name tagine eklememiz gerek.
}