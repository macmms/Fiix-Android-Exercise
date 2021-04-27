package fiix.challenge.fiixexercise.kotlinsample.activities.main

interface MainActivityContract {

    interface Router {
        fun goToTriviaQuestionsListScreen()
        fun goToTriviaQuestionDetailsScreen()
        fun popBack()
    }
}