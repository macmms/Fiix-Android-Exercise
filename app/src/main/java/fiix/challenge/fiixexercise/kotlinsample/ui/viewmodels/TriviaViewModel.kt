package fiix.challenge.fiixexercise.kotlinsample.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import fiix.challenge.fiixexercise.kotlinsample.db.model.TriviaQuestion
import fiix.challenge.fiixexercise.kotlinsample.repository.TriviaRepository

class TriviaViewModel(private val triviaRepository: TriviaRepository) : ViewModel() {

    fun getTriviaQuestions(): LiveData<List<TriviaQuestion>> {
        return triviaRepository.getTrivia();
    }

    fun insertTriviaQuestions(triviaQuestion: List<TriviaQuestion>) {
        triviaRepository.insertTrivia(triviaQuestion)
    }
    fun updateAnswerRevealed(questionId: Int,answerRevealed : Boolean) {
        triviaRepository.updateTriviaItemAnswerRevaledFlag(questionId,answerRevealed)
    }
}