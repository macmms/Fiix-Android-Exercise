package fiix.challenge.fiixexercise.kotlinsample.data

import androidx.lifecycle.LiveData
import fiix.challenge.fiixexercise.dp.Processor
import fiix.challenge.fiixexercise.kotlinsample.MockRepo
import fiix.challenge.fiixexercise.kotlinsample.TriviaQuestion
import fiix.challenge.fiixexercise.kotlinsample.data.local.TriviaQuestionDao

class TriviaRepositoryImpl(
    val dao: TriviaQuestionDao,
    val mockRepo: MockRepo,
    val processor: Processor): TriviaRepository {

    override fun getQuestions(): LiveData<List<TriviaQuestion>> {
        if (dao.getQuestions().value.isNullOrEmpty()) {
            dao.setQuestions(mockRepo.triviaQuestions)
        }
        return dao.getQuestions()
    }

    override fun getAnswers() {
        val answers = processor.getAnswers()
        val questions = getQuestions().value ?: throw IllegalStateException("Questions must be obtained before answers")
        check(answers.size == questions.size) { "Different number of questions and answers" }
        answers.forEachIndexed { i, answer ->
            questions[i].answer = answer
        }
        dao.setQuestions(questions)
    }

    override fun getQuestion(id: Int): LiveData<TriviaQuestion> {
        return dao.getQuestion(id)
    }

    override fun setQuestions(questions: List<TriviaQuestion>) {
        dao.setQuestions(questions)
    }

    override fun updateQuestion(question: TriviaQuestion) {
        dao.updateQuestion(question)
    }
}