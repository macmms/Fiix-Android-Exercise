package fiix.challenge.fiixexercise.kotlinsample.ui.edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import fiix.challenge.fiixexercise.R
import fiix.challenge.fiixexercise.kotlinsample.ui.TriviaScreensViewModel
import kotlinx.android.synthetic.main.fragment_edit.*

/**
 * Fragment for edit screen
 */
class EditFragment : Fragment() {

    companion object {
        const val TAG = "EditFragment"

        fun newInstance(hostCallback: HostCallback): EditFragment {
            return EditFragment().apply {
                this.hostCallback = hostCallback
            }
        }
    }

    private lateinit var hostCallback: HostCallback

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_edit, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }


    private fun initViews() {
        val viewModel = hostCallback.getViewModel()
        viewModel.getTriviaToEdit().observe(viewLifecycleOwner, Observer { trivia ->
            if (trivia == null) return@Observer exitScreen()

            edit_question_view.setText(trivia.question)
            edit_answer_view.setText(trivia.answer)
            edit_save_button.isEnabled = true
            //todo: check data, before save
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

    interface HostCallback {
        fun getViewModel(): TriviaScreensViewModel
    }

}