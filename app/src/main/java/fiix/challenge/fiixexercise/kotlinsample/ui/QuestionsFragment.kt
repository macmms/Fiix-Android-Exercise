package fiix.challenge.fiixexercise.kotlinsample.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import fiix.challenge.fiixexercise.R
import fiix.challenge.fiixexercise.kotlinsample.data.TriviaQuestion
import fiix.challenge.fiixexercise.kotlinsample.ui.TriviaQuestionsAdapter.TriviaQuestionListener
import fiix.challenge.fiixexercise.kotlinsample.utils.InjectionUtils
import fiix.challenge.fiixexercise.kotlinsample.viewmodels.TriviaViewModel
import kotlinx.android.synthetic.main.fragment_questions.view.questionRecyclerView

class QuestionsFragment: Fragment(), TriviaQuestionListener {

    private val triviaViewModel: TriviaViewModel by viewModels {
        InjectionUtils.provideTriviaViewModelFactory(requireActivity())
    }

    private val adapter = TriviaQuestionsAdapter(this)
    private var listener: QuestionsFragmentListener? = null

    override fun onResume() {
        super.onResume()
        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
        (requireActivity() as AppCompatActivity).supportActionBar?.title = getString(R.string.trivia)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_questions, container, false)
        view.apply {
            questionRecyclerView.layoutManager = LinearLayoutManager(requireContext())
            questionRecyclerView.adapter = adapter
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        triviaViewModel.getQuestions().observe(viewLifecycleOwner, Observer { questions ->
            if (questions.first().answer == null) triviaViewModel.getAnswers(questions)
            adapter.updateQuestions(questions)
        })
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is QuestionsFragmentListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement QuestionsFragmentListener")
        }
    }

    override fun onAnswerClicked(question: TriviaQuestion) {
        triviaViewModel.getAnswer(question)
    }

    override fun onQuestionEditClicked(questionId: Int) {
        listener?.onQuestionSelected(questionId)
    }

    interface QuestionsFragmentListener {
        fun onQuestionSelected(questionId: Int)
    }

    companion object {
        fun newInstance() = QuestionsFragment()
    }
}