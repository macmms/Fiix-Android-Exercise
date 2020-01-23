package fiix.challenge.fiixexercise.kotlinsample.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TriviaQuestion(@PrimaryKey val id: Int, val question: String, var answer: String? = null, var showAnswer: Boolean = false)