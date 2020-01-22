package fiix.challenge.fiixexercise.kotlinsample

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import fiix.challenge.fiixexercise.dp.DataProcessor

class TriviaViewModel : ViewModel() {
    val dp = DataProcessor(LocalDataSource())
    val repo = MockRepo()
    var answers: List<String> = listOf()

    private val questions = MutableLiveData<List<TriviaQuestion>>().apply {
        postValue(repo.triviaQuestions)
    }

    private val loading = MutableLiveData<Boolean>()

    fun getQuestions(): LiveData<List<TriviaQuestion>> = questions

    fun getLoading(): LiveData<Boolean> = loading

    fun fetchAnswer(questionIndex: Int) {

    }
}