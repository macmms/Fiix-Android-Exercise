package fiix.challenge.fiixexercise.kotlinsample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.widget.TextView
import fiix.challenge.fiixexercise.R

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val toolbar = findViewById<Toolbar>(R.id.view_toolbar)
        toolbar.setTitle(R.string.app_title)

        val index = intent.getIntExtra("index", 0)
        if (RawData.triviaQuestions != null) {
            val triviaQuestion = RawData.triviaQuestions!!.get(index)
            val questionTextView = findViewById<TextView>(R.id.questionTextView)
            val answerTextView = findViewById<TextView>(R.id.answerTextView)

            questionTextView.text = triviaQuestion.question
            answerTextView.text = triviaQuestion.answer
        }

    }
}
