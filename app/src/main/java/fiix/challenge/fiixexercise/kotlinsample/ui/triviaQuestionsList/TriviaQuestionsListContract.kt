package fiix.challenge.fiixexercise.kotlinsample.ui.triviaQuestionsList

interface TriviaQuestionsListContract {

    interface View {
        fun onAnswerClick(id: Int)
        fun onItemClick(id: Int)
    }
}