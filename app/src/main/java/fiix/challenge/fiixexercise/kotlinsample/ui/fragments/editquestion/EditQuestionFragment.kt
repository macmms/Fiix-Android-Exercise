package fiix.challenge.fiixexercise.kotlinsample.ui.fragments.editquestion

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController

import fiix.challenge.fiixexercise.R
import fiix.challenge.fiixexercise.databinding.EditQuestionFragmentBinding
import fiix.challenge.fiixexercise.dp.DataProcessor
import fiix.challenge.fiixexercise.kotlinsample.LocalDataSource
import fiix.challenge.fiixexercise.kotlinsample.ui.MainActivityViewModel
import fiix.challenge.fiixexercise.kotlinsample.ui.MainViewModelFactory

class EditQuestionFragment : Fragment() {

    companion object {
        fun newInstance() = EditQuestionFragment()
    }

    private lateinit var viewModel: EditQuestionViewModel
    private lateinit var activityViewModel: MainActivityViewModel

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

        //Get Reference of MainActivity ViewModel
        val factory = MainViewModelFactory(DataProcessor(LocalDataSource()))
        activityViewModel = activity.run {
            ViewModelProvider(this!!.viewModelStore, factory)
                    .get(MainActivityViewModel::class.java)
        }

        //Observer for the live data
        viewModel.navigateToHome.observe(viewLifecycleOwner, Observer {
            if(it==true){
                viewModel.selectedTrivia?.answer=binding.etAnswer.text.toString()
                viewModel.selectedTrivia?.question=binding.etQuestion.text.toString()
                activityViewModel.isQuestionEdited.value=true
                this.findNavController().popBackStack()
//                this.findNavController().navigate(EditQuestionFragmentDirections.actionEditQuestionFragmentToHomeFragment())
                viewModel.doneNavigating()
            }
        })

        if(activityViewModel.selectedItemIndex>=0){
            viewModel.selectedTrivia = activityViewModel.triviaList.value?.get(activityViewModel.selectedItemIndex);

        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (requireActivity() as AppCompatActivity).supportActionBar?.title = getString(R.string.edit_question)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

}
