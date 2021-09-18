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
    /*
     A list of questions provided by the repo
     */
    private val questions = repo.questions
    /*
     A variable that indicates the question being edited
     */
    var questionDetailBeingEdited: TriviaQuestionUiModel? = null
    /*
     A variable that indicates if answers have been fetched
     */
    var answersFetched = false
    /*
     A variable corresponding to an updated trivia
     */
    var updatedTrivia: TriviaQuestionUiModel? = null
        set(value) {
            field = value
            // When this field is set, get the current list of trivia
            val existingTriviaQuestions = _mainFragmentUiModel.value?.triviaQuestions
            if (value != null) {
                // Create an updated list by changing only the entry that is provided, by using it's position
                val updatedList = existingTriviaQuestions?.toMutableList()?.apply {
                    set(value.position, value)
                }
                // Post the updated list to the main fragment
                _mainFragmentUiModel.postValue(MainFragmentUiModel(updatedList?.toList() ?: listOf()))
            }
        }

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