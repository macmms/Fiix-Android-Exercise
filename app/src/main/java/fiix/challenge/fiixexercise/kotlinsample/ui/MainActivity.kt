package fiix.challenge.fiixexercise.kotlinsample.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import fiix.challenge.fiixexercise.R
import fiix.challenge.fiixexercise.dp.DataProcessor
import fiix.challenge.fiixexercise.kotlinsample.ViewModelFactory
import fiix.challenge.fiixexercise.kotlinsample.data.LocalDataSource
import fiix.challenge.fiixexercise.kotlinsample.data.TriviaRepository
import fiix.challenge.fiixexercise.kotlinsample.data.db.TriviaDatabase
import fiix.challenge.fiixexercise.kotlinsample.ui.edit.EditFragment
import fiix.challenge.fiixexercise.kotlinsample.ui.list.ListFragment

class MainActivity : AppCompatActivity(), BaseFragment.HostCallback {

    private lateinit var viewModel: TriviaScreensViewModel

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViewModel()
        loadListFragment()
    }

    private fun setupViewModel() {
        val repo = TriviaRepository(TriviaDatabase.getInstance(application), DataProcessor(LocalDataSource()))
        val viewModelFactory = ViewModelFactory(repo)
        viewModel = viewModelFactory.create(TriviaScreensViewModel::class.java)
        viewModel.getTriviaToEdit().observe(this, Observer { trivia ->
            trivia?.let { loadEditFragment() }
        })
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

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
        } else {
            super.onBackPressed()
        }
    }
}
