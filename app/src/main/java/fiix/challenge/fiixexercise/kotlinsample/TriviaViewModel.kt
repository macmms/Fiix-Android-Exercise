package fiix.challenge.fiixexercise.kotlinsample

import androidx.lifecycle.ViewModel
import fiix.challenge.fiixexercise.dp.DataProcessor
import java.lang.IllegalArgumentException

class TriviaViewModel : ViewModel() {
    val dp = DataProcessor(LocalDataSource())
    val answers = dp.getAnswers()


    fun getAnswer(questionIndex: Int): String {
        return ""
    }
}