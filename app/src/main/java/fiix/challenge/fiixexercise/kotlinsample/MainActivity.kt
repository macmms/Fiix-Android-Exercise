package fiix.challenge.fiixexercise.kotlinsample

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import fiix.challenge.fiixexercise.R
import fiix.challenge.fiixexercise.dp.DataProcessor
import fiix.challenge.fiixexercise.kotlinsample.data.LocalDataSource
import fiix.challenge.fiixexercise.kotlinsample.ui.EditQuestionFragment
import fiix.challenge.fiixexercise.kotlinsample.ui.QuestionsFragmentList
import fiix.challenge.fiixexercise.kotlinsample.viewmodels.TriviaViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.reflect.KClass

class MainActivity : AppCompatActivity() {

    val dp = DataProcessor(LocalDataSource())
    lateinit var mListViewModel: TriviaViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
        assert(supportActionBar != null)

        mListViewModel = ViewModelProviders.of(this, ViewModelProvider.NewInstanceFactory())[TriviaViewModel::class.java]

        mListViewModel.currentFragment.observe(this, Observer { mKclass ->
            replaceFragment(mKclass!!, true)
        })

        // load question list Fragment
        replaceFragment(QuestionsFragmentList::class, false)
    }


    fun replaceFragment(fragmentClass: KClass<out Fragment>, addToBackStack: Boolean) {

        var fragment: Fragment? = supportFragmentManager.findFragmentByTag(fragmentClass.toString())

        if (fragment == null) {
            fragment = when (fragmentClass) {
                EditQuestionFragment::class -> {
                    EditQuestionFragment()
                }
                QuestionsFragmentList::class -> {
                    QuestionsFragmentList()
                }
                else -> {
                    null
                }
            }
        }

        if (fragment == null) {
            return
        }

        val mTransaction = supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, fragment, "")
        if(addToBackStack)
            mTransaction.addToBackStack(null)
        mTransaction.commit()

        return
    }
}
