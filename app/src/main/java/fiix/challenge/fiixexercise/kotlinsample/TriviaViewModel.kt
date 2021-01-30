package fiix.challenge.fiixexercise.kotlinsample

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import fiix.challenge.fiixexercise.dp.DataProcessor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TriviaViewModel(application: Application) : AndroidViewModel(application) {

    val triviaQuestionsLiveData = MutableLiveData<ArrayList<TriviaQuestion>>()
    val triviaAnswersLiveData = MutableLiveData<List<String>>()


    fun retrieveQuestions() {
        triviaQuestionsLiveData.value = MockRepo().triviaQuestions
    }

    fun retrieveAnswers() {
        viewModelScope.launch(Dispatchers.IO) {
            triviaAnswersLiveData.postValue(DataProcessor(LocalDataSource()).getAnswers())
        }
    }
}