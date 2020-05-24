package fiix.challenge.fiixexercise.kotlinsample

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import fiix.challenge.fiixexercise.R
import fiix.challenge.fiixexercise.dp.DataProcessor
import fiix.challenge.fiixexercise.kotlinsample.data.LocalDataSource
import fiix.challenge.fiixexercise.kotlinsample.data.TriviaRepository
import fiix.challenge.fiixexercise.kotlinsample.data.db.TriviaDatabase

class MainActivity : AppCompatActivity() {

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val repo = TriviaRepository(TriviaDatabase.getInstance(application), DataProcessor(LocalDataSource()))
        Log.i("Ente Appa", "Start Call  : ")
        repo.getTrivia().subscribe { trivia ->
            Log.i("Ente Appa", "Size is : "+trivia.size)
        }
    }
}
