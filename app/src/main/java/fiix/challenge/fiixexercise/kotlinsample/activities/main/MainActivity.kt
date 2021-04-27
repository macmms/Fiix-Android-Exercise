package fiix.challenge.fiixexercise.kotlinsample.activities.main

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import fiix.challenge.fiixexercise.R
import fiix.challenge.fiixexercise.kotlinsample.ui.triviaQuestionDetails.TriviaQuestionDetailsFragment
import fiix.challenge.fiixexercise.kotlinsample.ui.triviaQuestionsList.TriviaQuestionsListFragment
import fiix.challenge.fiixexercise.kotlinsample.viewmodels.TEViewModel

class MainActivity: AppCompatActivity(), MainActivityContract.Router {

    private var loadingContainerConstraintLayout: ConstraintLayout? = null

    private val teViewModel: TEViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initialViews()
        goToTriviaQuestionsListScreen()
    }

    override fun onBackPressed() {
        if (teViewModel.showLoading.value != true) super.onBackPressed()
    }

    // region MainActivityContract.Router Methods
    override fun goToTriviaQuestionsListScreen() {
        supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(R.anim.single_fade_in, R.anim.single_fade_out)
            .replace(R.id.fe_main_activity_fragment_container_view, TriviaQuestionsListFragment(resId = R.layout.fragment_trivia_questions_list))
            .commitAllowingStateLoss()
    }

    override fun goToTriviaQuestionDetailsScreen() {
        supportFragmentManager
            .beginTransaction()
            .addToBackStack(null)
            .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right)
            .add(R.id.fe_main_activity_fragment_container_view, TriviaQuestionDetailsFragment(resId = R.layout.fragment_trivia_question_details))
            .commitAllowingStateLoss()
    }

    override fun popBack() {
        supportFragmentManager.popBackStack()
    }
    // endregion

    // region Private Methods
    private fun initialViews() {
        loadingContainerConstraintLayout = findViewById(R.id.fe_main_activity_loading_container_constraint_layout)
        setUpLoadingIndicator()
    }

    // region Loading Layouts Methods
    private fun setUpLoadingIndicator() {
        teViewModel.showLoading.observe(this, { show ->
            showOrHideLoadingIndicator(show = show)
        })
    }

    private fun showOrHideLoadingIndicator(show: Boolean) {
        loadingContainerConstraintLayout?.visibility = if (show) View.VISIBLE else View.GONE
    }
    // endregion
    // endregion
}
