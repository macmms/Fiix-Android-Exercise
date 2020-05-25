package fiix.challenge.fiixexercise.db

import androidx.lifecycle.LiveData
import androidx.room.*
import fiix.challenge.fiixexercise.kotlinsample.db.TriviaQuestion

@Dao
interface TriviaQuestionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertQuestions(questions: List<TriviaQuestion>)

    @Query("SELECT * FROM trivia_question WHERE questionId = :id_")
    fun retrieveQuestion(id_: Int): LiveData<TriviaQuestion>

    @Query("SELECT * FROM trivia_question")
    fun retrieveAllQuestions(): LiveData<List<TriviaQuestion>>

    @Update
    fun updateQuestion(updatedQuestion: TriviaQuestion)
}