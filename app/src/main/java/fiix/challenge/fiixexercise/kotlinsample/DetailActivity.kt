package fiix.challenge.fiixexercise.kotlinsample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import fiix.challenge.fiixexercise.R
import kotlinx.android.synthetic.main.activity_detail.*
import android.content.Intent


class DetailActivity : AppCompatActivity() {
    private var triviaQuestion: TriviaQuestion? = null
    private var position:Int=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        getDataFromIncomingIntent()
        setDataOnUI()
    }

    /**
     * This function will parse the data from incoming intent.
     * It will get position and question object
     */
    private fun getDataFromIncomingIntent() {
        intent.let {
            triviaQuestion= it.getSerializableExtra(getString(R.string.data)) as TriviaQuestion?
            position=it.getIntExtra(getString(R.string.position),position)
        }
    }
    /**
     * This function will set the data on UI which we have received
     * as a intent param. If data is null then it will set the default value
     */
    private fun setDataOnUI() {
        triviaQuestion?.let {
            question.text=getString(R.string.question_header, it.question)
            answer.text=getString(R.string.answer_header, it.answer)
        }?:run {
            question.text=getString(R.string.no_question)
            answer.text=getString(R.string.no_answer)
        }
    }

    /**
     * This function will set the data on UI which we have received
     * as a intent param. If data is null then it will set the default value
     */
    override fun onBackPressed() {
        sendResult()
        super.onBackPressed()
    }

    /**
     * This function will send the result back to calling activity.
     * It will return the position of list item.
     */
    private fun sendResult(){
        val backIntent = Intent()
        backIntent.putExtra(getString(R.string.position),position)
        setResult(RESULT_OK, backIntent)
    }

}
