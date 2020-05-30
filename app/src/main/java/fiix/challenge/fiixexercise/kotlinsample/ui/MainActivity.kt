package fiix.challenge.fiixexercise.kotlinsample.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import fiix.challenge.fiixexercise.R
import fiix.challenge.fiixexercise.kotlinsample.repository.TriviaRepository
import fiix.challenge.fiixexercise.kotlinsample.viewmodel.TriviaViewModel
import fiix.challenge.fiixexercise.kotlinsample.viewmodel.TriviaViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val viewModel: TriviaViewModel by lazy {
        ViewModelProvider(
            this,
            TriviaViewModelFactory(
                TriviaRepository(this)
            )
        ).get(TriviaViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.hasQuestions.observe(
            this,
            Observer { hasQuestions ->
                if(!hasQuestions){
                    viewModel.loadQuestionsFromAPI()
                }else{
                    showLoadingBar(false)
                }
            }
        )
        viewModel.areThereQuestions()

        viewModel.isLoading.observe(
            this,
            Observer { loading ->
                showLoadingBar(loading)
            }
        )
        viewModel.getLoading()
    }

    private fun showLoadingBar(loading: Boolean) {
        if(loading){
            progressBar.visibility = View.VISIBLE
        }else{
            progressBar.visibility = View.GONE
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.settings_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.action_reset){
            viewModel.resetTrivia()
        }
        return super.onOptionsItemSelected(item)
    }
}