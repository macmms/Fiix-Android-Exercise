package fiix.challenge.fiixexercise.kotlinsample

import android.os.Parcelable
import androidx.room.Entity
import kotlinx.android.parcel.Parcelize



@Entity(
        tableName = "questions", primaryKeys=["id"]
)

@Parcelize
data class TriviaQuestion(
        val id: Int,
        val question: String,
        var answer: String? = null,
        var answerShown: Boolean = false
): Parcelable