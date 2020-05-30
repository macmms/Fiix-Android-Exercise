package fiix.challenge.fiixexercise.kotlinsample

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import fiix.challenge.fiixexercise.R

class QnADetailsActivity : AppCompatActivity() {

    private var currentQuestion: CharSequence? = null
    private var currentAnswer: CharSequence? = null
    private lateinit var originalQuestion: CharSequence
    private var originalAnswer: CharSequence? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qn_a_details)

        val inputBundle = intent?.extras
        if (inputBundle == null) {
            // log to Crashlytics or crash
        } else {
            val question = inputBundle[ARG_QNA] as TriviaQuestion
            val questionPosition = inputBundle[ARG_QNA_POS] as Int
            originalQuestion = question.question
            originalAnswer = question.answer
            val etQuestion: EditText = findViewById(R.id.et_question)
            val etAnswer: EditText = findViewById(R.id.et_answer)
            etQuestion.addTextChangedListener(object: TextWatcher {
                override fun afterTextChanged(p0: Editable?) {
                    // no-op
                }

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    // no-op
                }

                override fun onTextChanged(p0: CharSequence, p1: Int, p2: Int, p3: Int) {
                    currentQuestion = p0
                    updateResult(questionPosition)
                }

            })
            etAnswer.addTextChangedListener(object: TextWatcher {
                override fun afterTextChanged(p0: Editable?) {
                    // no-op
                }

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    // no-op
                }

                override fun onTextChanged(p0: CharSequence, p1: Int, p2: Int, p3: Int) {
                    currentAnswer = p0
                    updateResult(questionPosition)
                }

            })
            etQuestion.setText(question.question)
            etAnswer.setText(question.answer)
        }
    }

    private fun updateResult(questionPosition: Int) {
        println("updateResult")
        println("current question: $currentQuestion vs. original: $originalQuestion")
        println("current answer: $currentAnswer vs. original: $originalAnswer")
        if (currentQuestion == originalQuestion && currentAnswer == originalAnswer) {
            println("no change")
            setResult(RESULT_QNA_UNCHANGED)
        } else {
            println("changed")
            val result = Intent()
            result.putExtra(ARG_QNA_POS, questionPosition)
            setResult(RESULT_QNA_CHANGED, result)
        }
    }

    companion object {
        private const val ARG_QNA = "ARG_QNA"
        const val ARG_QNA_POS = "ARG_QNA_POS"
        const val RESULT_QNA_CHANGED = 11
        const val RESULT_QNA_UNCHANGED = 12
        fun newIntent(context: Context, triviaQuestion: TriviaQuestion, adapterPosition: Int): Intent {
            val intent = Intent(context, QnADetailsActivity::class.java)
            intent.putExtra(ARG_QNA, triviaQuestion)
            intent.putExtra(ARG_QNA_POS, adapterPosition)
            return intent
        }
    }
}
