package fiix.challenge.fiixexercise.kotlinsample

import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import fiix.challenge.fiixexercise.R
import fiix.challenge.fiixexercise.dp.DataProcessor
import fiix.challenge.fiixexercise.kotlinsample.db.QuestionsDatabase
import fiix.challenge.fiixexercise.kotlinsample.repository.QuestionsRepository
import fiix.challenge.fiixexercise.kotlinsample.ui.MainViewModel
import fiix.challenge.fiixexercise.kotlinsample.ui.ViewModelProviderFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel
    private lateinit var questionRepository: QuestionsRepository
    private lateinit var db: QuestionsDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        db = QuestionsDatabase(this)
        questionRepository = QuestionsRepository(db)
        val viewModelProviderFactory = ViewModelProviderFactory(questionRepository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(MainViewModel::class.java)


        setContentView(R.layout.activity_main)






        pbProgressMain

    }


    override fun onDestroy() {
        super.onDestroy()
        db.close()
    }
}
