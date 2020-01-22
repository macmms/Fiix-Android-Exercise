package fiix.challenge.fiixexercise

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.nhaarman.mockito_kotlin.*
import fiix.challenge.fiixexercise.dp.Processor
import fiix.challenge.fiixexercise.kotlinsample.TriviaQuestion
import fiix.challenge.fiixexercise.kotlinsample.TriviaViewModel
import fiix.challenge.fiixexercise.kotlinsample.data.TriviaRepository
import org.junit.*
import org.junit.runner.*
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

@RunWith(AndroidJUnit4::class)
class TriviaViewModelTest {

    @Rule
    @JvmField
    var rule = InstantTaskExecutorRule()

    private val repository = mock(TriviaRepository::class.java)

    private lateinit var viewModel: TriviaViewModel

    @Before
    fun setup() {
        viewModel = TriviaViewModel(repository)
    }

    @Test
    fun callToQuestionsGetsAnswersAsWell() {
        viewModel.getQuestions()
        verify(repository, times(1)).getAnswers()
    }

    @Test
    fun getAnswerSetsShowAnswerToTrue() {
        val question = TriviaQuestion(0, "How are you?", "Good")
        assert(!question.showAnswer)
        val questionLiveData = MutableLiveData<TriviaQuestion>().apply { value = question }
        whenever(repository.getQuestion(any())).thenReturn(questionLiveData)
        viewModel.getAnswer(0)
        assert(question.showAnswer)
    }
}