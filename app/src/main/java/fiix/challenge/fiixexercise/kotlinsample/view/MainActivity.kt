package fiix.challenge.fiixexercise.kotlinsample.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import fiix.challenge.fiixexercise.R
import fiix.challenge.fiixexercise.dp.DataProcessor
import fiix.challenge.fiixexercise.kotlinsample.repository.LocalDataSource

class MainActivity : AppCompatActivity() {

    val dp = DataProcessor(LocalDataSource())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val answers = dp.getAnswers()
    }
}
