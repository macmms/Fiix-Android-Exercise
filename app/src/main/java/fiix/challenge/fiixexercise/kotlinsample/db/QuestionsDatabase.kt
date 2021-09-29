package fiix.challenge.fiixexercise.kotlinsample.db

import android.content.Context
import androidx.room.*
import fiix.challenge.fiixexercise.kotlinsample.TriviaQuestion

@Database(entities = [TriviaQuestion::class], version = 1)

abstract class QuestionsDatabase : RoomDatabase(){
    abstract fun getQuestionDao(): QuestionDao

    companion object {
        @Volatile
        private var instance: QuestionsDatabase? = null
        private val LOCK = Any()


        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also { instance = it }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                QuestionsDatabase::class.java,
                "questions.db"
            ).build()
    }
}