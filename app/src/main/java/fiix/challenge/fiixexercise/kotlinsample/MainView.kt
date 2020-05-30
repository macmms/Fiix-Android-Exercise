package fiix.challenge.fiixexercise.kotlinsample

interface MainView {
    fun showError(s: String)
    fun showQuestions(questions: List<TriviaQuestion>)
}