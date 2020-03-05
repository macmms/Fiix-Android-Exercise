package fiix.challenge.fiixexercise.kotlinsample.ui.interfaces

import fiix.challenge.fiixexercise.kotlinsample.db.model.TriviaQuestion

interface QuestionActionListener {
    fun onAnswerClicked(question: TriviaQuestion)
    fun onQuestionEditClicked(questionId: Int)
}