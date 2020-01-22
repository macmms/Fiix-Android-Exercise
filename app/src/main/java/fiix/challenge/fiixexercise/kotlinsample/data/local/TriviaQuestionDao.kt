package fiix.challenge.fiixexercise.kotlinsample.data.local

import androidx.room.Query
import fiix.challenge.fiixexercise.kotlinsample.TriviaQuestion

interface TriviaQuestionDao {
    @Query("SELECT * FROM TriviaQuestion")
    fun getQuestions(): List<TriviaQuestion>

    @Query("SELECT * FROM TriviaQuestion WHERE id = :id")
    fun getQuestion(id: Int): TriviaQuestion
}