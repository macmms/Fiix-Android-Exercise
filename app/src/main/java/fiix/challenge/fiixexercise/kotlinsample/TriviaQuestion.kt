package fiix.challenge.fiixexercise.kotlinsample

import android.os.Parcel
import android.os.Parcelable

data class TriviaQuestion(var question: String? = null, var answer: String? = null, var isShowing:Boolean, var id:Int): Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readByte() != 0.toByte(),
            parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(question)
        parcel.writeString(answer)
        parcel.writeByte(if (isShowing) 1 else 0)
        parcel.writeInt(id)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<TriviaQuestion> {
        override fun createFromParcel(parcel: Parcel): TriviaQuestion {
            return TriviaQuestion(parcel)
        }

        override fun newArray(size: Int): Array<TriviaQuestion?> {
            return arrayOfNulls(size)
        }
    }
}