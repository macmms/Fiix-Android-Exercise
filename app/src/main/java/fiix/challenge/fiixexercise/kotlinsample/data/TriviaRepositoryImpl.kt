package fiix.challenge.fiixexercise.kotlinsample.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer
import fiix.challenge.fiixexercise.dp.Processor
import fiix.challenge.fiixexercise.kotlinsample.data.local.TriviaQuestionDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class TriviaRepositoryImpl(
    val dao: TriviaQuestionDao,
    val mockRepo: MockRepo,
    val processor: Processor
) : TriviaRepository {

    private val questionsLive = MediatorLiveData<List<TriviaQuestion>>()

    override fun getQuestions(): LiveData<List<TriviaQuestion>> {
        val questionsLD = dao.getQuestions()
        questionsLive.addSource(questionsLD) { questions ->
            if (questions.isNullOrEmpty()) {
                runBlocking(Dispatchers.IO) { setQuestions(mockRepo.triviaQuestions) }
            } else {
                questionsLive.postValue(questions)
            }
        }
        return questionsLive
    }

    override suspend fun getAnswers(questions: List<TriviaQuestion>) {
        withContext(Dispatchers.IO) {
            val answers = processor.getAnswers()
            check(answers.size == questions.size) { "Different number of questions and answers" }
            answers.forEachIndexed { i, answer ->
                questions[i].answer = answer
            }
            setQuestions(questions)
        }
    }

    override fun getQuestion(id: Int): LiveData<TriviaQuestion> {
        return dao.getQuestion(id)
    }

    override suspend fun setQuestions(questions: List<TriviaQuestion>) {
        withContext(Dispatchers.IO) { dao.setQuestions(questions) }
    }

    override suspend fun updateQuestion(question: TriviaQuestion) {
        withContext(Dispatchers.IO) { dao.updateQuestion(question) }
    }
}