package fiix.challenge.fiixexercise.kotlinsample.ui.viewmodels

import androidx.lifecycle.ViewModel
import fiix.challenge.fiixexercise.kotlinsample.db.model.TriviaQuestion
import fiix.challenge.fiixexercise.kotlinsample.repository.TriviaRepository

class TriviaEditViewModel(private val triviaRepository: TriviaRepository) : ViewModel() {
    fun getTriviaQuestion(questionId: Int): TriviaQuestion {
        return triviaRepository.getTriviaQuestion(questionId);
    }
    fun updateTriviaQuestions(question: TriviaQuestion) {
        triviaRepository.updateTrivia(question)
    }
}