package kr.pe.deliverycloneapp2

import android.app.Application
import com.google.firebase.FirebaseApp
import timber.log.Timber

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)

        Timber.plant(Timber.DebugTree())
    }

}