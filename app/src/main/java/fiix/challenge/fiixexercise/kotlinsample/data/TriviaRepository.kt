package fiix.challenge.fiixexercise.kotlinsample.data

import androidx.lifecycle.LiveData
import fiix.challenge.fiixexercise.kotlinsample.TriviaQuestion

interface TriviaRepository {
    suspend fun fetchQuestions()
    fun getQuestions(): LiveData<List<TriviaQuestion>>
    fun getQuestion(id: Int): LiveData<TriviaQuestion>
    suspend fun getAnswers(questions: List<TriviaQuestion>)
    suspend fun setQuestions(questions: List<TriviaQuestion>)
    suspend fun updateQuestion(question: TriviaQuestion)
}