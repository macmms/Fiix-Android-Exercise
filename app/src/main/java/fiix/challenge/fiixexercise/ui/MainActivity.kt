package fiix.challenge.fiixexercise.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import fiix.challenge.fiixexercise.R
import fiix.challenge.fiixexercise.ui.edit.EditQuestionFragment
import fiix.challenge.fiixexercise.ui.trivialist.OnTriviaQuestionSelectedListener
import fiix.challenge.fiixexercise.ui.trivialist.TriviaListFragment

class MainActivity : AppCompatActivity(), OnTriviaQuestionSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, TriviaListFragment.newInstance())
                    .addToBackStack(null)
                    .commit()
        }
    }

    override fun onTriviaQuestionClicked(questionId: Int) {
        supportFragmentManager.beginTransaction()
                .replace(R.id.container, EditQuestionFragment.newInstance(questionId))
                .addToBackStack(null)
                .commit()
    }
}
