package fiix.challenge.fiixexercise.kotlinsample.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fiix.challenge.fiixexercise.kotlinsample.model.TriviaQuestion
import fiix.challenge.fiixexercise.kotlinsample.repository.MockRepo
import kotlinx.coroutines.launch

class SharedViewModel : ViewModel() {
    private val repo = MockRepo()
    val triviaQuestions: LiveData<List<TriviaQuestion>>
        get() = _triviaQuestions
    private val _triviaQuestions = MutableLiveData<List<TriviaQuestion>>()
    private val questions = repo.questions

    init {
        // Post the questions without answers first
        _triviaQuestions.postValue(questions)

        viewModelScope.launch {
            // Get the answers asynchronously from the repo
            val answers = repo.getAnswers()

            // Remake the list of questions, this time with the answers for each question
            val questionsWithAnswers = questions.mapIndexed { index, triviaQuestion ->
                TriviaQuestion(triviaQuestion.question, answers[index])
            }

            // Post the questions with answers again when answers are available
            _triviaQuestions.postValue(questionsWithAnswers)
        }
    }
}