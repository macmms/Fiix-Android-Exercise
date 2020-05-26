package fiix.challenge.fiixexercise.kotlinsample.ui.edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import fiix.challenge.fiixexercise.R
import fiix.challenge.fiixexercise.kotlinsample.ui.BaseFragment
import fiix.challenge.fiixexercise.kotlinsample.ui.TriviaScreensViewModel
import kotlinx.android.synthetic.main.fragment_edit.*

/**
 * Fragment for edit screen
 */
class EditFragment : BaseFragment() {

    companion object {
        const val TAG = "EditFragment"

        fun newInstance(): EditFragment {
            return EditFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_edit, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        val viewModel = ViewModelProvider(requireActivity()).get(TriviaScreensViewModel::class.java)
        viewModel.getTriviaToEdit().observe(viewLifecycleOwner, Observer { trivia ->
            if (trivia == null) return@Observer exitScreen()
            edit_question_view.setText(trivia.question)
            edit_answer_view.setText(trivia.answer)
            edit_save_button.isEnabled = true
            edit_save_button.setOnClickListener {
                viewModel.updateTrivia(
                        trivia.copy(
                                question = edit_question_view.text.toString(),
                                answer = edit_answer_view.text.toString(),
                                isAnswerRevealed = false
                        )
                )
                viewModel.clearTriviaToEdit()
            }
        })
    }

    private fun exitScreen() {
        activity?.onBackPressed()
    }

}