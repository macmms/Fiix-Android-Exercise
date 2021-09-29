package fiix.challenge.fiixexercise.kotlinsample.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import fiix.challenge.fiixexercise.kotlinsample.repository.QuestionsRepository

class ViewModelProviderFactory(val questionRepository: QuestionsRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(questionRepository) as T
    }
}