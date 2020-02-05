package fiix.challenge.fiixexercise.kotlinsample.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import fiix.challenge.fiixexercise.R
import fiix.challenge.fiixexercise.kotlinsample.MainActivity
import fiix.challenge.fiixexercise.kotlinsample.adapter.QuestionsListAdapter
import fiix.challenge.fiixexercise.kotlinsample.adapter.QuestionsListInterface
import fiix.challenge.fiixexercise.kotlinsample.model.TriviaQuestion
import fiix.challenge.fiixexercise.kotlinsample.viewmodels.TriviaViewModel
import kotlinx.android.synthetic.main.fragment_list_questions.*

lateinit var mViewModel: TriviaViewModel
var mLists = mutableListOf<TriviaQuestion>()
var mAdapter: QuestionsListAdapter? = null

class QuestionsFragmentList : Fragment(), QuestionsListInterface {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mViewModel = activity?.run {
            ViewModelProviders.of(this)[TriviaViewModel::class.java]
        } ?: throw Exception("Invalid Activity")
        return inflater.inflate(R.layout.fragment_list_questions, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mAdapter = QuestionsListAdapter(activity?.applicationContext!!, this)
        mAdapter?.setQuestions(mViewModel.questions.value)

        view.apply {
            RVquestions.layoutManager = LinearLayoutManager(requireContext())
            RVquestions.adapter = mAdapter
        }

        subscribeObservers()
    }

    fun subscribeObservers() {
        mViewModel.questions.observe(this, Observer { questionsList ->
            if (questionsList?.size!! > 0) {
                RVquestions.visibility = View.VISIBLE
                progressBar.visibility = View.GONE
                TxtError.visibility = View.GONE
            } else {
                progressBar.visibility = View.GONE
                RVquestions.visibility = View.GONE
                TxtError.visibility = View.VISIBLE
                TxtError.text = "There is a different number of questions and answers"
            }
            mLists.clear()
            mLists.addAll(questionsList)
            mAdapter?.setQuestions(mLists)
            mAdapter?.notifyDataSetChanged()
        })

        mViewModel.isLoadingData.observe(this, Observer { isLoaded ->
            if (isLoaded!!) {
                RVquestions.visibility = View.GONE
                progressBar.visibility = View.VISIBLE
            } else {
                RVquestions.visibility = View.VISIBLE
                progressBar.visibility = View.GONE
            }
        })

        if (mViewModel.questions.value.isNullOrEmpty()) {
            mViewModel.getQuestions()
        }
    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).supportActionBar?.title = getString(R.string.app_name)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
        (activity as AppCompatActivity).supportActionBar?.setHomeButtonEnabled(false)
    }

    override fun onAnswerClick(question: TriviaQuestion) {
        question.showAnswer = !question.showAnswer
        mAdapter?.notifyItemChanged(mLists.indexOf(mLists.find { item ->
            item.question == question.question
        }))
    }

    override fun onRowClick(question: TriviaQuestion) {
        mViewModel.currentFragment.value = EditQuestionFragment::class
        mViewModel.editQuestion.value = question
    }
}