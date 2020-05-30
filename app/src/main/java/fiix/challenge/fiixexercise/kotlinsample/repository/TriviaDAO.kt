package fiix.challenge.fiixexercise.kotlinsample.repository

import androidx.room.*
import fiix.challenge.fiixexercise.kotlinsample.model.Trivia
import fiix.challenge.fiixexercise.kotlinsample.util.Constants
import kotlinx.coroutines.flow.Flow

@Dao
interface TriviaDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(trivia: Trivia): Long

    @Query("SELECT * FROM Trivia")
    fun getTriviaList(): Flow<List<Trivia>>

    @Query("SELECT * FROM Trivia WHERE id = :id ")
    suspend fun getTrivia(id: Int) : Trivia

    @Query("SELECT status FROM Trivia WHERE id = :id")
    suspend fun isEditing(id: String) : Int

    @Query("SELECT COUNT(id) FROM Trivia")
    suspend fun areThereQuestions() : Int

    @Update
    suspend fun update(vararg trivia: Trivia): Int

    @Query("UPDATE Trivia SET status = ${Constants.STATUS_ORIGINAL} ")
    suspend fun resetTrivia(): Int
}