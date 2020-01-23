package fiix.challenge.fiixexercise

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.test.ext.junit.runners.AndroidJUnit4
import fiix.challenge.fiixexercise.kotlinsample.data.TriviaQuestion
import fiix.challenge.fiixexercise.kotlinsample.viewmodels.TriviaViewModel
import fiix.challenge.fiixexercise.kotlinsample.data.TriviaRepository
import org.junit.*
import org.junit.runner.*
import org.mockito.Mockito.*

@RunWith(AndroidJUnit4::class)
class TriviaActivityTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    private val questionsObserver = mock(Observer::class.java) as Observer<List<TriviaQuestion>>
    private val repository = mock(TriviaRepository::class.java)

    private val viewModel = TriviaViewModel(repository)

    @Test
    fun openActivityGetsQuestions() {

        viewModel.getQuestions().observeForever(questionsObserver)
    }


    @Test
    fun clickAnswerCallsDataSource() {

    }
}