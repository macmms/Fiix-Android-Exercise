package fiix.challenge.fiixexercise.kotlinsample.framework.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import fiix.challenge.fiixexercise.R
import fiix.challenge.fiixexercise.databinding.FragmentDetailBinding
import fiix.challenge.fiixexercise.kotlinsample.utils.hideKeyboard
import fiix.challenge.fiixexercise.kotlinsample.framework.presentation.viewmodule.HomeViewModel

@AndroidEntryPoint
class DetailedFragment : Fragment(), View.OnClickListener {

    private lateinit var binding: FragmentDetailBinding
    private val mHomeViewModel: HomeViewModel by activityViewModels()
    private  var position : Int = 0

    override fun onCreateView(inflater: LayoutInflater, @Nullable container: ViewGroup?, @Nullable savedInstanceState: Bundle?): View? {
        binding = FragmentDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mHomeViewModel.position.observe(viewLifecycleOwner,  {
            binding.editQuestion.setText(mHomeViewModel.triviaQuestionList.value?.get(it)?.question?.trim())
            binding.editAnswer.setText(mHomeViewModel.triviaQuestionList.value?.get(it)?.answer?.trim())
            position = it
        })
        binding.btnSave.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            binding.btnSave.id -> updateTrivialQuestion()
        }
    }

    private fun updateTrivialQuestion() {
        if (validateTrivialQuestion()){
            mHomeViewModel.updateTrivialQuestion(position, binding.editAnswer.text.toString().trim(),
                    binding.editQuestion.text.toString().trim())
            findNavController().navigate(R.id.action_detailedFragment_to_homeFragment)
        }
        binding.root.hideKeyboard()
    }


    private fun validateTrivialQuestion(): Boolean {
        if (binding.editQuestion.text.isNullOrEmpty()) {
            Toast.makeText(context, getString(R.string.errorQuestion), Toast.LENGTH_LONG).show()
            return false
        }else if(binding.editAnswer.text.isNullOrEmpty()){
            Toast.makeText(context, getString(R.string.errorAnswer), Toast.LENGTH_LONG).show()
            return false
        }
        else return true
    }
}

