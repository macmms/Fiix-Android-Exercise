package fiix.challenge.fiixexercise.kotlinsample.repository

import fiix.challenge.fiixexercise.dp.DataSource
import fiix.challenge.fiixexercise.kotlinsample.models.TriviaQuestion

class LocalDataSource: DataSource {

    internal val triviaQuestionsList: MutableList<TriviaQuestion> by lazy {
        MockRepo().triviaQuestions
    }

    // region DataSource Methods
    override suspend fun getData(): List<TriviaQuestion> {
        return triviaQuestionsList
    }

    /**
     * Update specific TriviaQuestion base on provided id, new question and new answer
     * @return true - if update is successful
     *         false - if item not found or new question and new answer are identical to original
     *                 question and answer
     */
    override suspend fun updateQOrA(id: Int, newQuestion: String?, newAnswer: String?): Boolean {
        return triviaQuestionsList.find { mItem ->
            mItem.id == id
        }?.let { mItem ->
            when {
                mItem.question != newQuestion && mItem.answer != newAnswer -> {
                    mItem.question = newQuestion
                    mItem.answer = newAnswer
                    mItem.isShownAnswer = false
                    return@let true
                }
                mItem.question != newQuestion -> {
                    mItem.question = newQuestion
                    mItem.isShownAnswer = false
                    return@let true
                }
                mItem.answer != newAnswer -> {
                    mItem.answer = newAnswer
                    mItem.isShownAnswer = false
                    return@let true
                }
                else -> return@let false
            }
        } ?: false
    }

    override suspend fun updateAnswerVisibility(id: Int, showAnswer: Boolean): List<TriviaQuestion> {
        triviaQuestionsList.find { mItem ->
            mItem.id == id
        }?.isShownAnswer = showAnswer
        return triviaQuestionsList
    }

    override suspend fun getSpecificData(id: Int): TriviaQuestion? {
        return triviaQuestionsList.find { mItem ->
            mItem.id == id
        }
    }
    // endregion
}