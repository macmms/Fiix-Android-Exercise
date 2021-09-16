package fiix.challenge.fiixexercise.kotlinsample.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fiix.challenge.fiixexercise.dp.DataProcessor
import fiix.challenge.fiixexercise.kotlinsample.data.LocalDataSource
import fiix.challenge.fiixexercise.kotlinsample.data.MockRepo
import fiix.challenge.fiixexercise.kotlinsample.data.TriviaQuestion
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    private var _triviaItems =  MutableLiveData<ArrayList<TriviaQuestion>>()
    val triviaItems: LiveData<ArrayList<TriviaQuestion>>
        get() = _triviaItems

    private var _selectedQuestionPosition = MutableLiveData<Int>()
    val selectedQuestionPosition: LiveData<Int>
    get() = _selectedQuestionPosition

    fun editQuestion(position: Int, editedQuestion: String){
        _triviaItems.value?.get(position)?.question = editedQuestion
    }

    fun editAnswer(position: Int, editedAnswer: String){
        _triviaItems.value?.get(position)?.answer = editedAnswer
    }

    fun getSelectedTrivia(position: Int): TriviaQuestion?{
        return triviaItems.value?.get(position)
    }

    fun setSelectedQuestion(position: Int){
        _selectedQuestionPosition.value = position
    }

    init {
        _triviaItems.value = ArrayList()

        //fetching data and initializing the list
        viewModelScope.launch(Dispatchers.IO) {
            val dp = DataProcessor(LocalDataSource())
            val questionsList = MockRepo()
            val answers = dp.getAnswers()
            var items = ArrayList<TriviaQuestion>()
            for(i in 0 until questionsList.triviaQuestions.size){
                items.add(TriviaQuestion(questionsList.triviaQuestions[i].question, answers[i]))
            }
            _triviaItems.postValue(items)
        }

    }
}