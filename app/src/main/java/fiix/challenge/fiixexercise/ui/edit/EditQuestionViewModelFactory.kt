package fiix.challenge.fiixexercise.ui.edit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import fiix.challenge.fiixexercise.TriviaQuestionRepository

class EditQuestionViewModelFactory(private val repository: TriviaQuestionRepository) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return EditQuestionViewModel(repository) as T
    }
}