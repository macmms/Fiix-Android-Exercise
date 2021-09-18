package fiix.challenge.fiixexercise.kotlinsample.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fiix.challenge.fiixexercise.kotlinsample.model.TriviaQuestionUiModel
import fiix.challenge.fiixexercise.kotlinsample.repository.MockRepo
import kotlinx.coroutines.launch

class SharedViewModel : ViewModel() {
    private val repo = MockRepo()
    val triviaQuestions: LiveData<List<TriviaQuestionUiModel>>
        get() = _triviaQuestions
    private val _triviaQuestions = MutableLiveData<List<TriviaQuestionUiModel>>()
    private val questions = repo.questions

    init {
        // Post the questions without answers first
        _triviaQuestions.postValue(questions)

        viewModelScope.launch {
            // Get the questions with answers asynchronously from the repo
            val questionsWithAnswers = repo.getQuestionsWithAnswers()
            // Post the questions with answers again when answers are available
            _triviaQuestions.postValue(questionsWithAnswers)
        }
    }
}