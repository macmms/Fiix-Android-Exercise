package fiix.challenge.fiixexercise.kotlinsample.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import fiix.challenge.fiixexercise.db.ioThread
import fiix.challenge.fiixexercise.db.LocalDataSource
import fiix.challenge.fiixexercise.db.TriviaQuestionDao


@Database(
        entities = [TriviaQuestion::class],
        version = 1,
        exportSchema = false
)
abstract class TriviaDatabase : RoomDatabase() {

    abstract fun triviaQuestionDao(): TriviaQuestionDao

    companion object {
        // Use of a singleton to prevents multiple instances of opening the database opening.

        @Volatile
        private var INSTANCE: TriviaDatabase? = null

        fun getInstance(context: Context): TriviaDatabase =
                INSTANCE ?: synchronized(this) {
                    INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
                }

        private fun buildDatabase(context: Context) =
                Room.databaseBuilder(context.applicationContext,
                        TriviaDatabase::class.java, "trivia_database")
                        .addCallback(object : RoomDatabase.Callback() {
                            override fun onCreate(db: SupportSQLiteDatabase) {
                                super.onCreate(db)
                                ioThread {
                                    val data = LocalDataSource().getData().map { TriviaQuestion(question = it.question, answer = it.answer) }
                                    getInstance(context).triviaQuestionDao().insertQuestions(data)
                                }
                            }
                        })
                        .build()
    }

}