package fiix.challenge.fiixexercise.kotlinsample.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import com.google.android.material.snackbar.Snackbar
import fiix.challenge.fiixexercise.R
import fiix.challenge.fiixexercise.databinding.EditFragmentBinding
import fiix.challenge.fiixexercise.kotlinsample.MainActivity
import fiix.challenge.fiixexercise.kotlinsample.TriviaQuestion

class EditFragment : Fragment() {
    private var saveButtonClicked = false
    private var item: TriviaQuestion? = null
    private lateinit var viewModel: MainViewModel

    companion object {
        fun newInstance() = EditFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = (activity as MainActivity).viewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.edit_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let{
            item = it.getParcelable("question")
        }



        EditFragmentBinding.bind(view).apply{
            this.etQuestion.setText(item?.question)
            this.etAnswer.setText(item?.answer)
            this.btnSave.setOnClickListener{
                saveButtonClicked = true
                val question = TriviaQuestion(item!!.id, etQuestion.text.toString(), etAnswer.text.toString(), false)
                viewModel.updateQuestionInDb(question)
                it.hideKeyboard()
            }
        }

        viewModel.getQuestionsInDb().observe(viewLifecycleOwner, {
            if (saveButtonClicked) {
                Snackbar.make(view, R.string.question_updated, Snackbar.LENGTH_SHORT).show()
                saveButtonClicked = false
            }
        } )

    }

    fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }

}