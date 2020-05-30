package fiix.challenge.fiixexercise.kotlinsample.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import fiix.challenge.fiixexercise.dp.DataProcessor
import fiix.challenge.fiixexercise.kotlinsample.dto.TriviaDTO
import fiix.challenge.fiixexercise.kotlinsample.model.Trivia
import fiix.challenge.fiixexercise.kotlinsample.repository.LocalDataSource
import fiix.challenge.fiixexercise.kotlinsample.repository.TriviaRepository
import fiix.challenge.fiixexercise.kotlinsample.util.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TriviaViewModel(
    private val repository: TriviaRepository
) : ViewModel() {

    private val dp = DataProcessor(LocalDataSource())

    private val _hasQuestions = MutableLiveData<Boolean>()
    val hasQuestions: LiveData<Boolean> = _hasQuestions

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    val questionsList = repository.getTriviaList().asLiveData()

    private val _trivia = MutableLiveData<TriviaDTO>()
    val trivia: LiveData<TriviaDTO> = _trivia

    fun loadQuestionsFromAPI(){
        viewModelScope.launch {
            var id = 0
            val questions = withContext(Dispatchers.IO){
                dp.getQuestions()
            }

            if(questions.isNotEmpty()){
                val answers = withContext(Dispatchers.IO){
                    dp.getAnswers()
                }

                if(answers.isNotEmpty()){
                    answers.forEach{ description ->
                        val trivia = Trivia(
                            id + 1,
                            questions[id].question,
                            description,
                            Constants.STATUS_ORIGINAL
                        )
                        repository.save(trivia)
                        id++
                    }
                }
            }
            if(id >= questions.size){
                _isLoading.value = false
            }
        }
    }

    fun updateTrivia(dto: TriviaDTO, timeDelay: Long){
        viewModelScope.launch {
            delay(timeDelay)
            repository.updateTrivia(dto)
            _trivia.postValue(dto)
        }
    }

    fun getLoading(){
        viewModelScope.launch {
            _isLoading.value = true
        }
    }

    fun areThereQuestions() {
        viewModelScope.launch {
            _hasQuestions.value = repository.areThereQuestions()
        }
    }

    fun resetTrivia(){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                repository.resetTrivia()
            }
        }
    }
}