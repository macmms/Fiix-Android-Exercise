package fiix.challenge.fiixexercise.kotlinsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import fiix.challenge.fiixexercise.R
import fiix.challenge.fiixexercise.dp.DataProcessor
import kotlinx.android.synthetic.main.activity_main.container

class TriviaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .add(R.id.container, QuestionsFragment.newInstance())
            .commit()
    }
}
