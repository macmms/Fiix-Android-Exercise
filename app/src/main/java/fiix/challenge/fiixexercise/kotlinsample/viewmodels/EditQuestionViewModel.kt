package fiix.challenge.fiixexercise.kotlinsample.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fiix.challenge.fiixexercise.kotlinsample.data.TriviaQuestion
import fiix.challenge.fiixexercise.kotlinsample.data.TriviaRepository
import kotlinx.coroutines.launch

class EditQuestionViewModel(private val triviaRepository: TriviaRepository): ViewModel() {

    fun getQuestion(questionId: Int): LiveData<TriviaQuestion> = triviaRepository.getQuestion(questionId)

    fun saveQuestion(triviaQuestion: TriviaQuestion) {
        triviaQuestion.showAnswer = false
        viewModelScope.launch {
            triviaRepository.updateQuestion(triviaQuestion)
        }
    }

}