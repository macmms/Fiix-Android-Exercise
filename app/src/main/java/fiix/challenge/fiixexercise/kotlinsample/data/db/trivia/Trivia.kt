package fiix.challenge.fiixexercise.kotlinsample.data.db.trivia

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Defines the trivia table entry
 */
@Entity
data class Trivia(
        val question: String,
        val answer: String? = null,
        val isAnswerRevealed: Boolean = false,
        @PrimaryKey(autoGenerate = true) val id: Int = 0
) {
    companion object {
        const val TABLE_NAME = "Trivia"
    }
}