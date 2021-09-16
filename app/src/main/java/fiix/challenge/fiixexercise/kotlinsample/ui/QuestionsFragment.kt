package fiix.challenge.fiixexercise.kotlinsample.ui

import androidx.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import fiix.challenge.fiixexercise.R
import fiix.challenge.fiixexercise.databinding.QuestionsFragmentBinding
import fiix.challenge.fiixexercise.kotlinsample.viewModels.MainViewModel

class QuestionsFragment: Fragment() {

    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var binding: QuestionsFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.questions_fragment,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.triviaItems.observe(viewLifecycleOwner, Observer { it ->
            if(binding.questionsRecyclerView.adapter == null){
                if (it.size > 0) {
                binding.questionsRecyclerView.adapter =
                    TriviaItemAdapter(it) { position: Int -> listItemClicked(position) }
                binding.questionsRecyclerView.layoutManager = LinearLayoutManager(view.context)
            }
            }
        })
        viewModel.selectedQuestionPosition.value?.let {
            binding.questionsRecyclerView.adapter?.notifyItemChanged(it)
        }
    }
    private fun listItemClicked(position: Int) {
        viewModel.setSelectedQuestion(position)
        findNavController().navigate(R.id.action_questionsFragment_to_detailsQuestionsFragment)
    }
}