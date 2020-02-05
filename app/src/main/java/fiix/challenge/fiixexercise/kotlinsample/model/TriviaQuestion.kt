package fiix.challenge.fiixexercise.kotlinsample.model

data class TriviaQuestion(val id: Int, var question: String, var answer: String? = null, var showAnswer: Boolean = false)