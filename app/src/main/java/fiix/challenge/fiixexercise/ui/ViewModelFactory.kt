package fiix.challenge.fiixexercise.ui

import android.content.Context
import fiix.challenge.fiixexercise.TriviaQuestionRepository
import fiix.challenge.fiixexercise.kotlinsample.db.TriviaDatabase
import fiix.challenge.fiixexercise.ui.edit.EditQuestionViewModelFactory
import fiix.challenge.fiixexercise.ui.trivialist.TriviaListViewModelFactory

object ViewModelFactory {

    fun provideTriviaListViewModelFactory(context: Context): TriviaListViewModelFactory {
        val repository = TriviaQuestionRepository(TriviaDatabase.getInstance(context.applicationContext))

        return TriviaListViewModelFactory(repository)
    }

    fun provideEditQuestionViewModelFactory(context: Context): EditQuestionViewModelFactory {
        val repository = TriviaQuestionRepository(TriviaDatabase.getInstance(context.applicationContext))

        return EditQuestionViewModelFactory(repository)
    }
}