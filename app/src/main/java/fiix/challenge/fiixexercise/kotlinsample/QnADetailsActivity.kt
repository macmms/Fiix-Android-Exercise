package fiix.challenge.fiixexercise.kotlinsample

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import fiix.challenge.fiixexercise.R

class QnADetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qn_a_details)

        val inputBundle = intent?.extras
        if (inputBundle == null) {
            // log to Crashlytics or crash
        } else {
            val question = inputBundle[ARG_QNA] as TriviaQuestion
            val etQuestion : EditText = findViewById(R.id.et_question)
            val etAnswer : EditText = findViewById(R.id.et_answer)
            etQuestion.setText(question.question)
            etAnswer.setText(question.answer)
        }
    }

    companion object {
        private const val ARG_QNA = "ARG_QNA"
        fun newIntent(context: Context, triviaQuestion: TriviaQuestion) : Intent {
            val intent = Intent(context, QnADetailsActivity::class.java)
            intent.putExtra(ARG_QNA, triviaQuestion)
            return intent
        }
    }
}
