package fiix.challenge.fiixexercise.kotlinsample.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import fiix.challenge.fiixexercise.R
import fiix.challenge.fiixexercise.kotlinsample.ViewModelFactory
import fiix.challenge.fiixexercise.kotlinsample.ui.edit.EditFragment
import fiix.challenge.fiixexercise.kotlinsample.ui.list.ListFragment

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: TriviaScreensViewModel

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViewModel()
        loadListFragment()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this, ViewModelFactory(application)).get(TriviaScreensViewModel::class.java)
        viewModel.getTriviaToEdit().observe(this, Observer { trivia ->
            trivia?.let { loadEditFragment() }
        })
    }

    private fun loadListFragment() {
        Log.i("Appa guruve", " New LIST Frag ")
        val listFragment = ListFragment.newInstance()
        supportFragmentManager.beginTransaction()
                .add(R.id.frag_container, listFragment)
                .commit()
    }

    private fun loadEditFragment() {
        Log.i("Appa guruve", " New EDIT Frag ")
        val editFragment = EditFragment.newInstance()
        supportFragmentManager.beginTransaction()
                .add(R.id.frag_container, editFragment)
                .addToBackStack(EditFragment.TAG)
                .commit()
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
        } else {
            super.onBackPressed()
        }
    }
}
