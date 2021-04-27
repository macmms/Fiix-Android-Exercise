package fiix.challenge.fiixexercise.dp

import fiix.challenge.fiixexercise.kotlinsample.models.TriviaQuestion
import kotlinx.coroutines.*
import kotlin.random.Random

open class DataProcessor(private val source: DataSource) {

    //DO NOT MODIFY
    val scope = CoroutineScope(Dispatchers.Default)
    //DO NOT MODIFY
    val delayModifier = Random.nextLong(2, 30)

    // region Public Methods
    suspend fun getTriviaQuestions(): List<TriviaQuestion> {
        return processDataAsync().await()
    }

    suspend fun updateTriviaQuestion(id: Int, newQuestion: String?, newAnswer: String?): Boolean {
        return processDataAsync(id = id, newQuestion = newQuestion, newAnswer = newAnswer).await()
    }

    suspend fun updateTriviaQuestion(id: Int, showAnswer: Boolean): List<TriviaQuestion> {
        return processDataAsync(id = id, showAnswer = showAnswer).await()
    }

    suspend fun getSpecificTriviaQuestion(id: Int): TriviaQuestion? {
        return processDataAsync(id = id).await()
    }
    // endregion

    // region Private Methods
    //DO NOT MODIFY THIS FUNCTION
    private suspend fun processDataAsync()= scope.async {
            delay(1000 * delayModifier)
            source.getData()
    }

    private suspend fun processDataAsync(id: Int, newQuestion: String?, newAnswer: String?) = scope.async {
        source.updateQOrA(id = id, newQuestion = newQuestion, newAnswer = newAnswer)
    }

    private suspend fun processDataAsync(id: Int, showAnswer: Boolean) = scope.async {
        source.updateAnswerVisibility(id = id, showAnswer = showAnswer)
    }

    private suspend fun processDataAsync(id: Int) = scope.async {
        source.getSpecificData(id = id)
    }
    // endregion
}

interface DataSource {
    suspend fun getData(): List<TriviaQuestion>
    suspend fun updateQOrA(id: Int, newQuestion: String?, newAnswer: String?): Boolean
    suspend fun updateAnswerVisibility(id: Int, showAnswer: Boolean): List<TriviaQuestion>
    suspend fun getSpecificData(id: Int): TriviaQuestion?
}