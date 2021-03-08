package fiix.challenge.fiixexercise.kotlinsample
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import fiix.challenge.fiixexercise.R
import kotlinx.android.synthetic.main.activity_details.*


class QuestionDetailsActivity : AppCompatActivity() {

    var question: TriviaQuestion? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        val intent = intent
        question = intent.extras?.getParcelable(MainActivity.EXTRA_QUESTION)!!

        if (question != null) {
            edit_Text_view_question.setText(question?.question)
            edit_Text_view_answer.setText(question?.answer)
        }
        button_save.setOnClickListener {
            val dbHelper: QuestionDbHelper? = QuestionDbHelper(this)
            edit_Text_view_question.text.toString()
            question?.question = edit_Text_view_question.text.toString()
            question?.answer = edit_Text_view_answer.text.toString()
            question?.isShowing = false
            dbHelper?.update(question!!)
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
            this.finish()
        }
    }

}
