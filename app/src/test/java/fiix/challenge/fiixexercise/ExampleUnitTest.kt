package fiix.challenge.fiixexercise

import fiix.challenge.fiixexercise.kotlinsample.MockRepo
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun isDataCountCorrect() {
        val mockRepo = MockRepo()
        assertEquals(mockRepo.triviaQuestions.size, 12)
    }

    @Test
    fun isDataValid() {
        val mockRepo = MockRepo()
        val question = mockRepo.triviaQuestions.get(0)
        assertEquals(question.answer, "7")
        assertEquals(question.questionId, 1)
        assertEquals(question.question, "How many books are in the Chronicles of Narnia series?")
    }

}
