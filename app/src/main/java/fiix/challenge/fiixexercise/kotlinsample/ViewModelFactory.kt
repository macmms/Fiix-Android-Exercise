package fiix.challenge.fiixexercise.kotlinsample

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import fiix.challenge.fiixexercise.dp.DataProcessor
import fiix.challenge.fiixexercise.kotlinsample.data.LocalDataSource
import fiix.challenge.fiixexercise.kotlinsample.data.TriviaRepository
import fiix.challenge.fiixexercise.kotlinsample.data.db.TriviaDatabase
import fiix.challenge.fiixexercise.kotlinsample.ui.TriviaScreensViewModel

/**
 * Responsible for constructing the view models
 */
class ViewModelFactory(application: Application) : ViewModelProvider.Factory {

    private val triviaRepository = TriviaRepository(TriviaDatabase.getInstance(application), DataProcessor(LocalDataSource()))


    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TriviaScreensViewModel::class.java)) {
            return TriviaScreensViewModel(triviaRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class :${modelClass.canonicalName}")
    }

}