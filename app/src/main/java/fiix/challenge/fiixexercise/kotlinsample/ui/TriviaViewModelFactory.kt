package fiix.challenge.fiixexercise.kotlinsample.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import fiix.challenge.fiixexercise.kotlinsample.repository.TriviaRepository
import fiix.challenge.fiixexercise.kotlinsample.ui.viewmodels.TriviaViewModel

class TriviaViewModelFactory (private val triviaRepository: TriviaRepository) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TriviaViewModel(triviaRepository) as T
    }
}