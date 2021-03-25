package fiix.challenge.fiixexercise.kotlinsample.ui

import android.os.Bundle
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fiix.challenge.fiixexercise.adapters.TriviaQuestionAdapter
import fiix.challenge.fiixexercise.databinding.ActivityMainBinding
import fiix.challenge.fiixexercise.interfaces.ClickEventHandler
import fiix.challenge.fiixexercise.model.TriviaQuestion
import fiix.challenge.fiixexercise.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), ClickEventHandler {

    private val mainViewModel: MainViewModel by viewModel()
    private lateinit var binding: ActivityMainBinding
    private var questionList  = ArrayList<TriviaQuestion>()
    private lateinit var triviaQuestionAdapter: TriviaQuestionAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getQuestions()
        observeViewModel()
        setupAdapter()
    }

    private fun getQuestions() {
        binding.rvQuestions.visibility = RecyclerView.GONE
        mainViewModel.syncQuestionAnswers()
    }

    private fun setupAdapter() {
        triviaQuestionAdapter = TriviaQuestionAdapter(questionList, this)
        binding.rvQuestions.layoutManager = LinearLayoutManager(this)
        binding.rvQuestions.adapter = triviaQuestionAdapter
    }

    private fun observeViewModel() {
        mainViewModel.questions.observe(this, androidx.lifecycle.Observer {
            questionList.clear()
            questionList.addAll(it)
            triviaQuestionAdapter.notifyDataSetChanged()
            binding.progressBar.visibility = ProgressBar.GONE
            binding.rvQuestions.visibility = RecyclerView.VISIBLE
        })
    }

    override fun itemClick(position: Int) {
        supportFragmentManager.beginTransaction()
                .replace(binding.flContainer.id,DescriptionFragment.newInstance(position))
                .addToBackStack("description")
                .commit()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        triviaQuestionAdapter.notifyDataSetChanged()
    }
}
