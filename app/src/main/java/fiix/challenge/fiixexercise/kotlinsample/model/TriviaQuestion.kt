package fiix.challenge.fiixexercise.kotlinsample.model

import java.util.*

data class TriviaQuestion(
    val question: String,
    var answer: String? = null,
    val id: String = UUID.randomUUID().toString()
)