package fiix.challenge.fiixexercise.kotlinsample.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.jarvis.ca.Mark
import fiix.challenge.fiixexercise.R
import fiix.challenge.fiixexercise.databinding.FragmentEditQuestionsBinding
import fiix.challenge.fiixexercise.kotlinsample.dto.TriviaDTO
import fiix.challenge.fiixexercise.kotlinsample.repository.TriviaRepository
import fiix.challenge.fiixexercise.kotlinsample.util.Constants
import fiix.challenge.fiixexercise.kotlinsample.viewmodel.TriviaViewModel
import fiix.challenge.fiixexercise.kotlinsample.viewmodel.TriviaViewModelFactory

class EditQuestionsFragment : Fragment() {

    private lateinit var binding: FragmentEditQuestionsBinding
    lateinit var dto: TriviaDTO

    private val viewModel: TriviaViewModel by lazy {
        ViewModelProvider(
            this,
            TriviaViewModelFactory(
                TriviaRepository(requireContext())
            )
        ).get(TriviaViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditQuestionsBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(arguments != null) {
            val args = EditQuestionsFragmentArgs.fromBundle(requireArguments())
            dto = args.trivia as TriviaDTO
            binding.etQuestion.setText(dto.question)
            binding.etAnswer.setText(dto.answer)
            binding.btSave.setOnClickListener{ updateTrivia(dto) }

            viewModel.trivia.observe(viewLifecycleOwner, Observer { triviaDTO ->
                if(triviaDTO.status == Constants.STATUS_UPDATED) {
                    binding.progressBar.visibility = View.GONE
                    binding.btSave.isEnabled = true
                    Mark.showAlert(
                        requireActivity(), null, getString(R.string.message_save_success),
                        R.color.colorBackgroundAlert, 3000L
                    )
                    viewModel.updateTrivia(
                        TriviaDTO(
                            dto.id,
                            dto.question,
                            dto.answer,
                            Constants.STATUS_ORIGINAL
                        ), 0L
                    )
                }
            })
        }else{
            Mark.showAlert(requireActivity(), null, getString(R.string.message_load_trivia_error),
                R.color.colorBackgroundAlert, 3000L, R.drawable.ic_error)
        }
    }

    private fun updateTrivia(dto: TriviaDTO) {
        if(isValid()){
            binding.progressBar.visibility = View.VISIBLE
            binding.btSave.isEnabled = false

            this.dto.question = binding.etQuestion.text.toString()
            this.dto.answer = binding.etAnswer.text.toString()

            viewModel.updateTrivia(
                TriviaDTO(
                    dto.id,
                    dto.question,
                    dto.answer,
                    Constants.STATUS_UPDATED
                ), 1000L
            )
        }
    }

    private fun isValid(): Boolean {
        var valid = true
        if(binding.etQuestion.text.isEmpty()){
            valid = false
            binding.etQuestion.requestFocus()
            Mark.showAlert(requireActivity(), null, getString(R.string.message_invalid_question),
                R.color.colorBackgroundAlert, 3000L, R.drawable.ic_error)
        }else if(binding.etAnswer.text.isEmpty()){
            valid = false
            binding.etAnswer.requestFocus()
            Mark.showAlert(requireActivity(), null, getString(R.string.message_invalid_answer),
                R.color.colorBackgroundAlert, 3000L, R.drawable.ic_error)
        }
        return valid
    }
}
