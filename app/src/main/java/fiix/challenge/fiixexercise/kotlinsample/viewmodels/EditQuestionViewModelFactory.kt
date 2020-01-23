package fiix.challenge.fiixexercise.kotlinsample.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import fiix.challenge.fiixexercise.kotlinsample.data.TriviaRepository

class EditQuestionViewModelFactory(private val repository: TriviaRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return EditQuestionViewModel(repository) as T
    }
}