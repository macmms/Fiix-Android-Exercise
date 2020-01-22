package fiix.challenge.fiixexercise.kotlinsample.data

import fiix.challenge.fiixexercise.kotlinsample.TriviaQuestion

interface TriviaRepository {
    fun getQuestions(): List<TriviaQuestion>
    fun getAnswers(): List<String>

    fun setQuestions(questions: List<TriviaQuestion>)
    fun setAnswers(answers: List<String>)

    fun updateQuestion(question: TriviaQuestion)
}