package fiix.challenge.fiixexercise.kotlinsample.data

import fiix.challenge.fiixexercise.dp.DataProcessor
import fiix.challenge.fiixexercise.dp.DataSource
import fiix.challenge.fiixexercise.kotlinsample.data.db.TriviaDatabase
import fiix.challenge.fiixexercise.kotlinsample.data.db.trivia.Trivia
import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito

class TriviaRepositoryTest {

    private val database = Mockito.mock(TriviaDatabase::class.java)
    private val dataProcessor = DataProcessor(Mockito.mock(DataSource::class.java))
    private val triviaRepo = TriviaRepository(database, dataProcessor)


    @Test
    fun testUpdateTriviaWithAnswersDifferentSize() {
        val trivia = emptyList<Trivia>()
        val answers = listOf("a1")
        val result = triviaRepo.updateTriviaWithAnswers(trivia, answers)
        Assert.assertTrue(result.isEmpty())
    }

    @Test
    fun testUpdateTriviaWithAnswersSameSize() {
        val trivia = listOf(
                Trivia(question = "q1"),
                Trivia(question = "q2"),
                Trivia(question = "q3"),
                Trivia(question = "q4"))
        val answers = listOf("a1", "a2", "a3", "a4")
        val result = triviaRepo.updateTriviaWithAnswers(trivia, answers)
        Assert.assertTrue(result.size == trivia.size)
    }


}