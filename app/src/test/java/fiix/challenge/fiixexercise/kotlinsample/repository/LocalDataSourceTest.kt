package fiix.challenge.fiixexercise.kotlinsample.repository

import fiix.challenge.fiixexercise.kotlinsample.models.TriviaQuestion
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import org.junit.Assert.*

class LocalDataSourceTest {

    private val id1: Int = 1
    private val id2: Int = 2
    private val id3: Int = 3
    private val id4: Int = 4
    private val question1: String = "How many books are in the Chronicles of Narnia series?"
    private val question2: String = "What is the title of the first Sherlock Holmes book by Arthur Conan Doyle?"
    private val question3: String = "Typically, how many keys are on a piano?"
    private val question4: String = "In what year was the first Transformers movie released?"
    private val answer2: String = "A Study in Scarlet"
    private val answer3: String = "88"
    private val answer4: String = "1986"
    private val isShownAnswerTrue: Boolean = true
    private val isShownAnswerFalse: Boolean = false

    @ExperimentalCoroutinesApi
    @Test
    fun get_data_empty_list() {
        val localDataSource = LocalDataSource()
        localDataSource.triviaQuestionsList.clear()
        runBlockingTest {
            val dataList = localDataSource.getData()
            assertTrue(dataList.isEmpty())
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun get_data_one_item_list() {
        val localDataSource = LocalDataSource()
        localDataSource.triviaQuestionsList.clear()
        localDataSource.triviaQuestionsList.add(element = TriviaQuestion(id = id2, question = question2, answer = answer2, isShownAnswer = isShownAnswerFalse))
        runBlockingTest {
            val dataList = localDataSource.getData()
            assertEquals(localDataSource.triviaQuestionsList[0].question, dataList[0].question)
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun get_data_multiple_items_list() {
        val localDataSource = LocalDataSource()
        localDataSource.triviaQuestionsList.clear()
        localDataSource.triviaQuestionsList.add(element = TriviaQuestion(id = id2, question = question2, answer = answer2, isShownAnswer = isShownAnswerFalse))
        localDataSource.triviaQuestionsList.add(element = TriviaQuestion(id = id3, question = question3, answer = answer3, isShownAnswer = isShownAnswerTrue))
        localDataSource.triviaQuestionsList.add(element = TriviaQuestion(id = id4, question = question4, answer = answer4, isShownAnswer = isShownAnswerFalse))
        runBlockingTest {
            val dataList = localDataSource.getData()
            assertEquals(localDataSource.triviaQuestionsList[0].question, dataList[0].question)
            assertEquals(localDataSource.triviaQuestionsList[1].answer, dataList[1].answer)
            assertEquals(localDataSource.triviaQuestionsList[2].id, dataList[2].id)
            assertEquals(localDataSource.triviaQuestionsList[2].isShownAnswer, dataList[2].isShownAnswer)
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun update_q_or_a_item_found() {
        val localDataSource = LocalDataSource()
        localDataSource.triviaQuestionsList.clear()
        localDataSource.triviaQuestionsList.add(element = TriviaQuestion(id = id2, question = question2, answer = answer2, isShownAnswer = isShownAnswerFalse))
        localDataSource.triviaQuestionsList.add(element = TriviaQuestion(id = id3, question = question3, answer = answer3, isShownAnswer = isShownAnswerTrue))
        localDataSource.triviaQuestionsList.add(element = TriviaQuestion(id = id4, question = question4, answer = answer4, isShownAnswer = isShownAnswerFalse))
        runBlockingTest {
            val successful = localDataSource.updateQOrA(id = id3, newQuestion = question1, newAnswer = answer4)
            assertTrue(successful)
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun update_q_or_a_item_not_found() {
        val localDataSource = LocalDataSource()
        localDataSource.triviaQuestionsList.clear()
        localDataSource.triviaQuestionsList.add(element = TriviaQuestion(id = id2, question = question2, answer = answer2, isShownAnswer = isShownAnswerFalse))
        localDataSource.triviaQuestionsList.add(element = TriviaQuestion(id = id3, question = question3, answer = answer3, isShownAnswer = isShownAnswerTrue))
        localDataSource.triviaQuestionsList.add(element = TriviaQuestion(id = id4, question = question4, answer = answer4, isShownAnswer = isShownAnswerFalse))
        runBlockingTest {
            val successful = localDataSource.updateQOrA(id = id1, newQuestion = question1, newAnswer = answer4)
            assertFalse(successful)
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun update_q_or_a_item_found_but_nothing_to_update() {
        val localDataSource = LocalDataSource()
        localDataSource.triviaQuestionsList.clear()
        localDataSource.triviaQuestionsList.add(element = TriviaQuestion(id = id2, question = question2, answer = answer2, isShownAnswer = isShownAnswerFalse))
        localDataSource.triviaQuestionsList.add(element = TriviaQuestion(id = id3, question = question3, answer = answer3, isShownAnswer = isShownAnswerTrue))
        localDataSource.triviaQuestionsList.add(element = TriviaQuestion(id = id4, question = question4, answer = answer4, isShownAnswer = isShownAnswerFalse))
        runBlockingTest {
            val successful = localDataSource.updateQOrA(id = id2, newQuestion = question2, newAnswer = answer2)
            assertFalse(successful)
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun update_answer_visibility_item_found() {
        val localDataSource = LocalDataSource()
        localDataSource.triviaQuestionsList.clear()
        localDataSource.triviaQuestionsList.add(element = TriviaQuestion(id = id2, question = question2, answer = answer2, isShownAnswer = isShownAnswerFalse))
        localDataSource.triviaQuestionsList.add(element = TriviaQuestion(id = id3, question = question3, answer = answer3, isShownAnswer = isShownAnswerTrue))
        localDataSource.triviaQuestionsList.add(element = TriviaQuestion(id = id4, question = question4, answer = answer4, isShownAnswer = isShownAnswerFalse))
        runBlockingTest {
            val dataList = localDataSource.updateAnswerVisibility(id = id2, showAnswer = isShownAnswerTrue)
            assertTrue(dataList[0].isShownAnswer)
            assertTrue(dataList[1].isShownAnswer)
            assertFalse(dataList[2].isShownAnswer)
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun update_answer_visibility_item_not_found() {
        val localDataSource = LocalDataSource()
        localDataSource.triviaQuestionsList.clear()
        localDataSource.triviaQuestionsList.add(element = TriviaQuestion(id = id2, question = question2, answer = answer2, isShownAnswer = isShownAnswerFalse))
        localDataSource.triviaQuestionsList.add(element = TriviaQuestion(id = id3, question = question3, answer = answer3, isShownAnswer = isShownAnswerTrue))
        localDataSource.triviaQuestionsList.add(element = TriviaQuestion(id = id4, question = question4, answer = answer4, isShownAnswer = isShownAnswerFalse))
        runBlockingTest {
            val dataList = localDataSource.updateAnswerVisibility(id = id1, showAnswer = isShownAnswerTrue)
            assertFalse(dataList[0].isShownAnswer)
            assertTrue(dataList[1].isShownAnswer)
            assertFalse(dataList[2].isShownAnswer)
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun get_Specific_Data_item_found() {
        val localDataSource = LocalDataSource()
        localDataSource.triviaQuestionsList.clear()
        localDataSource.triviaQuestionsList.add(element = TriviaQuestion(id = id2, question = question2, answer = answer2, isShownAnswer = isShownAnswerFalse))
        localDataSource.triviaQuestionsList.add(element = TriviaQuestion(id = id3, question = question3, answer = answer3, isShownAnswer = isShownAnswerTrue))
        localDataSource.triviaQuestionsList.add(element = TriviaQuestion(id = id4, question = question4, answer = answer4, isShownAnswer = isShownAnswerFalse))
        runBlockingTest {
            val data = localDataSource.getSpecificData(id = id4)
            assertEquals(id4, data?.id)
            assertEquals(question4, data?.question)
            assertEquals(answer4, data?.answer)
            assertEquals(isShownAnswerFalse, data?.isShownAnswer)
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun get_Specific_Data_item_not_found() {
        val localDataSource = LocalDataSource()
        localDataSource.triviaQuestionsList.clear()
        localDataSource.triviaQuestionsList.add(element = TriviaQuestion(id = id2, question = question2, answer = answer2, isShownAnswer = isShownAnswerFalse))
        localDataSource.triviaQuestionsList.add(element = TriviaQuestion(id = id3, question = question3, answer = answer3, isShownAnswer = isShownAnswerTrue))
        localDataSource.triviaQuestionsList.add(element = TriviaQuestion(id = id4, question = question4, answer = answer4, isShownAnswer = isShownAnswerFalse))
        runBlockingTest {
            val data = localDataSource.getSpecificData(id = id1)
            assertNull(data)
        }
    }
}