package fiix.challenge.fiixexercise.ui.edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import fiix.challenge.fiixexercise.R
import fiix.challenge.fiixexercise.ui.ViewModelFactory
import kotlinx.android.synthetic.main.edit_question_fragment.*

class EditQuestionFragment : Fragment() {

    companion object {
        fun newInstance(questionId: Int) = EditQuestionFragment().apply {
            arguments = Bundle().apply {
                putInt("QUESTION_ID", questionId)
            }
        }
    }

    private val viewModel: EditQuestionViewModel by viewModels {
        ViewModelFactory.provideEditQuestionViewModelFactory(requireContext())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.edit_question_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.triviaQuestionLiveData.observe(viewLifecycleOwner, Observer {
            question.setText(it.question)
            answer.setText(it.answer)
        })


        arguments?.getInt("QUESTION_ID")?.let { viewModel.loadQuestion(it) }
        saveButton.setOnClickListener { viewModel.save(question = question.text.toString(), answer = answer.text.toString()) }
    }

}
