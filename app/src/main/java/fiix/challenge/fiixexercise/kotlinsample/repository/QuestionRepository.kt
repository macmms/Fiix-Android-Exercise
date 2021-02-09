package fiix.challenge.fiixexercise.kotlinsample.repository

import androidx.lifecycle.LiveData
import fiix.challenge.fiixexercise.kotlinsample.data.TriviaQuestion
import fiix.challenge.fiixexercise.kotlinsample.db.dao.QuestionDao
import javax.inject.Inject

class QuestionRepository @Inject constructor(
        private val questionDao: QuestionDao
) {
    fun getQuestions() = questionDao.getAllQuestions()
    suspend fun getQuestionsOneShot() = questionDao.getAllQuestionsOneShot()
    fun getQuestion(questionId: Long): LiveData<TriviaQuestion> {
        return questionDao.getQuestion(questionId)
    }
    suspend fun updateQuestion(question: TriviaQuestion) = questionDao.updateQuestions(question)
    suspend fun insertAll(questions: List<TriviaQuestion>) = questionDao.insertAll(questions)
}