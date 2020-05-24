package fiix.challenge.fiixexercise.kotlinsample.data.db

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import fiix.challenge.fiixexercise.kotlinsample.data.db.trivia.TriviaDao

/**
 * This is the Database class for the application
 */
abstract class TriviaDatabase : RoomDatabase() {

    companion object {
        private val lock = Any()
        private const val DB_NAME = "Trivia.db"
        private lateinit var instance: TriviaDatabase

        /**
         * Invoke this function to acquire an instance of the database
         **/
        fun getInstance(application: Application): TriviaDatabase {
            synchronized(lock) {
                if (!::instance.isInitialized) {
                    instance = createInstance(application)
                }
                return instance
            }
        }

        /**
         * Creates an instance of the database and populates it.
         **/
        private fun createInstance(application: Application): TriviaDatabase {
            return Room.databaseBuilder(application, TriviaDatabase::class.java, DB_NAME).build()
        }

    }

    abstract fun triviaDao(): TriviaDao
}