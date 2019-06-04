package fiix.challenge.solution.views

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import fiix.challenge.fiixexercise.R
import fiix.challenge.solution.core.TriviaAdapter
import fiix.challenge.solution.models.TriviaQuestion
import fiix.challenge.solution.viewmodels.TriviaViewModel
import kotlinx.android.synthetic.main.question_list_fragment.*

class QuestionListFragment : Fragment() {

    lateinit var triviaViewModel: TriviaViewModel

    var adapter: TriviaAdapter? = null
    private var questionsList = mutableListOf<TriviaQuestion>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
            triviaViewModel = activity?.run {
                ViewModelProviders.of(this).get(TriviaViewModel::class.java)
            } ?: throw Exception("Activity not found")
        return inflater.inflate(R.layout.question_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (adapter == null) {
            adapter = TriviaAdapter(this::handleClick)
        }

        adapter!!.submitList(triviaViewModel.triviaQuestions.value)
        triviaRecyclerView.layoutManager = GridLayoutManager(this.activity, 1)
        triviaRecyclerView.adapter = adapter
        setObsevers()

        if(triviaViewModel.triviaQuestions.value.isNullOrEmpty()) {
            triviaViewModel.getTriviaQuestions()
        }
    }

    private fun handleClick(item : TriviaQuestion) {
        triviaViewModel.currentFragment.value = DetailViewFragment::class
        triviaViewModel.currentQuestion.value = item
    }

    private fun setObsevers() {

        triviaViewModel.triviaQuestions.observe(this, Observer {
            questionsList.clear()
            questionsList.addAll(it)
            adapter!!.submitList(questionsList)
            adapter!!.notifyDataSetChanged()
        })

        triviaViewModel.currentQuestion.observe(this, Observer {
            it.isCheckedOrAnswered = true
            adapter!!.notifyItemChanged(questionsList.indexOf(questionsList.find { item ->
                item.question == it.question
            }))

        })
    }
}