package fiix.challenge.fiixexercise.kotlinsample.framework.presentation.viewmodule

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fiix.challenge.fiixexercise.dp.DataProcessor
import fiix.challenge.fiixexercise.kotlinsample.buisness.domain.TriviaQuestion
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val dataProcessor: DataProcessor) : ViewModel() {

    private var _triviaQuestionList = MutableLiveData<List<TriviaQuestion>>()
    var triviaQuestionList: LiveData<List<TriviaQuestion>> = _triviaQuestionList
    private var mQuestionAnswerList: List<TriviaQuestion> = ArrayList(0)

    suspend fun bindQuestionAndAnswer() = withContext(context = Dispatchers.IO) {
        if (mQuestionAnswerList.isNullOrEmpty()){
                    val questionList = dataProcessor.getQuestion()
                    val answerList =   dataProcessor.getAnswers()
                    mQuestionAnswerList = questionList
                    for (item in mQuestionAnswerList.indices){
                        mQuestionAnswerList[item].answer = answerList[item]
                    }
        }
        withContext(Dispatchers.Main){
            _triviaQuestionList.value = mQuestionAnswerList
        }
    }

    fun launchDataLoad() {
        viewModelScope.launch {
            bindQuestionAndAnswer()
        }
    }

    private var _position = MutableLiveData<Int>()
    var position: LiveData<Int>

    fun setPosition(position: Int) {
        _position.value = position
    }

    fun updateTrivialQuestion(position: Int, answer: String, question: String) {
        mQuestionAnswerList[position].answer = answer
        mQuestionAnswerList[position].question = question
        _triviaQuestionList.value = mQuestionAnswerList
    }

    init {
        position = _position
        triviaQuestionList = _triviaQuestionList
    }


}