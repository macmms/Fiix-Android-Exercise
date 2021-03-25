package fiix.challenge.fiixexercise

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import fiix.challenge.fiixexercise.model.TriviaQuestion
import fiix.challenge.fiixexercise.repositories.MockRepo
import fiix.challenge.fiixexercise.viewmodel.MainViewModel
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule


class MainViewModelTest {
    private lateinit var mainnViewModel: MainViewModel
    private lateinit var mockDataProcessor: MockDataProcessor

    private lateinit var mockDataSource: MockDataSource
    private lateinit var mockRepo: MockRepo

    @Rule
    @JvmField
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @Rule
    @JvmField
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        mockRepo = MockRepo()
        mockDataSource = MockDataSource(mockRepo)
        mockDataProcessor = MockDataProcessor(mockDataSource)
        mainnViewModel = MainViewModel(mockDataProcessor)
    }

    @Test
    fun syncQuestionAnswer(){
        mainnViewModel.syncQuestionAnswers()
        Assert.assertNotNull(mainnViewModel.questions.value)
    }

    @Test
    fun getSelectedQuestionAnswer_Success(){
        val mockData =  TriviaQuestion("How many books are in the Chronicles of Narnia series?","7")
        mainnViewModel.syncQuestionAnswers()
        mainnViewModel.getSelectedQuestionAnswer(0)
        Assert.assertEquals(mainnViewModel.selectedQuestion.value,mockData)
    }

    @Test
    fun getSelectedQuestionAnswer_Failure(){
        val mockData =  TriviaQuestion("How many books are in the Chronicles of Narnia series?","7")
        mainnViewModel.syncQuestionAnswers()
        mainnViewModel.getSelectedQuestionAnswer(10)
        Assert.assertNotEquals(mainnViewModel.selectedQuestion.value,mockData)
    }

    @Test
    fun updateQuestionAnswer_Success(){
        val mockQuestion = "How many books are in the Chronicles of Narnia series You Will check?"
        val mockAnswer = "7"
        val position = 0
        mainnViewModel.syncQuestionAnswers()
        mainnViewModel.updateQuestionAnswer(mockQuestion,mockAnswer,position)
        Assert.assertEquals(mainnViewModel.questions.value?.get(position)?.question,mockQuestion)
    }

    @Test
    fun updateAnswer_Success(){
        val mockQuestion = "How many books are in the Chronicles of Narnia series?"
        val mockAnswer = "74"
        val position = 0
        mainnViewModel.syncQuestionAnswers()
        mainnViewModel.updateQuestionAnswer(mockQuestion,mockAnswer,position)
        Assert.assertEquals(mainnViewModel.questions.value?.get(position)?.answer,mockAnswer)
    }

}