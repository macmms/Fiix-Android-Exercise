package fiix.challenge.fiixexercise.ui.trivialist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import fiix.challenge.fiixexercise.TriviaQuestionRepository

class TriviaListViewModelFactory(private val repository: TriviaQuestionRepository) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TriviaListViewModel(repository) as T
    }
}