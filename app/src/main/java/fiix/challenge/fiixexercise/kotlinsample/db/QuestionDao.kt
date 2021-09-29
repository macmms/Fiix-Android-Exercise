package fiix.challenge.fiixexercise.kotlinsample.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import fiix.challenge.fiixexercise.kotlinsample.TriviaQuestion

@Dao
interface QuestionDao {
    @Insert(onConflict = REPLACE)
    suspend fun save(questions: List<TriviaQuestion>)

    @Insert(onConflict = REPLACE)
    suspend fun updateQuestion(questions: TriviaQuestion)

    @Query("SELECT * FROM questions ORDER BY id asc")
    fun getQuestions(): LiveData<List<TriviaQuestion>>
}