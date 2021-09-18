package fiix.challenge.fiixexercise.dp

import dagger.hilt.android.scopes.ViewModelScoped
import fiix.challenge.fiixexercise.kotlinsample.model.TriviaQuestion
import kotlinx.coroutines.*
import javax.inject.Inject
import kotlin.random.Random

@ViewModelScoped
class DataProcessor @Inject constructor(private val source: DataSource) {

    //DO NOT MODIFY
    val scope = CoroutineScope(Dispatchers.Default)
    //DO NOT MODIFY
    val delayModifier = Random.nextLong(2, 30)

    val questionsWithoutAnswers: List<TriviaQuestion> = source.getQuestions()

    // Get answers without blocking the main thread, as it could take anywhere between 2 to 30 seconds
    suspend fun getAnswers(): List<String> = processDataAsync().await()

    //DO NOT MODIFY THIS FUNCTION
    private suspend fun processDataAsync()= scope.async {
            delay(1000 * delayModifier)
            source.getAnswers()
    }

}

interface DataSource {
    fun getQuestions(): List<TriviaQuestion>
    fun getAnswers(): List<String>
}




