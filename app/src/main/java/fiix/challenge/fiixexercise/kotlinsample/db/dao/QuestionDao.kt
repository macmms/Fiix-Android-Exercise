package fiix.challenge.fiixexercise.kotlinsample.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import fiix.challenge.fiixexercise.kotlinsample.data.TriviaQuestion

@Dao
interface QuestionDao {

    @Query("SELECT * FROM questions WHERE id = :questionId")
    fun getQuestion(questionId: Long): LiveData<TriviaQuestion>

    @Query("SELECT * FROM questions")
    fun getAllQuestions(): LiveData<List<TriviaQuestion>>

    @Query("SELECT * FROM questions")
    suspend fun getAllQuestionsOneShot(): List<TriviaQuestion>

    @Update
    suspend fun updateQuestions(vararg qustions: TriviaQuestion)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(questions: List<TriviaQuestion>)
}
