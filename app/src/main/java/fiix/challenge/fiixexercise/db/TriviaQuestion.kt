package fiix.challenge.fiixexercise.kotlinsample.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "trivia_question")
data class TriviaQuestion(
        @PrimaryKey(autoGenerate = true)
        var questionId: Int = 0,
        var question: String,
        var answer: String
)