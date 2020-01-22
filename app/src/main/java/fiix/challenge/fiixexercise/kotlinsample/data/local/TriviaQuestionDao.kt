package fiix.challenge.fiixexercise.kotlinsample.data.local

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import fiix.challenge.fiixexercise.kotlinsample.TriviaQuestion

interface TriviaQuestionDao {
    @Query("SELECT * FROM TriviaQuestion")
    fun getQuestions(): LiveData<List<TriviaQuestion>>

    @Query("SELECT * FROM TriviaQuestion WHERE id = :id")
    fun getQuestion(id: Int): LiveData<TriviaQuestion>

    @Insert
    fun setQuestions(questions: List<TriviaQuestion>)

    @Update
    fun updateQuestion(question: TriviaQuestion)
}