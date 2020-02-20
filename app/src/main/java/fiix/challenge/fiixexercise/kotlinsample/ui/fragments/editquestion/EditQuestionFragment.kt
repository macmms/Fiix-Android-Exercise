package fiix.challenge.fiixexercise.kotlinsample.ui.fragments.editquestion

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider

import fiix.challenge.fiixexercise.R
import fiix.challenge.fiixexercise.databinding.EditQuestionFragmentBinding

class EditQuestionFragment : Fragment() {

    companion object {
        fun newInstance() = EditQuestionFragment()
    }

    private lateinit var viewModel: EditQuestionViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Get a reference to the binding object and inflate the fragment views.
        val binding: EditQuestionFragmentBinding = DataBindingUtil.inflate(
                inflater, R.layout.edit_question_fragment, container, false)

        //Create view model factory and get reference to the EditQuestionViewModel
        val viewModelFactory = EditQuestionViewModelFactory()
        viewModel = ViewModelProvider(this,viewModelFactory)
                .get(EditQuestionViewModel::class.java)

        // To use the View Model with data binding give the binding object a reference to it.
        binding.editQuestionViewModel = viewModel

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

          }

}
