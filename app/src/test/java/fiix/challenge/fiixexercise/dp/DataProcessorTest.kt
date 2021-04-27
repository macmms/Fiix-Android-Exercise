package fiix.challenge.fiixexercise.dp

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import fiix.challenge.fiixexercise.kotlinsample.models.TriviaQuestion
import kotlinx.coroutines.runBlocking
import org.junit.Test

class DataProcessorTest {

    private val id2: Int = 2
    private val id3: Int = 3
    private val id4: Int = 4
    private val question2: String = "What is the title of the first Sherlock Holmes book by Arthur Conan Doyle?"
    private val question3: String = "Typically, how many keys are on a piano?"
    private val question4: String = "In what year was the first Transformers movie released?"
    private val answer2: String = "A Study in Scarlet"
    private val answer3: String = "88"
    private val answer4: String = "1986"
    private val isShownAnswerTrue: Boolean = true
    private val isShownAnswerFalse: Boolean = false

    private val mockDataSource: DataSource = mock()
    private val dataProcessor = DataProcessor(source = mockDataSource)

    @Test
    fun get_trivia_questions_data_source_method_invoke_check() {
        runBlocking {
            whenever(mockDataSource.getData()).thenReturn(stubDataList())
            dataProcessor.getTriviaQuestions()
            verify(mockDataSource).getData()
        }
    }

    @Test
    fun update_trivia_question_q_and_a_data_source_method_invoke_check() {
        runBlocking {
            whenever(mockDataSource.updateQOrA(id = id3, newQuestion = question2, newAnswer = answer2)).thenReturn(true)
            dataProcessor.updateTriviaQuestion(id = id3, newQuestion = question2, newAnswer = answer2)
            verify(mockDataSource).updateQOrA(id = id3, newQuestion = question2, newAnswer = answer2)
        }
    }

    @Test
    fun update_trivia_question_show_answer_data_source_method_invoke_check() {
        runBlocking {
            whenever(mockDataSource.updateAnswerVisibility(id = id2, showAnswer = isShownAnswerTrue)).thenReturn(stubDataList())
            dataProcessor.updateTriviaQuestion(id = id2, showAnswer = isShownAnswerTrue)
            verify(mockDataSource).updateAnswerVisibility(id = id2, showAnswer = isShownAnswerTrue)
        }
    }

    @Test
    fun get_specific_trivia_question_data_source_method_invoke_check() {
        runBlocking {
            whenever(mockDataSource.getSpecificData(id = id2)).thenReturn(stubData())
            dataProcessor.getSpecificTriviaQuestion(id = id2)
            verify(mockDataSource).getSpecificData(id = id2)
        }
    }

    // region Private Methods
    private fun stubDataList(): List<TriviaQuestion> {
        return listOf(
            TriviaQuestion(id = id2, question = question2, answer = answer2, isShownAnswer = isShownAnswerFalse),
            TriviaQuestion(id = id3, question = question3, answer = answer3, isShownAnswer = isShownAnswerTrue),
            TriviaQuestion(id = id4, question = question4, answer = answer4, isShownAnswer = isShownAnswerFalse)
        )
    }

    private fun stubData(): TriviaQuestion {
        return TriviaQuestion(id = id2, question = question2, answer = answer2, isShownAnswer = isShownAnswerFalse)
    }
    // endregion
}