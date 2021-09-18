package fiix.challenge.fiixexercise.kotlinsample.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import fiix.challenge.fiixexercise.kotlinsample.model.TriviaQuestion
import fiix.challenge.fiixexercise.kotlinsample.repository.MockRepo

class SharedViewModel : ViewModel() {
    private val repo = MockRepo()
    val triviaQuestions: LiveData<List<TriviaQuestion>>
        get() = _triviaQuestions
    private val _triviaQuestions = MutableLiveData<List<TriviaQuestion>>()

    init {
        repo.triviaQuestions.observeForever {
            _triviaQuestions.postValue(it)
        }
    }
}