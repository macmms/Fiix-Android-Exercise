package fiix.challenge.fiixexercise.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import fiix.challenge.fiixexercise.dp.BaseDataProcessor
import fiix.challenge.fiixexercise.model.TriviaQuestion

class MainViewModel(private val dataProcessor: BaseDataProcessor): ViewModel() {

    private val _questions = MutableLiveData<List<TriviaQuestion>>()
    val questions:LiveData<List<TriviaQuestion>> = _questions

    private val _selectedQuestion = MutableLiveData<TriviaQuestion>()
    val selectedQuestion:LiveData<TriviaQuestion> = _selectedQuestion

    private var questionAnswerList: List<TriviaQuestion> = ArrayList()

    /**
    Getting Answers From Data Processor
     */
    private fun getAnswers() = dataProcessor.getAnswers()

    /**
    Getting Questions from Data Processor
     */
    private fun getQuestionsList() = dataProcessor.getQuestions()

    /**
     Syncing Question and answers in a Single LOCAL List
     */
    fun syncQuestionAnswers(){
        if (questionAnswerList.isNullOrEmpty()){
            val questionList = getQuestionsList()
            val answerList =  getAnswers()
            questionAnswerList = questionList
            for (item in questionAnswerList.indices){
                questionAnswerList[item].answer = answerList[item]
            }
        }else{
            _questions.value = questionAnswerList
        }
        _questions.value = questionAnswerList
    }

    /**
    Getting Single Item from the List of questions
     */
    fun getSelectedQuestionAnswer(position: Int){
        _selectedQuestion.value = _questions.value?.get(position)
    }

    /**
    Updating the Questions and Answers
     */
    fun updateQuestionAnswer(question: String, answer: String, position: Int) {
        questionAnswerList[position].answer  = answer
        questionAnswerList[position].question = question
        _questions.value = questionAnswerList

    }
}
