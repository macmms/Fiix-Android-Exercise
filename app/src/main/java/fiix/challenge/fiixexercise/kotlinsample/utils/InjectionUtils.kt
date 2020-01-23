package fiix.challenge.fiixexercise.kotlinsample.utils

import android.content.Context
import fiix.challenge.fiixexercise.dp.DataProcessor
import fiix.challenge.fiixexercise.kotlinsample.data.LocalDataSource
import fiix.challenge.fiixexercise.kotlinsample.data.MockRepo
import fiix.challenge.fiixexercise.kotlinsample.viewmodels.TriviaViewModelFactory
import fiix.challenge.fiixexercise.kotlinsample.data.TriviaRepository
import fiix.challenge.fiixexercise.kotlinsample.data.TriviaRepositoryImpl
import fiix.challenge.fiixexercise.kotlinsample.data.local.AppDatabase

object InjectionUtils {
    fun provideDataBase(context: Context): AppDatabase {
        return AppDatabase.getInstance(context)
    }

    fun provideTriviaViewModelFactory(context: Context): TriviaViewModelFactory {
        return TriviaViewModelFactory(
            provideTriviaRepository(
                context
            )
        )
    }

    fun provideTriviaRepository(context: Context): TriviaRepository {
        val dao = provideDataBase(context).triviaQuestionDao()
        val repo = MockRepo()
        val processor = provideProcessor()
        return TriviaRepositoryImpl(dao, repo, processor)
    }

    fun provideProcessor(): DataProcessor {
        val dataSource = LocalDataSource()
        return DataProcessor(dataSource)
    }
}