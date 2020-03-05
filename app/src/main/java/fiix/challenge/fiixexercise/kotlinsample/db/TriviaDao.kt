package fiix.challenge.fiixexercise.kotlinsample.db

import androidx.lifecycle.LiveData
import androidx.room.*
import fiix.challenge.fiixexercise.kotlinsample.db.model.TriviaQuestion

@Dao
abstract class TriviaDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertTriviaItem(triviaItem: List<TriviaQuestion>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    abstract fun updateTriviaItem(question: TriviaQuestion)

    @Query(value = "SELECT * FROM trivia")
    abstract fun getAll(): LiveData<List<TriviaQuestion>>

    @Query(value = "SELECT * FROM trivia  WHERE id = :questionId")
    abstract fun getTriviaQuestion(questionId: Int): TriviaQuestion

}