package fiix.challenge.fiixexercise.kotlinsample

import androidx.annotation.VisibleForTesting
import androidx.annotation.VisibleForTesting.PRIVATE
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import fiix.challenge.fiixexercise.dp.DataProcessor
import fiix.challenge.fiixexercise.dp.Processor

class TriviaViewModel : ViewModel() {
    @VisibleForTesting(otherwise = PRIVATE)
    var dp: Processor = DataProcessor(LocalDataSource())
    private val repo = MockRepo()
    private var answers: LiveData<List<String>> = MutableLiveData()

    private val questions = MutableLiveData<List<TriviaQuestion>>().apply {
        value = repo.triviaQuestions
    }

    private val loading = MutableLiveData<Boolean>()

    fun getQuestions(): LiveData<List<TriviaQuestion>> = questions

    fun getAnswers(): LiveData<List<String>> = answers

    fun getLoading(): LiveData<Boolean> = loading

    fun fetchAnswer(questionIndex: Int) {

    }
}