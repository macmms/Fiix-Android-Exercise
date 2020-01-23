package fiix.challenge.fiixexercise.kotlinsample

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fiix.challenge.fiixexercise.kotlinsample.data.TriviaRepository
import kotlinx.coroutines.launch

class TriviaViewModel(private val triviaRepository: TriviaRepository) : ViewModel() {

    fun fetchQuestions() {
        viewModelScope.launch {
            triviaRepository.fetchQuestions()
        }
    }

    fun getQuestions(): LiveData<List<TriviaQuestion>> {
        return triviaRepository.getQuestions()
    }

    fun getAnswers(questions: List<TriviaQuestion>) {
        viewModelScope.launch {
            triviaRepository.getAnswers(questions)
        }
    }

    fun getAnswer(questionId: Int) {
        triviaRepository.getQuestion(questionId).value?.let {
            it.showAnswer = true
            viewModelScope.launch {
                triviaRepository.updateQuestion(it)
            }

        }
    }
}