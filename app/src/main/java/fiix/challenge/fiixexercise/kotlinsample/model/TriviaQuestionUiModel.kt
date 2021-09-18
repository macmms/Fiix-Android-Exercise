package fiix.challenge.fiixexercise.kotlinsample.model

/**
 * Ui Model object for a trivia question
 *
 * This model class is different from [TriviaQuestion] in that it could have additional/lesser
 * fields relevant to the UI
 *
 * The reason for this pattern is that it doesn't tamper the network model [TriviaQuestion] (In this
 * case, we don't have this data sent by an API, but this is still a good practice)
 */
data class TriviaQuestionUiModel(
    val question: String,
    var answer: String? = null,
    var description: String? = question
)