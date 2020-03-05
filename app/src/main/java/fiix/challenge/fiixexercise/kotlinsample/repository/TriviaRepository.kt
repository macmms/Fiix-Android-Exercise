package fiix.challenge.fiixexercise.kotlinsample.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import fiix.challenge.fiixexercise.dp.DataProcessor
import fiix.challenge.fiixexercise.kotlinsample.datasource.MockRepo
import fiix.challenge.fiixexercise.kotlinsample.db.TriviaDAO
import fiix.challenge.fiixexercise.kotlinsample.db.model.TriviaQuestion
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

class TriviaRepository(private val processor: DataProcessor, private val triviaDAO: TriviaDAO) {

    companion object {
        private var triviaRepository: TriviaRepository? = null

        fun getInstance(processor: DataProcessor, triviaDAO: TriviaDAO): TriviaRepository? {
            if (triviaRepository == null) {
                triviaRepository = TriviaRepository(processor, triviaDAO)
            }
            return triviaRepository as TriviaRepository
        }
    }

    private val questionsLive = MediatorLiveData<List<TriviaQuestion>>()

    fun insertTrivia(triviaQuestions: List<TriviaQuestion>) {
        insertAnswers(triviaQuestions)
    }

    fun updateTrivia(triviaQuestion: TriviaQuestion) {
        return runBlocking {
            updateTriviaQuest(triviaQuestion).await()
        }
    }

    fun getTrivia(): LiveData<List<TriviaQuestion>> {
        val allQuestions = triviaDAO.getAll()
        questionsLive.addSource(allQuestions) { questions ->
            if (questions.isNullOrEmpty()) {
                insertQuestions(MockRepo().triviaQuestions)
            } else {
                questionsLive.postValue(questions)
            }
        }
        return questionsLive
    }

    fun getTriviaQuestion(questionId: Int): TriviaQuestion {
        return runBlocking {
            getTrivia(questionId).await()
        }
    }

    private fun insertQuestions(triviaQuestion: List<TriviaQuestion>) = CoroutineScope(Dispatchers.IO).async {
        triviaDAO.insertTriviaItem(triviaQuestion)

    }

    private fun insertAnswers(triviaQuestions: List<TriviaQuestion>) = CoroutineScope(Dispatchers.IO).async {
        val answers = processor.getAnswers()
        if (answers.size == triviaQuestions.size) {
            answers.forEachIndexed { index, s ->
                triviaQuestions[index].answer = s
                triviaDAO.updateTriviaItem(triviaQuestions[index])
            }
        }
    }

    private fun getTrivia(questionId: Int) = CoroutineScope(Dispatchers.IO).async {
        triviaDAO.getTriviaQuestion(questionId)
    }
    private fun updateTriviaQuest(question: TriviaQuestion) = CoroutineScope(Dispatchers.IO)
            .async {
                triviaDAO.updateTriviaItem(question)
            }

}