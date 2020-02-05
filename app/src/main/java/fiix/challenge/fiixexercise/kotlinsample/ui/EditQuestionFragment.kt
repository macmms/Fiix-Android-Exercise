package fiix.challenge.fiixexercise.kotlinsample.ui

import android.app.Activity
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import fiix.challenge.fiixexercise.R
import fiix.challenge.fiixexercise.kotlinsample.MainActivity
import fiix.challenge.fiixexercise.kotlinsample.model.TriviaQuestion
import fiix.challenge.fiixexercise.kotlinsample.viewmodels.TriviaViewModel
import kotlinx.android.synthetic.main.fragment_edit_question.*

class EditQuestionFragment : Fragment() {

    lateinit var mQuestion: TriviaQuestion
    lateinit var mViewModel: TriviaViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mViewModel = activity?.run {
            ViewModelProviders.of(this)[TriviaViewModel::class.java]
        } ?: throw Exception("Invalid Activity")

        return inflater.inflate(R.layout.fragment_edit_question, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)
        mQuestion = mViewModel.editQuestion.value!!

        EditQuestion.setText(mQuestion.question)
        EditAnswer.setText(mQuestion.answer)
        BtnSave.setOnClickListener { btnview ->
            mQuestion.question  = EditQuestion.text.toString()
            mQuestion.answer    = EditAnswer.text.toString()
            mViewModel.editQuestion(mQuestion)
            btnview.let { activity?.hideKeyboard(btnview) }
            Toast.makeText(activity, getString(R.string.question_updated), Toast.LENGTH_LONG).show()
        }
    }

    fun Context.hideKeyboard(view: View) {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as AppCompatActivity).supportActionBar?.setHomeButtonEnabled(true)
        (activity as MainActivity).supportActionBar?.title = getString(R.string.edit_question)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                activity!!.onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}