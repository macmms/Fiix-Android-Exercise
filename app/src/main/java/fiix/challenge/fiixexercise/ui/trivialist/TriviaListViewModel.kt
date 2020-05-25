package fiix.challenge.fiixexercise.ui.trivialist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import fiix.challenge.fiixexercise.TriviaQuestionRepository
import fiix.challenge.fiixexercise.ui.TriviaQuestionModel

class TriviaListViewModel constructor(private val repository: TriviaQuestionRepository) : ViewModel() {

    var listener: OnTriviaQuestionSelectedListener? = null

    private val _triviaListLiveData: MediatorLiveData<List<TriviaQuestionModel>> = MediatorLiveData()
    val triviaListLiveData: LiveData<List<TriviaQuestionModel>>
        get() = _triviaListLiveData

    fun showAnswer(id: Int) {
        val triviaQuestions = triviaListLiveData.value
        if (triviaQuestions != null) {
            triviaQuestions.find { it.id == id }?.isShowingAnswer = true
            _triviaListLiveData.value = triviaQuestions
        }

    }

    fun loadTriviaList() {
        _triviaListLiveData.addSource(repository.retrieveTriviaQuestions()) { triviaList ->
            val temp = triviaList.map {
                TriviaQuestionModel(it.questionId, it.question, it.answer, false)
            }
            _triviaListLiveData.value = temp
        }
    }

    fun editQuestion(questionId: Int) {
        listener?.onTriviaQuestionClicked(questionId)
    }
}