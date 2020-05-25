package fiix.challenge.fiixexercise.db

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import fiix.challenge.fiixexercise.getOrAwaitValue
import fiix.challenge.fiixexercise.kotlinsample.db.AbstractDatabaseTest
import fiix.challenge.fiixexercise.kotlinsample.db.TriviaQuestion
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class TriviaDatabaseTest : AbstractDatabaseTest() {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun readTriviaDefaultQuestionFromDb() {
        val insertedQuestion = database.triviaQuestionDao().retrieveAllQuestions().getOrAwaitValue()

        assertEquals("Question 1", insertedQuestion[0].question)
        assertEquals("Answer 1", insertedQuestion[0].answer)
    }

    @Test
    fun readTriviaInsertedQuestionFromDb() {

        val triviaQuestionList = listOf(TriviaQuestion(question = "Question 2", answer = "Answer 2"))
        database.triviaQuestionDao().insertQuestions(triviaQuestionList)
        val insertedQuestion = database.triviaQuestionDao().retrieveAllQuestions().getOrAwaitValue()

        assertEquals("Question 2", insertedQuestion[1].question)
        assertEquals("Answer 2", insertedQuestion[1].answer)
    }

    @Test
    fun updateTriviaQuestionFromDb() {
        val triviaQuestion = database.triviaQuestionDao().retrieveQuestion(1).getOrAwaitValue()

        val updatedQuestion = "Updated Question"
        triviaQuestion.question = updatedQuestion

        val updatedAnswer = "Updated Answer"
        triviaQuestion.answer = updatedAnswer

        database.triviaQuestionDao().updateQuestion(triviaQuestion)
        val updatedTriviaQuestion = database.triviaQuestionDao().retrieveQuestion(1).getOrAwaitValue()

        assertEquals(updatedQuestion, updatedTriviaQuestion.question)
        assertEquals(updatedAnswer, updatedTriviaQuestion.answer)
    }


}