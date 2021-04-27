package fiix.challenge.fiixexercise.kotlinsample.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fiix.challenge.fiixexercise.dp.DataProcessor
import fiix.challenge.fiixexercise.kotlinsample.models.TriviaQuestion
import fiix.challenge.fiixexercise.kotlinsample.repository.LocalDataSource
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class TEViewModel: ViewModel() {

    internal var dataProcessor = DataProcessor(source = LocalDataSource())

    private val _showLoading = MutableLiveData<Boolean>()
    val showLoading: LiveData<Boolean> = _showLoading

    private val _triviaQuestionsListData = MutableLiveData<List<TriviaQuestion>>()
    val triviaQuestionsListData: LiveData<List<TriviaQuestion>> = _triviaQuestionsListData

    private val _selectedTriviaQuestion = MutableLiveData<TriviaQuestion>()
    val selectedTriviaQuestion: LiveData<TriviaQuestion> = _selectedTriviaQuestion

    init {
        loadTriviaQuestions()
    }

    // region Public Methods
    suspend fun updateTriviaQuestion(id: Int, newQuestion: String?, newAnswer: String?) {
        updateShowLoading(show = true)
        if (!dataProcessor.updateTriviaQuestion(id = id, newQuestion = newQuestion, newAnswer = newAnswer)){
            updateShowLoading(show = false)
            throw Exception()
        }
        loadTriviaQuestionsHelper().join()
        updateShowLoading(show = false)
    }

    fun updateTriviaQuestion(id: Int, showAnswer: Boolean) {
        viewModelScope.launch {
            _triviaQuestionsListData.value = dataProcessor.updateTriviaQuestion(id = id, showAnswer = showAnswer)
        }
    }

    fun selectTriviaQuestion(id: Int) {
        viewModelScope.launch {
            _selectedTriviaQuestion.value = dataProcessor.getSpecificTriviaQuestion(id = id)
        }
    }
    // endregion

    // region Private Methods
    private fun updateShowLoading(show: Boolean) {
        _showLoading.value = show
    }

    private suspend fun loadTriviaQuestionsHelper(): Job {
        return viewModelScope.launch {
            _triviaQuestionsListData.value = dataProcessor.getTriviaQuestions()
        }
    }

    private fun loadTriviaQuestions() {
        viewModelScope.launch {
            updateShowLoading(show = true)
            loadTriviaQuestionsHelper().join()
            updateShowLoading(show = false)
        }
    }
    // endregion
}