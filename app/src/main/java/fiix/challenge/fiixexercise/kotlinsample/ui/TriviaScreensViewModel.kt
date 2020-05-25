package fiix.challenge.fiixexercise.kotlinsample.ui

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import fiix.challenge.fiixexercise.kotlinsample.data.TriviaRepository
import fiix.challenge.fiixexercise.kotlinsample.data.db.trivia.Trivia

/**
 * The main view model
 */
class TriviaScreensViewModel(private val repository: TriviaRepository) : ViewModel() {

    private val allTrivia = MutableLiveData<List<Trivia>>() //list of all trivia
    private val triviaToEdit = MutableLiveData<Trivia?>() // current trivia user is possibly editing

    init {
        fetchData()
    }

    fun getAllTrivia(): LiveData<List<Trivia>> {
        return allTrivia
    }

    fun getTriviaToEdit(): LiveData<Trivia?> {
        return triviaToEdit
    }

    fun setTriviaToEdit(trivia: Trivia) {
        triviaToEdit.postValue(trivia)
    }

    fun clearTriviaToEdit() {
        triviaToEdit.postValue(null)
    }

    fun revealTriviaAnswer(trivia: Trivia) {
        if (trivia.isAnswerRevealed) return
        val newTrivia = trivia.copy(isAnswerRevealed = true)
        updateTrivia(newTrivia)
    }

    fun updateTrivia(trivia: Trivia) {
        repository.updateTrivia(trivia)
        val currentList = allTrivia.value?.toMutableList() ?: return
        val indexToUpdate = currentList.indexOfFirst { it.id == trivia.id }
        currentList[indexToUpdate] = trivia
        allTrivia.postValue(currentList)
    }

    @SuppressLint("CheckResult")
    private fun fetchData() {
        repository.getTrivia()
                .subscribe { trivia ->
                    allTrivia.postValue(trivia)
                }
    }


    private val currentScreen = MutableLiveData<Screen>()
    private val currentDataState = MutableLiveData<DataState>()

    /**
     * Defines the possible screens that can be shown
     * */
    enum class Screen {
        LIST, EDIT
    }

    /**
     * Defines the possible states the data can be in
     * */
    enum class DataState {
        LOADING, FETCHED
    }

}