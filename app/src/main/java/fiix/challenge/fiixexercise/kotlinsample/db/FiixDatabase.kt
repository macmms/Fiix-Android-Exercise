package fiix.challenge.fiixexercise.kotlinsample.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import fiix.challenge.fiixexercise.kotlinsample.db.model.TriviaQuestion

@Database(
        entities = [TriviaQuestion::class],
        version = 1
)
abstract class FiixDatabase : RoomDatabase() {

    companion object {
        const val FIIX_DATABASE_NAME = "fiix.db"
        var INSTANCE: FiixDatabase? = null

        fun getFiixDataBase(context: Context): FiixDatabase? {
            if (INSTANCE == null) {
                synchronized(FiixDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext, FiixDatabase::class.java,
                            FIIX_DATABASE_NAME).build()
                }
            }
            return INSTANCE
        }

        fun destroyDataBase() {
            INSTANCE = null
        }
    }

    abstract fun TriviaDao(): TriviaDAO

}