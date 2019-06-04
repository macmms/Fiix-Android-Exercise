package fiix.challenge.solution.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import fiix.challenge.fiixexercise.R
import fiix.challenge.solution.viewmodels.TriviaViewModel
import kotlin.reflect.KClass

class MainActivity : FragmentActivity() {

    lateinit var triviaViewModel: TriviaViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.trivia_list_activity)

        if (!this::triviaViewModel.isInitialized) {
            triviaViewModel = ViewModelProviders.of(this, ViewModelProvider.NewInstanceFactory()).get(TriviaViewModel::class.java)
        }

        triviaViewModel.currentFragment.observe(this, Observer {
            changeFragment(it!!, true)
        })

        changeFragment(QuestionListFragment::class, false)
    }

    fun changeFragment(fragmentClass: KClass<out Fragment>,
                       addToBackStack: Boolean): Boolean {

        var fragment: Fragment? = supportFragmentManager.findFragmentByTag(fragmentClass.toString())

        if (fragment == null) {
            fragment = when (fragmentClass) {
                DetailViewFragment::class -> {
                    DetailViewFragment()
                }
                QuestionListFragment::class -> {
                    QuestionListFragment()
                }
                else -> {
                    null
                }
            }
        }
        if (fragment == null) {
            return false
        }

        val transaction = supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragmentContainer, fragment, fragmentClass.toString())

        if (addToBackStack) {
            transaction.addToBackStack(fragmentClass.toString())
        }

        transaction.commit()

        return true
    }


}
