package fiix.challenge.fiixexercise.kotlinsample.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fiix.challenge.fiixexercise.dp.DataProcessor
import fiix.challenge.fiixexercise.kotlinsample.LocalDataSource
import fiix.challenge.fiixexercise.kotlinsample.model.TriviaQuestion
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TriviaViewModel : ViewModel() {
    private val triviaRepo = DataProcessor(LocalDataSource())

    private val _triviaQuestions = MutableStateFlow<List<TriviaQuestion>>(emptyList())
    val triviaQuestions: StateFlow<List<TriviaQuestion>> = _triviaQuestions

    init {
        getTriviaList()
    }

    @Suppress("MemberVisibilityCanBePrivate")
    //I assume this will want to be publicly exposed at some time in the future (i.e. for swipe refresh)
    // so suppressing the visibility warning for now.
    fun getTriviaList() {
        viewModelScope.launch(Dispatchers.IO) {
            _triviaQuestions.value = triviaRepo.getData()
        }
    }

    fun getTriviaQuestionById(triviaId: String?): TriviaQuestion? {
        return triviaId?.let { idToFind -> _triviaQuestions.value.find { it.id == idToFind } }
    }

    fun saveQuestion(id: String, question: String, answer: String) {
        //System requirements didn't specify that data had to persist between app close
        //TODO: Check if that would be necessary (if so make a room db and persist there)
        _triviaQuestions.value = _triviaQuestions.value.map {
            if (it.id == id) TriviaQuestion(question, answer, id) else it
        }
    }
}