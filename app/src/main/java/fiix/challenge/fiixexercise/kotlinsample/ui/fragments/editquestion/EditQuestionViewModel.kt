package fiix.challenge.fiixexercise.kotlinsample.ui.fragments.editquestion

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import fiix.challenge.fiixexercise.kotlinsample.model.TriviaQuestion

class EditQuestionViewModel : ViewModel() {
    var selectedTrivia : TriviaQuestion?=null

    //Navigation block starts here
    private val _navigateToHome = MutableLiveData<Boolean>()

    //Variable for observing
    val navigateToHome : LiveData<Boolean>
        get() = _navigateToHome

    //Function to call on save
    fun onSaved(){
        _navigateToHome.value=true
    }

    //Function to call after navigation
    fun doneNavigating(){
        _navigateToHome.value=null
    }

}
