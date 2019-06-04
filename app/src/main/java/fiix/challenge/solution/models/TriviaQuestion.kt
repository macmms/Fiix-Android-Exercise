package fiix.challenge.solution.models

import java.io.Serializable

data class TriviaQuestion(val question: String, val answer: String, var isCheckedOrAnswered: Boolean) : Serializable