package fiix.challenge.fiixexercise.kotlinsample.viewmodels

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.support.v4.app.Fragment
import fiix.challenge.fiixexercise.dp.DataProcessor
import fiix.challenge.fiixexercise.kotlinsample.data.LocalDataSource
import fiix.challenge.fiixexercise.kotlinsample.data.MockRepo
import fiix.challenge.fiixexercise.kotlinsample.model.TriviaQuestion
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlin.reflect.KClass

class TriviaViewModel : ViewModel() {

    val currentFragment = MutableLiveData<KClass<out Fragment>>()
    val questions = MutableLiveData<List<TriviaQuestion>>()
    val isLoadingData = MutableLiveData<Boolean>()
    val editQuestion = MutableLiveData<TriviaQuestion>()

    fun getQuestions() {
        CoroutineScope(IO).launch {
            isLoadingData.postValue(true)
            val questionsWithAnswers =  async {
               getAnswers(MockRepo().triviaQuestions)
            }.await()
            questions.postValue(questionsWithAnswers)
            isLoadingData.postValue(false)
        }
    }

     private fun getAnswers(questions: List<TriviaQuestion>): List<TriviaQuestion> {
        val dp = DataProcessor(LocalDataSource())
        val answers = dp.getAnswers()
        check(questions.size == answers.size) { return listOf() } // array sizes are different -> return empty list
        answers.forEachIndexed { position, answerString ->
            questions[position].answer = answerString
        }
        return questions
    }

    fun editQuestion(mQuestion: TriviaQuestion) {
        questions.value?.find { it.id == mQuestion.id }.apply {
            this?.answer = mQuestion.answer
            this?.question = mQuestion.question
            this?.showAnswer = false
        }
    }
}