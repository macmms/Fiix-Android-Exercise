package fiix.challenge.fiixexercise.kotlinsample.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "questions")
data class TriviaQuestion(
        @PrimaryKey
        val id: Long,
        var question: String,
        var answer: String? = null,
        var hasClickedAnswer: Boolean = false
)