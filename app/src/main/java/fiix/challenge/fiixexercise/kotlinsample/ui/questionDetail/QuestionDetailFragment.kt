package fiix.challenge.fiixexercise.kotlinsample.ui.questionDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import fiix.challenge.fiixexercise.R
import fiix.challenge.fiixexercise.databinding.QuestionDetailFragmentBinding

@AndroidEntryPoint
class QuestionDetailFragment : Fragment() {

    private val args: QuestionDetailFragmentArgs by navArgs()

    private val questionsDetailViewModel: QuestionsDetailViewModel by viewModels()
    private lateinit var binding: QuestionDetailFragmentBinding

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate<QuestionDetailFragmentBinding>(
                inflater,
                R.layout.question_detail_fragment,
                container,
                false
        ).apply {
            viewModel = questionsDetailViewModel
            lifecycleOwner = viewLifecycleOwner
        }
        setHasOptionsMenu(true)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val question = args.questionId
        questionsDetailViewModel.getQuestion(question)
    }
}



