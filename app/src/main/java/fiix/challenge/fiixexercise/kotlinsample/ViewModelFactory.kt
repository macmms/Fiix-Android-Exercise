package fiix.challenge.fiixexercise.kotlinsample

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import fiix.challenge.fiixexercise.kotlinsample.data.TriviaRepository
import fiix.challenge.fiixexercise.kotlinsample.ui.TriviaScreensViewModel

/**
 * Responsible for providing the view models
 */
class ViewModelFactory(
        private val triviaRepository: TriviaRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TriviaScreensViewModel::class.java)) {
            return TriviaScreensViewModel(triviaRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class :${modelClass.canonicalName}")
    }

}