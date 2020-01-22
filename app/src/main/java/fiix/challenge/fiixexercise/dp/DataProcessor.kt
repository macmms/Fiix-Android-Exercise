package fiix.challenge.fiixexercise.dp

import kotlinx.coroutines.*
import kotlin.random.Random

open class DataProcessor(private val source: DataSource): Processor {

    //DO NOT MODIFY
    val scope = CoroutineScope(Dispatchers.Default)
    //DO NOT MODIFY
    val delayModifier = Random.nextLong(2, 30)


    override fun getAnswers(): List<String> {
        return runBlocking {
            processDataAsync().await()
        }
    }

    //DO NOT MODIFY THIS FUNCTION
    private suspend fun processDataAsync()= scope.async {
            delay(1000 * delayModifier)
            source.getData()
    }

}

interface DataSource {
    fun getData(): List<String>
}

interface Processor {
    fun getAnswers(): List<String>
}




