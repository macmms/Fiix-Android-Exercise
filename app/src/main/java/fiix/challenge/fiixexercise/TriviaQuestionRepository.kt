package fiix.challenge.fiixexercise

import fiix.challenge.fiixexercise.db.ioThread
import fiix.challenge.fiixexercise.kotlinsample.db.TriviaDatabase
import fiix.challenge.fiixexercise.kotlinsample.db.TriviaQuestion

class TriviaQuestionRepository constructor(private val database: TriviaDatabase) {

    fun retrieveTriviaQuestions() = database.triviaQuestionDao().retrieveAllQuestions()

    fun updateQuestion(updatedTriviaQuestion: TriviaQuestion) = ioThread { database.triviaQuestionDao().updateQuestion(updatedTriviaQuestion) }

    fun retrieveTriviaQuestion(questionId: Int) = database.triviaQuestionDao().retrieveQuestion(questionId)
}