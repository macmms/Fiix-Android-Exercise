package fiix.challenge.fiixexercise.kotlinsample.model

/**
 * This ui model holds the data required for [MainFragment]
 */
data class MainFragmentUiModel(
    val triviaQuestions: List<TriviaQuestionUiModel> = listOf(),
    val toastMessage: String? = null
)