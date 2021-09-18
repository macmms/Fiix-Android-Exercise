package fiix.challenge.fiixexercise.kotlinsample.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import fiix.challenge.fiixexercise.databinding.ActivityMainBinding
import fiix.challenge.fiixexercise.dp.DataProcessor
import fiix.challenge.fiixexercise.kotlinsample.repository.LocalDataSource

class MainActivity : AppCompatActivity() {

    val dp = DataProcessor(LocalDataSource())
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val answers = dp.getAnswers()
    }
}
