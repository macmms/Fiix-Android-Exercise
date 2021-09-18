package fiix.challenge.fiixexercise.kotlinsample.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import fiix.challenge.fiixexercise.databinding.FragmentMainBinding
import fiix.challenge.fiixexercise.kotlinsample.viewmodel.SharedViewModel

/**
 * A simple [Fragment] subclass that displays a list of questions
 */
class MainFragment : Fragment() {

    private var binding: FragmentMainBinding? = null
    private val viewModel: SharedViewModel by activityViewModels()
    private val adapter = TriviaQuestionsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Use view binding to inflate layout
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter.viewModel = viewModel
        binding?.questionsListRecyclerView?.adapter = adapter

        // Observe the list of questions and update the recycler view adapter whenever we get a new list
        viewModel.mainFragmentUiModel.observe(viewLifecycleOwner) { uiModel ->
            adapter.run {
                triviaQuestionsList = uiModel.triviaQuestions
                notifyDataSetChanged()
            }
            // Show a toast if applicable
            uiModel.toastMessage?.let {
                Toast.makeText(context, it, Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Assign the binding back to null on destroy view to prevent memory leaks
        binding = null
    }

}