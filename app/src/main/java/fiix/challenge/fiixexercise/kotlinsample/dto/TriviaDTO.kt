package fiix.challenge.fiixexercise.kotlinsample.dto

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TriviaDTO (
    val id: Int,
    var question: String?,
    var answer: String?,
    var status: Int?
): Parcelable