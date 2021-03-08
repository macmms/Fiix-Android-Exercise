package fiix.challenge.fiixexercise.kotlinsample

import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteDatabase
import android.content.ContentValues
import android.content.Context
import java.lang.Boolean
import java.util.ArrayList
import kotlin.jvm.Synchronized

class QuestionDbHelper(context: Context?) {
    private val _openHelper: SQLiteOpenHelper

    internal inner class QuestionSQLiteOpenHelper(context: Context?) :
            SQLiteOpenHelper(context, "TriviaQuestions.db", null, 1) {
        override fun onCreate(db: SQLiteDatabase) {
            db.execSQL("create table trivia_question (_id INTEGER PRIMARY KEY AUTOINCREMENT, question TEXT NOT NULL, answer TEXT NOT NULL, hide_answer TEXT NOT NULL)")
        }

        override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {}
    }

    fun insertQuestion(question: TriviaQuestion): Long {
        val db = _openHelper.writableDatabase ?: return 0
        val cv = ContentValues()
        cv.put(QuestionContract.QuestionTable._ID, question.id)
        cv.put(QuestionContract.QuestionTable.COLUMN_QUESTION, question.question)
        cv.put(QuestionContract.QuestionTable.COLUMN_ANSWER_NR, question.answer)
        cv.put(QuestionContract.QuestionTable.COLUMN_HIDE_ANSWER, question.isShowing.toString())
        val id = db!!.insert(QuestionContract.QuestionTable.TABLE_NAME, null, cv)
        return id
    }


    val allQuestion: ArrayList<TriviaQuestion>
        get() {
            val questionList = ArrayList<TriviaQuestion>()
            val db = _openHelper.readableDatabase
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

    fun delete(id: Long) {
        val db = _openHelper.writableDatabase ?: return
        db.delete(QuestionContract.QuestionTable.TABLE_NAME, "_id=?", arrayOf(id.toString()))
    }


    fun update(question: TriviaQuestion) {
        val db = _openHelper.writableDatabase ?: return
        val cv = ContentValues()
        cv.put(QuestionContract.QuestionTable._ID, question.id)
        cv.put(QuestionContract.QuestionTable.COLUMN_QUESTION, question.question)
        cv.put(QuestionContract.QuestionTable.COLUMN_ANSWER_NR, question.answer)
        cv.put(QuestionContract.QuestionTable.COLUMN_HIDE_ANSWER, question.isShowing.toString())
        val whereClause = "_id=?"
        val whereArgs = arrayOf<String>(question.id.toString())
        db.update(
                QuestionContract.QuestionTable.TABLE_NAME,
                cv,
                whereClause,
                whereArgs
        )
    }

    init {
        _openHelper = QuestionSQLiteOpenHelper(context)
    }
}
