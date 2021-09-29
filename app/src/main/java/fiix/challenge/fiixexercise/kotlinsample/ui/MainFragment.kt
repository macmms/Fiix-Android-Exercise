package fiix.challenge.fiixexercise.kotlinsample.ui

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import fiix.challenge.fiixexercise.R
import fiix.challenge.fiixexercise.databinding.MainFragmentBinding
import fiix.challenge.fiixexercise.dp.DataProcessor
import fiix.challenge.fiixexercise.kotlinsample.LocalDataSource
import fiix.challenge.fiixexercise.kotlinsample.MainActivity
import fiix.challenge.fiixexercise.kotlinsample.MockRepo
import fiix.challenge.fiixexercise.kotlinsample.TriviaQuestion
import fiix.challenge.fiixexercise.kotlinsample.adapter.QuestionsAdapter
import fiix.challenge.fiixexercise.kotlinsample.db.QuestionsDatabase
import fiix.challenge.fiixexercise.kotlinsample.model.Resource
import fiix.challenge.fiixexercise.kotlinsample.repository.QuestionsRepository
import kotlinx.android.synthetic.main.activity_main.*

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var binding: MainFragmentBinding
    private lateinit var questionAdapter: QuestionsAdapter
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MainFragmentBinding.inflate(layoutInflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel

        setupRecyclerView()

        viewModel.questions.observe(viewLifecycleOwner, { response->
            when(response){
                is Resource.Success -> {
                    toggleProgressBar(true)
                }

                is Resource.Loading -> {
                    toggleProgressBar(false)
                }
            }

        })

        viewModel.getQuestionsInDb().observe(viewLifecycleOwner, {
            questionAdapter.diff.submitList(it)
        })
    }

    private fun toggleProgressBar(hide: Boolean) {
        if (hide)
            binding.pbProgress.visibility = View.INVISIBLE
        else
            binding.pbProgress.visibility = View.VISIBLE

    }

    private fun setupRecyclerView() {
        questionAdapter = QuestionsAdapter()
        questionAdapter.setOnListItemClickListener { question->
            val b = Bundle()
            b.putParcelable("question", question)
            findNavController().navigate(R.id.action_mainFragment_to_editFragment, b)

        }
        questionAdapter.setOnAnswerBtnClickListener { item->
            item.answerShown = true
            viewModel.updateQuestionInDb(item)
        }

        binding.rvQuestions.apply{
            adapter = questionAdapter
            layoutManager = LinearLayoutManager(activity)
        }

    }

}