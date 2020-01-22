package fiix.challenge.fiixexercise.kotlinsample

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TriviaQuestion(@PrimaryKey val id: Int, val question: String, var answer: String? = null)