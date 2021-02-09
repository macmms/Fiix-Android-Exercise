package fiix.challenge.fiixexercise

import android.app.Application
import android.content.SharedPreferences
import dagger.hilt.android.HiltAndroidApp
import fiix.challenge.fiixexercise.kotlinsample.ui.questions.MUST_CLEAR_ANSWER_FIELD
import javax.inject.Inject

@HiltAndroidApp
class MainApplication : Application() {

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate() {
        super.onCreate()
        sharedPreferences.edit().putBoolean(MUST_CLEAR_ANSWER_FIELD, true).apply()
    }
}