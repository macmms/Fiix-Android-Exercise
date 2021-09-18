package fiix.challenge.fiixexercise.kotlinsample.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fiix.challenge.fiixexercise.kotlinsample.model.MainFragmentUiModel
import fiix.challenge.fiixexercise.kotlinsample.model.TriviaQuestionUiModel
import fiix.challenge.fiixexercise.kotlinsample.repository.MockRepo
import kotlinx.coroutines.launch

class SharedViewModel : ViewModel() {
    private val repo = MockRepo()
    val mainFragmentUiModel: LiveData<MainFragmentUiModel>
        get() = _mainFragmentUiModel
    private val _mainFragmentUiModel = MutableLiveData<MainFragmentUiModel>()
    private val questions = repo.questions
    var questionDetailBeingEdited: TriviaQuestionUiModel? = null
    var answersFetched = false

    init {
        // Post the questions without answers first
        _mainFragmentUiModel.postValue(
            MainFragmentUiModel(questions, "Answers are being fetched in the background")
        )

        viewModelScope.launch {
            // Get the questions with answers asynchronously from the repo
            val questionsWithAnswers = repo.getQuestionsWithAnswers()
            // Post the questions with answers again when answers are available, with a toast message
            _mainFragmentUiModel.postValue(
                MainFragmentUiModel(questionsWithAnswers, if (!answersFetched) "Answers are now available" else null)
            )
        }
        answersFetched = true
    }
}