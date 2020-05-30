package fiix.challenge.fiixexercise.kotlinsample.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Trivia (
    @PrimaryKey
    val id: Int,
    val question: String?,
    val answer: String?,
    val status: Int?
)