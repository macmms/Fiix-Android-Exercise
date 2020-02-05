package fiix.challenge.fiixexercise.kotlinsample.adapter

import fiix.challenge.fiixexercise.kotlinsample.model.TriviaQuestion

interface QuestionsListInterface {

    fun onAnswerClick(question: TriviaQuestion)

    fun onRowClick(question: TriviaQuestion)

}