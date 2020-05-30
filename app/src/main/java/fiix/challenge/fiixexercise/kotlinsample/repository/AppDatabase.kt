package fiix.challenge.fiixexercise.kotlinsample.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import fiix.challenge.fiixexercise.kotlinsample.model.Trivia

@Database(entities = [Trivia::class], version = 1, exportSchema = false)
abstract class AppDatabase() : RoomDatabase() {

    abstract fun getTriviaDAO(): TriviaDAO

    companion object {
        private var instance: AppDatabase? = null

        fun getDatabase(context: Context) : AppDatabase {
            if(instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "fiixTriviaDb"
                ).build()
            }
            return instance!!
        }
    }
}