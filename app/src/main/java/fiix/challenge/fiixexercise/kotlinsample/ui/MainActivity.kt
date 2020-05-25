package fiix.challenge.fiixexercise.kotlinsample.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import fiix.challenge.fiixexercise.R
import fiix.challenge.fiixexercise.dp.DataProcessor
import fiix.challenge.fiixexercise.kotlinsample.ViewModelFactory
import fiix.challenge.fiixexercise.kotlinsample.data.LocalDataSource
import fiix.challenge.fiixexercise.kotlinsample.data.TriviaRepository
import fiix.challenge.fiixexercise.kotlinsample.data.db.TriviaDatabase
import fiix.challenge.fiixexercise.kotlinsample.ui.edit.EditFragment
import fiix.challenge.fiixexercise.kotlinsample.ui.list.ListFragment

class MainActivity : AppCompatActivity(), ListFragment.HostCallback, EditFragment.HostCallback {

    private lateinit var viewModel: TriviaScreensViewModel


    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViewModel()
    }

    private fun setupViewModel() {
        val repo = TriviaRepository(TriviaDatabase.getInstance(application), DataProcessor(LocalDataSource()))
        val viewModelFactory = ViewModelFactory(repo)
        viewModel = viewModelFactory.create(TriviaScreensViewModel::class.java)
        loadListFragment()
    }

    private fun loadListFragment() {
        val listFragment = ListFragment.newInstance(this)
        supportFragmentManager.beginTransaction()
                .add(R.id.frag_container, listFragment)
                .commit()
    }

    fun loadEditFragment() {
        val editFragment = EditFragment.newInstance(this)
        supportFragmentManager.beginTransaction()
                .add(R.id.frag_container, editFragment)
                .addToBackStack(EditFragment.TAG)
                .commit()
    }

    override fun getViewModel(): TriviaScreensViewModel {
        return viewModel
    }

    override fun loadEditScreen() {
        loadEditFragment()
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
        } else {
            super.onBackPressed()
        }
    }
}
