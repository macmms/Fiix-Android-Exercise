package fiix.challenge.fiixexercise.kotlinsample.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "trivia")
data class TriviaQuestion(var question: String, var answer: String? = null){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
    var answerRevealed: Boolean = false
}