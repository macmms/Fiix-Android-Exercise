package fiix.challenge.fiixexercise.kotlinsample.utilities

import android.content.Context
import fiix.challenge.fiixexercise.dp.DataProcessor
import fiix.challenge.fiixexercise.kotlinsample.datasource.LocalDataSource
import fiix.challenge.fiixexercise.kotlinsample.db.FiixDatabase
import fiix.challenge.fiixexercise.kotlinsample.repository.TriviaRepository
import fiix.challenge.fiixexercise.kotlinsample.ui.TriviaEditViewModelFactory
import fiix.challenge.fiixexercise.kotlinsample.ui.TriviaViewModelFactory

object Injector {
    fun provideTriviaViewModelFactory(context: Context): TriviaViewModelFactory {
        val triviaRepository = repositoryProvider(context)
        return TriviaViewModelFactory(triviaRepository!!)
    }

    fun provideEditTriviaViewModelFactory(context: Context): TriviaEditViewModelFactory {
        val triviaRepository = repositoryProvider(context)
        return TriviaEditViewModelFactory(triviaRepository!!)
    }

    fun repositoryProvider(context: Context): TriviaRepository {
        return TriviaRepository.getInstance(DataProcessor(LocalDataSource()),
                FiixDatabase.getFiixDataBase(context)!!.TriviaDao())!!
    }
}