package fiix.challenge.fiixexercise.kotlinsample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import fiix.challenge.fiixexercise.R
import fiix.challenge.fiixexercise.dp.DataProcessor

class MainActivity : AppCompatActivity(), MainView {

    private val dp = DataProcessor(LocalDataSource())
    private val presenter by lazy {
        MainPresenter(dp, this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter.bind()
    }

    override fun onDestroy() {
        presenter.unBind()
        super.onDestroy()
    }

    override fun showError(s: String) {
        Toast.makeText(this, s, Toast.LENGTH_LONG).show()
    }

    override fun showQuestions(questions: List<TriviaQuestion>) {
        Toast.makeText(this, "${questions.size} questions found", Toast.LENGTH_SHORT).show()
        Toast.makeText(this, "answer of 3rd question is ${questions[2].answer}", Toast.LENGTH_LONG).show()
    }
}
