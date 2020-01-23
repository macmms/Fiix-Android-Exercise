package fiix.challenge.fiixexercise.kotlinsample.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import fiix.challenge.fiixexercise.kotlinsample.data.TriviaQuestion
import fiix.challenge.fiixexercise.kotlinsample.utils.InjectionUtils
import fiix.challenge.fiixexercise.kotlinsample.viewmodels.EditQuestionViewModel
import kotlinx.android.synthetic.main.fragment_edit_question.view.answerEditText
import kotlinx.android.synthetic.main.fragment_edit_question.view.questionEditText
import kotlinx.android.synthetic.main.fragment_edit_question.view.saveButton

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
        check(questionId < 0) { "Invalid ID passed to EditQuestionFragment" }

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.getQuestion(questionId).observe(viewLifecycleOwner, Observer { question ->
            view.apply {
                questionEditText.setText(question.question, TextView.BufferType.EDITABLE)
                answerEditText.setText(question.answer, TextView.BufferType.EDITABLE)
                saveButton.setOnClickListener {
                    val newQuestion = TriviaQuestion(
                        questionId,
                        questionEditText.text.toString(),
                        answerEditText.text.toString()
                    )
                    viewModel.saveQuestion(newQuestion)
                }
            }
        })
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