package fiix.challenge.fiixexercise.kotlinsample.data.db.trivia

import androidx.room.*

/**
 * DAO for the Trivia table
 */
@Dao
interface TriviaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(trivia: List<Trivia>)

    @Update
    fun update(trivia: Trivia)

    @Query("SELECT * FROM trivia")
    fun getAllTrivia(): List<Trivia>

    @Query("SELECT * FROM trivia WHERE id == :id")
    fun getTrivia(id: String): Trivia?
}