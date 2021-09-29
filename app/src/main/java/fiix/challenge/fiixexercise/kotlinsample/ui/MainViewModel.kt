package fiix.challenge.fiixexercise.kotlinsample.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fiix.challenge.fiixexercise.dp.DataProcessor
import fiix.challenge.fiixexercise.kotlinsample.LocalDataSource
import fiix.challenge.fiixexercise.kotlinsample.MockRepo
import fiix.challenge.fiixexercise.kotlinsample.TriviaQuestion
import fiix.challenge.fiixexercise.kotlinsample.model.Resource
import fiix.challenge.fiixexercise.kotlinsample.repository.QuestionsRepository
import kotlinx.coroutines.launch

class MainViewModel(val questionRepository: QuestionsRepository) : ViewModel() {
    val questions: MutableLiveData<Resource<String>> = MutableLiveData()
    val dp = DataProcessor(LocalDataSource())

    init{
        getQuestions()
    }

    fun getQuestions()  = viewModelScope.launch{
        questions.postValue(Resource.Loading())
        val response = dp.getAnswers()
        questions.postValue(handleResponse(response))
    }

    suspend fun handleResponse(response: List<String>) : Resource<String>{
        val questionList = MockRepo().triviaQuestions
        val itemList = arrayListOf<TriviaQuestion>()
        for ((i, answer) in response.withIndex()){
            questionList[i].answer = answer
            val item = questionList[i]
            itemList.add(item)
        }

        questionRepository.saveQuestions(itemList)

        return Resource.Success("Success")

    }

    fun getQuestionsInDb() = questionRepository.getQuestions()

    fun updateQuestionInDb(question: TriviaQuestion) = viewModelScope.launch{
        questionRepository.updateQuestion(question)
    }

}