package fiix.challenge.fiixexercise.kotlinsample.ui.questions

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import fiix.challenge.fiixexercise.dp.DataProcessor
import fiix.challenge.fiixexercise.kotlinsample.data.TriviaQuestion
import fiix.challenge.fiixexercise.kotlinsample.repository.QuestionRepository
import kotlinx.coroutines.launch

class QuestionsViewModel @ViewModelInject constructor(
        private val questionRepository: QuestionRepository,
        private val dataProcessor: DataProcessor,
) : ViewModel() {

    val allQuestions: LiveData<List<TriviaQuestion>>
        get() = questionRepository.getQuestions()

    val isLoading : LiveData<Boolean>
        get() = _isLoading

    private val _isLoading = MutableLiveData<Boolean>()

    fun seedDatabase() {
        viewModelScope.launch {
            _isLoading.postValue(true)
            val seedData = dataProcessor.getSeedData()
            _isLoading.postValue(false)
            questionRepository.insertAll(seedData)
        }
    }

    fun resetHasClickedAnswer() {
        viewModelScope.launch {
            val listOfQuestions = questionRepository.getQuestionsOneShot()
            listOfQuestions.forEach {
                it.hasClickedAnswer = false
                questionRepository.updateQuestion(it)
            }
        }
    }

    fun onAnswerButtonClicked(triviaQuestion: TriviaQuestion?) {
        viewModelScope.launch {
            triviaQuestion?.let {
                it.hasClickedAnswer = true
                questionRepository.updateQuestion(it)
            }
        }
    }
}