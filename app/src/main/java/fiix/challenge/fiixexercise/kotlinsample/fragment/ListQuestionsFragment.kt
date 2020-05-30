package fiix.challenge.fiixexercise.kotlinsample.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import fiix.challenge.fiixexercise.databinding.FragmentListQuestionsBinding
import fiix.challenge.fiixexercise.kotlinsample.adapter.QuestionsAdapter
import fiix.challenge.fiixexercise.kotlinsample.dto.TriviaDTO
import fiix.challenge.fiixexercise.kotlinsample.repository.TriviaRepository
import fiix.challenge.fiixexercise.kotlinsample.util.Constants
import fiix.challenge.fiixexercise.kotlinsample.viewmodel.TriviaViewModel
import fiix.challenge.fiixexercise.kotlinsample.viewmodel.TriviaViewModelFactory

class ListQuestionsFragment : Fragment() {
    private lateinit var binding: FragmentListQuestionsBinding
    private lateinit var layoutManager: LinearLayoutManager

    private val viewModel: TriviaViewModel by lazy {
        ViewModelProvider(
            this,
            TriviaViewModelFactory(
                TriviaRepository(requireContext())
            )
        ).get(TriviaViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListQuestionsBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        layoutManager = LinearLayoutManager(requireContext())
        binding.rvQuestions.layoutManager = layoutManager

        val layoutManager = LinearLayoutManager(requireContext())
        binding.rvQuestions.layoutManager = layoutManager
        binding.rvQuestions.addItemDecoration(
            DividerItemDecoration(requireContext(), layoutManager.orientation)
        )

        viewModel.questionsList.observe(viewLifecycleOwner, Observer { items ->
            binding.rvQuestions.adapter =
                QuestionsAdapter(items, this::openDetail, this::showAnswer)
        })
    }

    fun showAnswer(trivia: TriviaDTO) {
        trivia.status = Constants.STATUS_REVEALED
        viewModel.updateTrivia(trivia, 0L)
    }

    fun openDetail(trivia: TriviaDTO) {
        val action: ListQuestionsFragmentDirections.ActionListQuestionsFragmentToEditQuestionsFragment =
            ListQuestionsFragmentDirections.actionListQuestionsFragmentToEditQuestionsFragment(
                trivia
            )
        this.findNavController().navigate(action)
    }
}