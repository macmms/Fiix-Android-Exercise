package fiix.challenge.fiixexercise.kotlinsample.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import fiix.challenge.fiixexercise.dp.DataProcessor

class MainViewModelFactory(val dataProcessor: DataProcessor) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainActivityViewModel::class.java)) {
            return MainActivityViewModel(dataProcessor) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}