package fiix.challenge.fiixexercise.kotlinsample.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import fiix.challenge.fiixexercise.R
import fiix.challenge.fiixexercise.kotlinsample.ui.QuestionsFragment.QuestionsFragmentListener
import fiix.challenge.fiixexercise.kotlinsample.utils.InjectionUtils
import fiix.challenge.fiixexercise.kotlinsample.viewmodels.TriviaViewModel

class TriviaActivity : AppCompatActivity(), QuestionsFragmentListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, QuestionsFragment.newInstance())
                .commit()
        }
    }

    override fun onQuestionSelected(questionId: Int) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, EditQuestionFragment.newInstance(questionId))
            .addToBackStack(null)
            .commit()
    }
}
