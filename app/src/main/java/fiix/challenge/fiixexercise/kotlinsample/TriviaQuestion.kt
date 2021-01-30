package fiix.challenge.fiixexercise.kotlinsample

import java.io.Serializable

data class TriviaQuestion(var question: String, var answer: String? = null, var isVisible: Boolean = false) : Serializable