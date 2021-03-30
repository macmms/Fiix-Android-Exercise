package fiix.challenge.fiixexercise.dp

import fiix.challenge.fiixexercise.kotlinsample.buisness.datasource.cache.abstraction.DataSourceService
import fiix.challenge.fiixexercise.kotlinsample.buisness.domain.TriviaQuestion
import kotlinx.coroutines.*
import javax.inject.Inject
import kotlin.random.Random

class DataProcessor @Inject constructor(private val source: DataSourceService) {

    //DO NOT MODIFY
    val scope = CoroutineScope(Dispatchers.Default)
    //DO NOT MODIFY
    val delayModifier = Random.nextLong(2, 30)

    fun getAnswers(): List<String> {
        return runBlocking {
            processDataAsync().await()
        }
    }

    fun getQuestion(): List<TriviaQuestion>{
        return runBlocking {
            processDataQuestionAsync().await()
        }
    }

    //DO NOT MODIFY THIS FUNCTION
    private suspend fun processDataAsync()= scope.async {
            delay(1000 * delayModifier)
        source.getData()
    }

    private fun processDataQuestionAsync()= scope.async {
        source.getQuestion()
    }

}






