package fiix.challenge.fiixexercise.kotlinsample.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import fiix.challenge.fiixexercise.kotlinsample.TriviaQuestion
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

@Database(entities = [TriviaQuestion::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun triviaQuestionDao(): TriviaQuestionDao

    companion object {
        const val DATABASE_NAME = "trivia-question-database"
        @Volatile private var  instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: runBlocking {
                    buildDatabase(context).also { instance = it }
                }
            }
        }

        private suspend fun buildDatabase(context: Context): AppDatabase {
            return withContext(Dispatchers.IO) {
                Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                    .build()
            }
        }
    }
}