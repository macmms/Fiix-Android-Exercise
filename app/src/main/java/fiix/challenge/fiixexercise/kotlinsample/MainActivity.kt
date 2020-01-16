package fiix.challenge.fiixexercise.kotlinsample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import fiix.challenge.fiixexercise.R
import fiix.challenge.fiixexercise.dp.DataProcessor

class MainActivity : AppCompatActivity() {

    val dp = DataProcessor(LocalDataSource())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val answers = dp.getAnswers()
    }
}
