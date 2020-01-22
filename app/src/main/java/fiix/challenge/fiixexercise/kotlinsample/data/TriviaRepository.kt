package fiix.challenge.fiixexercise.kotlinsample.data

import androidx.lifecycle.LiveData
import fiix.challenge.fiixexercise.kotlinsample.TriviaQuestion

interface TriviaRepository {
    fun getQuestions(): LiveData<List<TriviaQuestion>>
    fun getQuestion(id: Int): LiveData<TriviaQuestion>
    fun getAnswers()
    fun setQuestions(questions: List<TriviaQuestion>)
    fun updateQuestion(question: TriviaQuestion)
}