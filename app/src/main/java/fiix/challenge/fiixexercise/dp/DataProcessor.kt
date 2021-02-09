package fiix.challenge.fiixexercise.dp

import fiix.challenge.fiixexercise.kotlinsample.data.TriviaQuestion
import kotlinx.coroutines.*
import kotlin.random.Random

class DataProcessor(private val source: DataSource) {

    //DO NOT MODIFY
    val scope = CoroutineScope(Dispatchers.Default)
    //DO NOT MODIFY
    val delayModifier = Random.nextLong(2, 30)


    suspend fun getSeedData(): List<TriviaQuestion> {
        return processDataAsync().await()
    }

    //DO NOT MODIFY THIS FUNCTION
    private suspend fun processDataAsync()= scope.async {
            delay(1000 * delayModifier)
            source.getData()
    }

}

interface DataSource {
    fun getData(): List<TriviaQuestion>
}




