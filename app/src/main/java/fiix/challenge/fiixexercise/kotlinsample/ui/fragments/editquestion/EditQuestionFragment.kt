package fiix.challenge.fiixexercise.kotlinsample.ui.fragments.editquestion

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider

import fiix.challenge.fiixexercise.R

class EditQuestionFragment : Fragment() {

    companion object {
        fun newInstance() = EditQuestionFragment()
    }

    private lateinit var viewModel: EditQuestionViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.edit_question_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val viewModelFactory = EditQuestionViewModelFactory()
        viewModel = ViewModelProvider(this,viewModelFactory).get(EditQuestionViewModel::class.java)
    }

}
