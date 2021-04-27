package fiix.challenge.fiixexercise.kotlinsample.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.*
import fiix.challenge.fiixexercise.dp.DataProcessor
import fiix.challenge.fiixexercise.kotlinsample.models.TriviaQuestion
import fiix.challenge.fiixexercise.kotlinsample.repository.LocalDataSource
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.Rule

class TEViewModelTest {

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val id1: Int = 1
    private val id2: Int = 2
    private val id3: Int = 3
    private val id4: Int = 4
    private val question1: String = "How many books are in the Chronicles of Narnia series?"
    private val question2: String = "What is the title of the first Sherlock Holmes book by Arthur Conan Doyle?"
    private val question3: String = "Typically, how many keys are on a piano?"
    private val question4: String = "In what year was the first Transformers movie released?"
    private val answer1: String = "7"
    private val answer2: String = "A Study in Scarlet"
    private val answer3: String = "88"
    private val answer4: String = "1986"
    private val isShownAnswerTrue: Boolean = true
    private val isShownAnswerFalse: Boolean = false

    @Test
    fun update_trivia_question_fail_trigger_loading() {
        val showLoadingObserver: Observer<Boolean> = mock()
        val mockDataSource = LocalDataSource()
        val mockDataProcessor = DataProcessor(source = mockDataSource)
        mockDataSource.triviaQuestionsList.clear()
        runBlocking {
            val teViewModel = TEViewModel()
            teViewModel.showLoading.observeForever(showLoadingObserver)
            teViewModel.dataProcessor = mockDataProcessor
            try {
                teViewModel.updateTriviaQuestion(id = id1, newQuestion = question1, newAnswer = answer1)
            } catch (ex: Exception) {
                verify(showLoadingObserver, atLeastOnce()).onChanged(true)
                verify(showLoadingObserver, atLeastOnce()).onChanged(false)
            }
            teViewModel.showLoading.removeObserver(showLoadingObserver)
        }
    }

    @Test
    fun update_trivia_question_successful_trigger_loading() {
        val showLoadingObserver: Observer<Boolean> = mock()
        val mockDataSource = LocalDataSource()
        val mockDataProcessor = DataProcessor(source = mockDataSource)
        mockDataSource.triviaQuestionsList.clear()
        mockDataSource.triviaQuestionsList.addAll(elements = stubDataList())
        runBlocking {
            val teViewModel = TEViewModel()
            teViewModel.showLoading.observeForever(showLoadingObserver)
            teViewModel.dataProcessor = mockDataProcessor
            teViewModel.updateTriviaQuestion(id = id2, newQuestion = question3, newAnswer = answer3)
            verify(showLoadingObserver, atLeastOnce()).onChanged(true)
            verify(showLoadingObserver, atLeastOnce()).onChanged(false)
            teViewModel.showLoading.removeObserver(showLoadingObserver)
        }
    }

    @Test
    fun update_trivia_question_successful_data_processor_method_call_check() {
        val mockDataSource = LocalDataSource()
        val mockDataProcessor = DataProcessor(source = mockDataSource)
        mockDataSource.triviaQuestionsList.clear()
        mockDataSource.triviaQuestionsList.addAll(elements = stubDataList())
        runBlocking {
            val teViewModel = TEViewModel()
            val spyDataProcessor = spy(mockDataProcessor)
            teViewModel.dataProcessor = spyDataProcessor
            teViewModel.updateTriviaQuestion(id = id3, newQuestion = question1, newAnswer = answer1)
            verify(spyDataProcessor).updateTriviaQuestion(id = id3, newQuestion = question1, newAnswer = answer1)
        }
    }

    @Test
    fun select_trivia_question_selected_data_observer_check() {
        val selectedTriviaQuestionObserver: Observer<TriviaQuestion> = mock()
        val mockDataSource = LocalDataSource()
        val mockDataProcessor = DataProcessor(source = mockDataSource)
        mockDataSource.triviaQuestionsList.clear()
        mockDataSource.triviaQuestionsList.addAll(elements = stubDataList())
        runBlocking {
            val teViewModel = TEViewModel()
            teViewModel.selectedTriviaQuestion.observeForever(selectedTriviaQuestionObserver)
            teViewModel.dataProcessor = mockDataProcessor
            teViewModel.selectTriviaQuestion(id = id4)
        }
        verify(selectedTriviaQuestionObserver, atMost(1)).onChanged(any())
    }

    // region Private Methods
    private fun stubDataList(): List<TriviaQuestion> {
        return listOf(
            TriviaQuestion(id = id2, question = question2, answer = answer2, isShownAnswer = isShownAnswerFalse),
            TriviaQuestion(id = id3, question = question3, answer = answer3, isShownAnswer = isShownAnswerTrue),
            TriviaQuestion(id = id4, question = question4, answer = answer4, isShownAnswer = isShownAnswerFalse)
        )
    }
    // endregion
}