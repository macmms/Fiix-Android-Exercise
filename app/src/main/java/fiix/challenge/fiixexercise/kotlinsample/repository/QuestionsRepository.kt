package fiix.challenge.fiixexercise.kotlinsample.repository

import fiix.challenge.fiixexercise.kotlinsample.TriviaQuestion
import fiix.challenge.fiixexercise.kotlinsample.db.QuestionsDatabase

class QuestionsRepository(val db: QuestionsDatabase) {
    suspend fun saveQuestions(questions: List<TriviaQuestion>) = db.getQuestionDao().save(questions)

    fun getQuestions() = db.getQuestionDao().getQuestions()

    suspend fun updateQuestion(question: TriviaQuestion) = db.getQuestionDao().updateQuestion(question)
}