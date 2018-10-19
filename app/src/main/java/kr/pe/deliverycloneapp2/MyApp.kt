package kr.pe.deliverycloneapp2

import android.app.Application
import android.util.Log

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        Log.d("KTH", "init");
    }

}