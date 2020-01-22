package fiix.challenge.fiixexercise.kotlinsample.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import fiix.challenge.fiixexercise.kotlinsample.TriviaQuestion

@Database(entities = [TriviaQuestion::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun triviaQuestionDao(): TriviaQuestionDao
}