package fiix.challenge.fiixexercise.kotlinsample.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import fiix.challenge.fiixexercise.kotlinsample.repository.TriviaRepository
import java.lang.IllegalArgumentException

class TriviaViewModelFactory(val repository: TriviaRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(TriviaViewModel::class.java)){
           return TriviaViewModel(repository) as T
        }
        throw IllegalArgumentException("Unkown ViewModel Class")
    }

}