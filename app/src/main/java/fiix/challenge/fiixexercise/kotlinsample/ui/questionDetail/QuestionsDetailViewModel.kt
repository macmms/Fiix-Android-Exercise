package fiix.challenge.fiixexercise.kotlinsample.ui.questionDetail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fiix.challenge.fiixexercise.kotlinsample.data.TriviaQuestion
import fiix.challenge.fiixexercise.kotlinsample.repository.QuestionRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class QuestionsDetailViewModel @ViewModelInject constructor(
        private val questionRepository: QuestionRepository,
) : ViewModel() {

    lateinit var triviaQuestion: LiveData<TriviaQuestion>

    fun getQuestion(questionId: Long) = viewModelScope.launch {
        triviaQuestion = questionRepository.getQuestion(questionId)
    }

    fun saveQuestion(id: Long, question: String, answer: String) {
        GlobalScope.launch {
            questionRepository.updateQuestion(TriviaQuestion(id, question, answer))
        }
    }
}