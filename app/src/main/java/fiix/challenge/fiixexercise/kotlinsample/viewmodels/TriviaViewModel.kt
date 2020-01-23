package fiix.challenge.fiixexercise.kotlinsample.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fiix.challenge.fiixexercise.kotlinsample.data.TriviaQuestion
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

    fun getAnswer(question: TriviaQuestion) {
        question.showAnswer = true
        viewModelScope.launch {
            triviaRepository.updateQuestion(question)
        }
    }
}