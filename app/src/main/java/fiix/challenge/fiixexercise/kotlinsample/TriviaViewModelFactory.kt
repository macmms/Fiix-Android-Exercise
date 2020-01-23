package fiix.challenge.fiixexercise.kotlinsample

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import fiix.challenge.fiixexercise.kotlinsample.data.TriviaRepository

class TriviaViewModelFactory(private val repository: TriviaRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TriviaViewModel(repository) as T
    }
}