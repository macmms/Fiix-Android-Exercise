package fiix.challenge.fiixexercise.kotlinsample

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import fiix.challenge.fiixexercise.R
import fiix.challenge.fiixexercise.dp.DataProcessor
import fiix.challenge.fiixexercise.kotlinsample.data.LocalDataSource
import fiix.challenge.fiixexercise.kotlinsample.data.TriviaRepository
import fiix.challenge.fiixexercise.kotlinsample.data.db.TriviaDatabase

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val repo = TriviaRepository(TriviaDatabase.getInstance(application), DataProcessor(LocalDataSource()))
        repo.getTriviaLiveData().observe(this, Observer { questions ->
            Log.i("Guruve", "${questions.size}")
        })
        repo.fetchTrivia()
    }
}
