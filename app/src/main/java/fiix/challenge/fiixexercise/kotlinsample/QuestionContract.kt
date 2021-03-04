package fiix.challenge.fiixexercise.kotlinsample

import android.provider.BaseColumns

class QuestionContract private constructor() {
    object QuestionTable : BaseColumns {
        const val TABLE_NAME = "trivia_question"
        const val COLUMN_QUESTION = "question"
        const val COLUMN_ANSWER_NR = "answer"
        const val COLUMN_HIDE_ANSWER = "hide_answer"
        const val _ID = "_id"
    }
}