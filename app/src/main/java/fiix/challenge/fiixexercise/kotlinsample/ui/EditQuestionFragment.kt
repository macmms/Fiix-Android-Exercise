package fiix.challenge.fiixexercise.kotlinsample.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import fiix.challenge.fiixexercise.kotlinsample.utils.InjectionUtils
import fiix.challenge.fiixexercise.kotlinsample.viewmodels.EditQuestionViewModel

class EditQuestionFragment: Fragment() {

    private var questionId: Int = -1

    private val viewModel: EditQuestionViewModel by viewModels {
        InjectionUtils.provideEditQuestionViewModelFactory(requireContext())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            questionId = it.getInt(QUESTION_ID, -1)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    companion object {
        const val QUESTION_ID = "question_id"
        fun newInstance(questionId: Int) = EditQuestionFragment().apply {
            arguments = Bundle().apply {
                putInt(QUESTION_ID, questionId)
            }
        }
    }
}