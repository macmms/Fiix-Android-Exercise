package fiix.challenge.fiixexercise.kotlinsample.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import fiix.challenge.fiixexercise.databinding.QuestionRowItemBinding
import fiix.challenge.fiixexercise.kotlinsample.model.TriviaQuestionUiModel
import fiix.challenge.fiixexercise.kotlinsample.viewmodel.SharedViewModel

/*
   Adapter for the trivia list recycler view
 */
class TriviaQuestionsAdapter : RecyclerView.Adapter<TriviaQuestionsAdapter.TriviaDescViewHolder>() {

    var triviaQuestionsList: List<TriviaQuestionUiModel> = listOf()
    var viewModel: SharedViewModel? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TriviaDescViewHolder {
        // Inflate layout through view binding
        val binding = QuestionRowItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TriviaDescViewHolder(binding, viewModel)
    }

    override fun onBindViewHolder(holder: TriviaDescViewHolder, position: Int) = holder.bindQuestion(triviaQuestionsList[position])

    override fun getItemCount(): Int = triviaQuestionsList.size

    class TriviaDescViewHolder(
        private val binding: QuestionRowItemBinding,
        private val viewModel: SharedViewModel?
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bindQuestion(question: TriviaQuestionUiModel) {
            binding.apply {
                // Set data in layout
                triviaQuestion = question
                answerButton.setOnClickListener { button ->
                    // Reveal the answer if it is available, else ask the user to wait
                    question.answer?.let {
                        button.visibility = View.INVISIBLE
                        answerTextView.visibility = View.VISIBLE
                    } ?: Toast.makeText(button.context, "Please wait till the answer is available", Toast.LENGTH_SHORT).show()
                }
                root.setOnClickListener { root ->
                    // Navigate to trivia detail screen if the answer is available, else ask the user to wait
                    question.answer?.let {
                        viewModel?.questionDetailBeingEdited = question
                        val action = MainFragmentDirections.actionMainFragmentToDetailsFragment()
                        root.findNavController().navigate(action)
                    } ?: Toast.makeText(root.context, "Please wait till the answer is available", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}