package fiix.challenge.fiixexercise.kotlinsample

import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteDatabase
import android.content.ContentValues
import android.content.Context
import java.lang.Boolean
import java.util.ArrayList
import kotlin.jvm.Synchronized

class QuestionDbHelper(context: Context?) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    private var db: SQLiteDatabase? = null
    override fun onCreate(db: SQLiteDatabase) {
        this.db = db
        val SQL_CREATE_QUESTION_TABLE = "CREATE TABLE " +
                QuestionContract.QuestionTable.TABLE_NAME + " ( " +
                QuestionContract.QuestionTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionContract.QuestionTable.COLUMN_QUESTION + " TEXT, " +
                QuestionContract.QuestionTable.COLUMN_ANSWER_NR + " TEXT, " +
                QuestionContract.QuestionTable.COLUMN_HIDE_ANSWER + " TEXT" + ")"
        db.execSQL(SQL_CREATE_QUESTION_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + QuestionContract.QuestionTable.TABLE_NAME)
        onCreate(db)
    }

    override fun onConfigure(db: SQLiteDatabase) {
        super.onConfigure(db)
        db.setForeignKeyConstraintsEnabled(true)
    }

    fun insertQuestion(question: TriviaQuestion) {
        val cv = ContentValues()
        cv.put(QuestionContract.QuestionTable._ID, question.id)
        cv.put(QuestionContract.QuestionTable.COLUMN_QUESTION, question.question)
        cv.put(QuestionContract.QuestionTable.COLUMN_ANSWER_NR, question.answer)
        cv.put(QuestionContract.QuestionTable.COLUMN_HIDE_ANSWER, question.isShowing.toString())
        db!!.insert(QuestionContract.QuestionTable.TABLE_NAME, null, cv)
    }

    val allQuestion: ArrayList<TriviaQuestion>
        get() {
            val questionList = ArrayList<TriviaQuestion>()
            db = readableDatabase
            val cursor =
                db?.rawQuery("SELECT * FROM " + QuestionContract.QuestionTable.TABLE_NAME, null)
            if (cursor?.moveToFirst()!!) {
                do {
                    val question = TriviaQuestion(
                        cursor.getString(cursor.getColumnIndex(QuestionContract.QuestionTable.COLUMN_QUESTION)),
                        cursor.getString(cursor.getColumnIndex(QuestionContract.QuestionTable.COLUMN_ANSWER_NR)),
                        Boolean.parseBoolean(
                            cursor.getString(cursor.getColumnIndex(QuestionContract.QuestionTable.COLUMN_HIDE_ANSWER))
                                .toLowerCase()
                        ),
                        cursor.getInt(cursor.getColumnIndex(QuestionContract.QuestionTable._ID))
                    )
                    questionList.add(question)
                } while (cursor.moveToNext())
            }
            cursor.close()
            return questionList
        }

    fun update(question:TriviaQuestion): kotlin.Boolean {
        val db = this.readableDatabase
        db.execSQL("UPDATE " + QuestionContract.QuestionTable.TABLE_NAME + " SET _id = " + "'" + question.id + "' " + "WHERE question = " + "'" + question.question+","
                +"answer = " + "'" + question.answer + "'" + "'")
        return true
    }
    companion object {
        private const val DATABASE_NAME = "TriviaQuestions.db"
        const val DATABASE_VERSION = 1
        private var instance: QuestionDbHelper? = null
        @Synchronized
        fun getInstance(context: Context): QuestionDbHelper? {
            if (instance == null) {
                instance = QuestionDbHelper(context.applicationContext)
            }
            return instance
        }
    }
}