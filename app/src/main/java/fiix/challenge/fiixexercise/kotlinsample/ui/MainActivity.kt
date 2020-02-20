package fiix.challenge.fiixexercise.kotlinsample.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import fiix.challenge.fiixexercise.R
import fiix.challenge.fiixexercise.dp.DataProcessor
import fiix.challenge.fiixexercise.kotlinsample.LocalDataSource

class MainActivity : AppCompatActivity() {

    val dp = DataProcessor(LocalDataSource())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Create view model factory & get reference of main view model.
        val factory = MainViewModelFactory(dp)

//        val viewModel = ViewModelProvider(this,factory).get(MainActivityViewModel::class.java)
//        viewModel.prepareTriviaList()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
           findNavController(R.id.fragmentHost).navigate(R.id.action_editQuestionFragment_to_homeFragment)
        }
        return super.onOptionsItemSelected(item)
    }
}
