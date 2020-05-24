package fiix.challenge.fiixexercise.kotlinsample.data.db.trivia

import androidx.room.*
import io.reactivex.Flowable
import io.reactivex.Single

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
    fun getAllTrivia(): Single<List<Trivia>>


    @Query("SELECT * FROM trivia WHERE id == :id")
    fun getTrivia(id: String): Flowable<Trivia>
}