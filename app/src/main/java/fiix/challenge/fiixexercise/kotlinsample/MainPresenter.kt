package fiix.challenge.fiixexercise.kotlinsample

import fiix.challenge.fiixexercise.dp.DataProcessor
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MainPresenter(val dp: DataProcessor, val view: MainView) : CoroutineScope {
    private var job: Job = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    fun bind() {
        launch {
            println("start")
            val answersDeferred = dp.getAnswers()
            val questionsDeferred = dp.getQuestions()
            // wait for both async jobs to finish
            if (answersDeferred.await().size != questionsDeferred.await().size) {
                withContext(Dispatchers.Main) {
                    view.showError("something went wrong")
                }
            } else {
                val answers = answersDeferred.await()
                val questions = questionsDeferred.await()
                questions.forEachIndexed { index, triviaQuestion ->
                    triviaQuestion.answer = answers[index]
                }
                withContext(Dispatchers.Main) {
                    println("update UI")
                    view.showQuestions(questions)
                }
            }
        }
    }

    fun unBind() {
        job.cancel()
    }
}