package fiix.challenge.fiixexercise.kotlinsample.ui.fragments.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {
    //Navigation block starts here
    /*For making navigation reactive.
       We will use live data to perform navigation*/
    private val _navigateToEditQuestion = MutableLiveData<Boolean>()

    //To observe the live value
    val navigateToEditQuestion: LiveData<Boolean?>
        get() = _navigateToEditQuestion

    /**
     * Function to be called when list item is pressed.
     * */
    fun listItemClicked() {
        _navigateToEditQuestion.value = true
    }

    // To call after navigation
    fun doneNavigating() {
        _navigateToEditQuestion.value = null
    }

    // Navigation block ends here

}
