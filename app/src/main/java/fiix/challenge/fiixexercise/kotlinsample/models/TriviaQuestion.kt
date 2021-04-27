package fiix.challenge.fiixexercise.kotlinsample.models

data class TriviaQuestion(
    var id: Int = 0,
    var question: String? = null,
    var answer: String? = null,
    var isShownAnswer: Boolean = false
)