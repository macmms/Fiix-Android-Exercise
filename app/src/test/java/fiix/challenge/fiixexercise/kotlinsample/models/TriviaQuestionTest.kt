package fiix.challenge.fiixexercise.kotlinsample.models

import org.junit.Test

import org.junit.Assert.*

class TriviaQuestionTest {

    private val testId: Int = 10
    private val testQuestion: String = "This is a test question"
    private val testAnswer: String = "This is a test answer"
    private val testShowAnswer: Boolean = true

    @Test
    fun get_id() {
        val model = TriviaQuestion(id = testId)
        assertEquals(testId, model.id)
    }

    @Test
    fun set_Id() {
        val model = TriviaQuestion()
        model.id = testId
        assertEquals(testId, model.id)
    }

    @Test
    fun get_question_string() {
        val model = TriviaQuestion(question = testQuestion)
        assertEquals(testQuestion, model.question)
    }

    @Test
    fun get_question_null() {
        val model = TriviaQuestion(question = null)
        assertNull(model.question)
    }

    @Test
    fun set_question() {
        val model = TriviaQuestion()
        model.question = testQuestion
        assertEquals(testQuestion, model.question)
    }

    @Test
    fun get_answer_string() {
        val model = TriviaQuestion(answer = testAnswer)
        assertEquals(testAnswer, model.answer)
    }

    @Test
    fun get_answer_null() {
        val model = TriviaQuestion(answer = null)
        assertNull(model.answer)
    }

    @Test
    fun set_answer() {
        val model = TriviaQuestion()
        model.answer = testAnswer
        assertEquals(testAnswer, model.answer)
    }

    @Test
    fun is_shown_answer_true() {
        val model = TriviaQuestion(isShownAnswer = testShowAnswer)
        assertEquals(testShowAnswer, model.isShownAnswer)
    }

    @Test
    fun is_shown_answer_false() {
        val model = TriviaQuestion(isShownAnswer = !testShowAnswer)
        assertEquals(!testShowAnswer, model.isShownAnswer)
    }

    @Test
    fun set_shown_answer() {
        val model = TriviaQuestion()
        model.isShownAnswer = testShowAnswer
        assertEquals(testShowAnswer, model.isShownAnswer)
    }
}