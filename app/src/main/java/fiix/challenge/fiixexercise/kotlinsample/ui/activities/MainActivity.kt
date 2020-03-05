package fiix.challenge.fiixexercise.kotlinsample.ui.activities

import android.os.Bundle
import fiix.challenge.fiixexercise.R
import fiix.challenge.fiixexercise.kotlinsample.ui.fragments.EditTriviaFragment
import fiix.challenge.fiixexercise.kotlinsample.ui.fragments.TriviaListFragment
import fiix.challenge.fiixexercise.kotlinsample.ui.interfaces.OnClickListener


class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val triviaFragment = TriviaListFragment()
        addFragment(triviaFragment)
        triviaFragment.setOnClickListener(object : OnClickListener {
            override fun onClick(questionId: Int) {
                replaceFragment(EditTriviaFragment.newInstance(questionId))
            }
        })
    }
}
