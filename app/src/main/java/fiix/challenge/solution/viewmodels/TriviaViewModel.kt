package fiix.challenge.solution.viewmodels

import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fiix.challenge.solution.core.MockRepo
import fiix.challenge.solution.models.TriviaQuestion
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.reflect.KClass

class TriviaViewModel : ViewModel(){
    val triviaQuestions = MutableLiveData<List<TriviaQuestion>>()
    val currentFragment = MutableLiveData<KClass<out Fragment>>()
    val currentQuestion = MutableLiveData<TriviaQuestion>()


    fun getTriviaQuestions(){
        viewModelScope.launch(Dispatchers.IO) {
            triviaQuestions.postValue(MockRepo().triviaQuestions)
        }
    }
}