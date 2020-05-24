package fiix.challenge.fiixexercise.kotlinsample.data.db.trivia

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Defines the trivia table entry
 */
@Entity
data class Trivia(
        var question: String,
        var answer: String? = null,
        @PrimaryKey(autoGenerate = true) val id: Int = 0
) {
    companion object {
        const val TABLE_NAME = "Trivia"
    }
}