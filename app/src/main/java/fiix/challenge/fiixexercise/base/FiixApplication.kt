package fiix.challenge.fiixexercise.base

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class FiixApplication: Application() {

    override fun onCreate() {
        super.onCreate()

    }
}