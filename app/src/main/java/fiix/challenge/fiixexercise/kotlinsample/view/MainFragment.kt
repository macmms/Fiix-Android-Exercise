package fiix.challenge.fiixexercise.kotlinsample.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import fiix.challenge.fiixexercise.databinding.FragmentMainBinding
import fiix.challenge.fiixexercise.kotlinsample.viewmodel.SharedViewModel

/**
 * A simple [Fragment] subclass that displays details of a question
 */
class MainFragment : Fragment() {

    private var binding: FragmentMainBinding? = null
    private val viewModel: SharedViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.triviaQuestions.observe(viewLifecycleOwner) {
            Log.i("MainFragment", "List received $it")
            val adapter = TriviaQuestionsAdapter(it)
            binding?.questionsListRecyclerView?.adapter = adapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}