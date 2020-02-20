package fiix.challenge.fiixexercise.kotlinsample.ui.fragments.editquestion

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class EditQuestionViewModelFactory:ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EditQuestionViewModel::class.java)) {
            return EditQuestionViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}