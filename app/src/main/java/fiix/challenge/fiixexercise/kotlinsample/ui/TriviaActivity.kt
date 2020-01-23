package fiix.challenge.fiixexercise.kotlinsample.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fiix.challenge.fiixexercise.R

class TriviaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .add(R.id.container, QuestionsFragment.newInstance())
            .commit()
    }
}
