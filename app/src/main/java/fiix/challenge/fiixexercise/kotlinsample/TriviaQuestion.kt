package fiix.challenge.fiixexercise.kotlinsample

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TriviaQuestion(val question: String, var answer: String? = null) : Parcelable