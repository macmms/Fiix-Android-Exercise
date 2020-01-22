package fiix.challenge.fiixexercise.kotlinsample

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import fiix.challenge.fiixexercise.dp.DataProcessor
import fiix.challenge.fiixexercise.dp.Processor
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import org.junit.*
import org.mockito.*
import org.mockito.Mockito.*

class TriviaViewModelTest {

    @Rule
    @JvmField
    var rule = InstantTaskExecutorRule()

    private val answerObserver = mock(Observer::class.java) as Observer<List<String>>
    private val repository = mock(Observer::class.java)
    private val dataProcessor = mock(Processor::class.java)

    private lateinit var viewModel: TriviaViewModel

    @Before
    fun setup() {
        viewModel = TriviaViewModel()
        viewModel.dp = dataProcessor
    }

    @Test
    fun clickAnswerCallsDataSource() {
        viewModel.getAnswers().observeForever(answerObserver)
        viewModel.fetchAnswer(0)
        verify(dataProcessor, atLeastOnce()).getAnswers()
        verify(answerObserver).onChanged(ArgumentMatchers.any())
    }


}