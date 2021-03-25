package fiix.challenge.fiixexercise.kotlinsample.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import fiix.challenge.fiixexercise.databinding.DescriptionFragmentLayoutBinding
import fiix.challenge.fiixexercise.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class DescriptionFragment(private val position: Int) : Fragment() {

    private val mainViewModel: MainViewModel by sharedViewModel()

    private lateinit var binding: DescriptionFragmentLayoutBinding
    companion object {
        fun newInstance(position: Int): DescriptionFragment {
            return DescriptionFragment(position)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DescriptionFragmentLayoutBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        handleSaveClickButton()
        mainViewModel.getSelectedQuestionAnswer(position)
        observeViewModel()
    }

    private fun observeViewModel() {
        mainViewModel.selectedQuestion.observe(viewLifecycleOwner, Observer {
            binding.etQuestion.setText(it.question)
            binding.etAnswer.setText(it.answer)
        })
    }

    private fun handleSaveClickButton() {
        binding.btnSave.setOnClickListener{
            val question = binding.etQuestion.text.toString()
            val answer = binding.etAnswer.text.toString()
            if (question.isEmpty() || answer.isEmpty()){
                Toast.makeText(context,"Please Enter Valid Question Or Answer", Toast.LENGTH_LONG).show()
            }else{
                mainViewModel.updateQuestionAnswer(question,answer,position)
                Toast.makeText(context,"Successfully Updated", Toast.LENGTH_LONG).show()
            }
        }
    }
}