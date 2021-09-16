package fiix.challenge.fiixexercise.kotlinsample.ui

import android.content.Context
import androidx.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import fiix.challenge.fiixexercise.R
import fiix.challenge.fiixexercise.databinding.DetailsQuestionsFragmentBinding
import fiix.challenge.fiixexercise.kotlinsample.viewModels.MainViewModel

class DetailsQuestionsFragment: Fragment() {

    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var binding: DetailsQuestionsFragmentBinding
    private var questionNumber = 0
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.details_questions_fragment,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.selectedQuestionPosition.observe(viewLifecycleOwner, Observer {
            questionNumber = it
            binding.questionEditText.setText(viewModel.getSelectedTrivia(it)?.question)
            binding.answerEditText.setText(viewModel.getSelectedTrivia(it)?.answer)
        })

        binding.saveButton.setOnClickListener {
            //the Question does not mention any limitation for the input
            //otherwise we could have a validator before we change the values
            Snackbar.make(requireView(), getString(R.string.saved), Snackbar.LENGTH_SHORT).show()
            viewModel.editAnswer(questionNumber, binding.answerEditText.text.toString())
            viewModel.editQuestion(questionNumber, binding.questionEditText.text.toString())
            findNavController().navigate(R.id.action_detailsQuestionsFragment_to_questionsFragment)
            it.hideKeyboard()
        }
    }
    private fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }
}