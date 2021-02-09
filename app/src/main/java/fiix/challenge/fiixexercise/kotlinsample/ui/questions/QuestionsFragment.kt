package fiix.challenge.fiixexercise.kotlinsample.ui.questions

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import fiix.challenge.fiixexercise.databinding.QuestionsFragmentBinding
import fiix.challenge.fiixexercise.kotlinsample.adapter.TriviaQuestionsAdapter
import fiix.challenge.fiixexercise.kotlinsample.data.TriviaQuestion
import javax.inject.Inject

const val HAS_SEEDED_DB_KEY = "HAS_SEEDED_DB_KEY"
const val MUST_CLEAR_ANSWER_FIELD = "MUST_CLEAR_ANSWER_FIELD"
@AndroidEntryPoint
class QuestionsFragment : Fragment() {

    private val questionViewModel: QuestionsViewModel by viewModels()
    private lateinit var adapter: TriviaQuestionsAdapter
    private lateinit var binding: QuestionsFragmentBinding
    @Inject lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = QuestionsFragmentBinding.inflate(inflater, container, false)
        .apply {
            viewModel = questionViewModel
            lifecycleOwner = viewLifecycleOwner
        }
        setupViews()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(!sharedPreferences.getBoolean(HAS_SEEDED_DB_KEY, false)) {
            sharedPreferences.edit().putBoolean(HAS_SEEDED_DB_KEY, true).apply()
            questionViewModel.seedDatabase()
        }
        if (sharedPreferences.getBoolean(MUST_CLEAR_ANSWER_FIELD, false)) {
            sharedPreferences.edit().putBoolean(MUST_CLEAR_ANSWER_FIELD, false).apply()
            questionViewModel.resetHasClickedAnswer()
        }

        setupRecyclerView()
        setupObservers()
    }

    val onAnswerButtonClicked: (triviaQuestion: TriviaQuestion?) -> Unit = { triviaQuestion ->
        questionViewModel.onAnswerButtonClicked(triviaQuestion)
    }

    private fun setupViews() {

        adapter = TriviaQuestionsAdapter(onAnswerButtonClicked)
        binding.adapter = adapter
    }

    private fun setupRecyclerView() {
        binding.questionsRv.layoutManager = LinearLayoutManager(requireContext())
        binding.questionsRv.adapter = adapter
    }

    private fun setupObservers() {
        questionViewModel.allQuestions.observe(viewLifecycleOwner, Observer {
            it.let(adapter::submitList)
        })
        questionViewModel.isLoading.observe(viewLifecycleOwner, Observer {
            binding.progressBar.visibility = if (it) {
                View.VISIBLE
            } else {
                View.INVISIBLE
            }
        })
    }
}
