package fiix.challenge.fiixexercise.dp

import fiix.challenge.fiixexercise.kotlinsample.MockRepo
import fiix.challenge.fiixexercise.kotlinsample.TriviaQuestion
import kotlinx.coroutines.*
import kotlin.random.Random

class DataProcessor(private val source: DataSource) {

    //DO NOT MODIFY
    val scope = CoroutineScope(Dispatchers.Default)
    //DO NOT MODIFY
    val delayModifier = 5L // Random.nextLong(2, 30)


    suspend fun getAnswers(): Deferred<List<String>> = processDataAsync()

    //DO NOT MODIFY THIS FUNCTION
    private suspend fun processDataAsync()= scope.async {
            delay(1000 * delayModifier)
            source.getData()
    }

    suspend fun getQuestions() : Deferred<List<TriviaQuestion>> = fetchQuestionsAsync()

    private suspend fun fetchQuestionsAsync()= scope.async {
        delay(3000L)
        MockRepo.triviaQuestions
    }

}

interface DataSource {
    fun getData(): List<String>
}




