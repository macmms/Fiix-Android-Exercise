package fiix.challenge.fiixexercise.kotlinsample.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import fiix.challenge.fiixexercise.databinding.FragmentDetailsBinding
import fiix.challenge.fiixexercise.kotlinsample.model.TriviaQuestionUiModel
import fiix.challenge.fiixexercise.kotlinsample.viewmodel.SharedViewModel

/**
 * A simple [Fragment] subclass that displays details of a question
 */
class DetailsFragment : Fragment() {

    private var binding: FragmentDetailsBinding? = null
    private val viewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            triviaQuestion = viewModel.questionDetailBeingEdited
            saveButton.setOnClickListener {
                viewModel.run {
                    questionDetailBeingEdited = null
                    updatedTrivia = TriviaQuestionUiModel(
                        questionEditText.text.toString(),
                        answerEditText.text.toString(),
                        triviaQuestion?.position ?: -1
                    )
                    activity?.onBackPressed()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}