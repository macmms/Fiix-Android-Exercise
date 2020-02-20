package fiix.challenge.fiixexercise.kotlinsample.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import fiix.challenge.fiixexercise.dp.DataProcessor
import fiix.challenge.fiixexercise.kotlinsample.MockRepo
import fiix.challenge.fiixexercise.kotlinsample.model.TriviaQuestion
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MainActivityViewModel(private val dataProcessor: DataProcessor) : ViewModel() {

    var selectedItemIndex: Int = -1
    val isLoadingData = MutableLiveData<Boolean>()
    val isQuestionEdited= MutableLiveData<Boolean>()
    val triviaList = MutableLiveData<List<TriviaQuestion>>()

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.IO + viewModelJob)

    // To avoid memory leak turn all the co-routines off.
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    // Prepare the trivia list one time.
    fun prepareTriviaList() {
        //Return if data is already present
        if(!triviaList.value.isNullOrEmpty()) return

        isLoadingData.postValue(true)
        uiScope.launch {
            // Get questions
            val questions = MockRepo().triviaQuestions

            // Get Answers
            val answers = dataProcessor.getAnswers()

            if (answers.size == questions.size) {
                answers.forEachIndexed { position, answerString ->
                    questions[position].answer = answerString
                }
                triviaList.postValue(questions)
            } else {
                triviaList.postValue(listOf())
            }

            isLoadingData.postValue(false)

        }
    }
}
