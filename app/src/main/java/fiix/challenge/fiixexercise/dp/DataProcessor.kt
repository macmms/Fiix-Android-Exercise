package fiix.challenge.fiixexercise.dp

import fiix.challenge.fiixexercise.model.TriviaQuestion
import kotlinx.coroutines.*
import kotlin.random.Random

class DataProcessor(private val source: DataSource): BaseDataProcessor {

    //DO NOT MODIFY
    val scope = CoroutineScope(Dispatchers.Default)
    //DO NOT MODIFY
    val delayModifier = Random.nextLong(2, 30)


    override fun getAnswers(): List<String> {
        return runBlocking {
            processDataAsync().await()
        }
    }

    override fun getQuestions(): List<TriviaQuestion> {
        return runBlocking {
            processQuestionsAsync().await()
        }
    }

    //DO NOT MODIFY THIS FUNCTION
    private suspend fun processDataAsync()= scope.async {
            delay(1000 * delayModifier)
            source.getData()
    }

    private fun processQuestionsAsync()= scope.async {
        source.getQuestion()
    }

}

interface DataSource {
    fun getData(): List<String>
    fun getQuestion(): List<TriviaQuestion>
}

interface BaseDataProcessor{
    fun getAnswers(): List<String>
    fun getQuestions(): List<TriviaQuestion>
}



