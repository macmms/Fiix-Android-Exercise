package fiix.challenge.fiixexercise.kotlinsample.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import fiix.challenge.fiixexercise.R
import fiix.challenge.fiixexercise.kotlinsample.ui.viewmodels.TriviaEditViewModel
import fiix.challenge.fiixexercise.kotlinsample.utilities.Injector

class EditTriviaFragment : Fragment() {

    private lateinit var triviaEditViewModel: TriviaEditViewModel
    companion object {
        private val ARG_CAUGHT = "myFragment_caught"

        fun newInstance(questionId: Int): EditTriviaFragment {
            val args = Bundle()
            args.putSerializable(ARG_CAUGHT, questionId)
            val fragment = EditTriviaFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_edit_trivia, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val question = view.findViewById<EditText>(R.id.question)
        val answer = view.findViewById<EditText>(R.id.answer)
        val save = view.findViewById<Button>(R.id.save)

        val questionId: Int = arguments!!.getInt(ARG_CAUGHT)

        val factory = Injector.provideEditTriviaViewModelFactory(requireContext())
        triviaEditViewModel = ViewModelProviders.of(requireActivity(), factory)
                .get(TriviaEditViewModel::class.java)

        val triviaQuestionItem = triviaEditViewModel.getTriviaQuestion(questionId)
        question.setText(triviaQuestionItem.question)
        answer.setText(triviaQuestionItem.answer)
        //val triviaViewmodel: TriviaEditViewModel = ViewModelProviders.of(this).get(TriviaEditViewModel::class.java)


        requireActivity().title = "Edit Trivia"

        save.setOnClickListener {
            val questionUpdated: String = question.text.toString().trim()
            val answerUpdated: String = answer.text.toString().trim()
            triviaQuestionItem.answerRevealed = !(triviaQuestionItem.question != questionUpdated || triviaQuestionItem.answer != answerUpdated)

            triviaQuestionItem.question = questionUpdated
            triviaQuestionItem.answer = answerUpdated

            triviaEditViewModel.updateTriviaQuestions(triviaQuestionItem)
            activity!!.supportFragmentManager.popBackStack()
        }
    }
}