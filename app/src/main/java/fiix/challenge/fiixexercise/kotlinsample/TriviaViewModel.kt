package fiix.challenge.fiixexercise.kotlinsample

import androidx.annotation.VisibleForTesting
import androidx.annotation.VisibleForTesting.PRIVATE
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import fiix.challenge.fiixexercise.dp.DataProcessor
import fiix.challenge.fiixexercise.dp.Processor
import fiix.challenge.fiixexercise.kotlinsample.data.TriviaRepository

class TriviaViewModel(val triviaRepository: TriviaRepository) : ViewModel() {

    fun getQuestions(): LiveData<List<TriviaQuestion>> {
        val questions = triviaRepository.getQuestions()
        triviaRepository.getAnswers()
        return questions
    }

    fun getAnswer(questionId: Int) {
        triviaRepository.getQuestion(questionId).value?.let {
            it.showAnswer = true
            triviaRepository.updateQuestion(it)
        }
    }
}