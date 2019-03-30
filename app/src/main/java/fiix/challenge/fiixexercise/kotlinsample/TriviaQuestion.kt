package fiix.challenge.fiixexercise.kotlinsample

import java.io.Serializable

data class TriviaQuestion(val question: String, val answer: String, var seen: Boolean):Serializable