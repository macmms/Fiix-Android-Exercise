package fiix.challenge.fiixexercise.kotlinsample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import fiix.challenge.fiixexercise.R
import fiix.challenge.fiixexercise.kotlinsample.TriviaQuestionsAdapter.TriviaQuestionListener
import kotlinx.android.synthetic.main.fragment_questions.view.questionRecyclerView

class QuestionsFragment: Fragment(), TriviaQuestionListener {

    private val triviaViewModel: TriviaViewModel by viewModels {
        InjectionUtils.provideTriviaViewModelFactory(requireContext())
    }

    private val adapter = TriviaQuestionsAdapter(this)

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
        triviaViewModel.fetchQuestions()
    }

    override fun onAnswerClicked(question: TriviaQuestion) {
        triviaViewModel.getAnswer(question)
    }

    companion object {
        fun newInstance() = QuestionsFragment()
    }
}