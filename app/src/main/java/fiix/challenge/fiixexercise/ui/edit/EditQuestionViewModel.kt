package fiix.challenge.fiixexercise.ui.edit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import fiix.challenge.fiixexercise.TriviaQuestionRepository
import fiix.challenge.fiixexercise.db.ioThread
import fiix.challenge.fiixexercise.kotlinsample.db.TriviaQuestion
import fiix.challenge.fiixexercise.ui.TriviaQuestionModel

class EditQuestionViewModel constructor(private val repository: TriviaQuestionRepository) : ViewModel() {

    private val _triviaQuestionLiveData: MutableLiveData<Int> = MutableLiveData()
    val triviaQuestionLiveData: LiveData<TriviaQuestionModel>

    init {
        triviaQuestionLiveData = Transformations.switchMap(_triviaQuestionLiveData) { questionId ->
            Transformations.map(repository.retrieveTriviaQuestion(questionId)) {
                TriviaQuestionModel(it.questionId, it.question, it.answer, false)
            }
        }
    }

    fun loadQuestion(questionId: Int) {
        _triviaQuestionLiveData.value = questionId
    }

    fun save(question: String, answer: String) {
        val temp = triviaQuestionLiveData.value
        temp?.id?.let {
            val updatedQuestion = TriviaQuestion(temp.id, question, answer)
             repository.updateQuestion(updatedQuestion)
        }
    }
}