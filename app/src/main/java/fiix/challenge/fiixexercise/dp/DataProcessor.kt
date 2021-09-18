package fiix.challenge.fiixexercise.dp

import kotlinx.coroutines.*
import kotlin.random.Random

class DataProcessor(private val source: DataSource) {

    //DO NOT MODIFY
    val scope = CoroutineScope(Dispatchers.Default)
    //DO NOT MODIFY
    val delayModifier = Random.nextLong(2, 30)

    // Get answers without blocking the main thread, as it could take anywhere between 2 to 30 seconds
    suspend fun getAnswers(): List<String> = processDataAsync().await()

    //DO NOT MODIFY THIS FUNCTION
    private suspend fun processDataAsync()= scope.async {
            delay(1000 * delayModifier)
            source.getData()
    }

}

interface DataSource {
    fun getData(): List<String>
}




