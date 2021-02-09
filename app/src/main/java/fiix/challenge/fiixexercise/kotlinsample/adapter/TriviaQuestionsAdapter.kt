package fiix.challenge.fiixexercise.kotlinsample.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import fiix.challenge.fiixexercise.databinding.QuestionItemBinding
import fiix.challenge.fiixexercise.kotlinsample.data.TriviaQuestion
import fiix.challenge.fiixexercise.kotlinsample.ui.questions.QuestionsFragmentDirections

class TriviaQuestionsAdapter(private val onAnswerButtonClicked: (triviaQuestion: TriviaQuestion?) -> Unit) : ListAdapter<TriviaQuestion, RecyclerView.ViewHolder>(ProductDiffCallback()) {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            return QuestionViewHolder(
                QuestionItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                ),
                onAnswerButtonClicked
            )
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            val product = getItem(position)
            (holder as QuestionViewHolder).bind(product)
        }

        class QuestionViewHolder(
                private val binding: QuestionItemBinding,
                onAnswerButtonClicked: (triviaQuestion: TriviaQuestion?) -> Unit
        ) : RecyclerView.ViewHolder(binding.root) {
            init {
                binding.setClickListener {
                    binding.question?.let { question ->
                        navigateToQuestionDetail(question, it)
                    }
                }

                binding.answerButton.setOnClickListener {
                    onAnswerButtonClicked.invoke(binding.question)
                    binding.answerButton.visibility = View.INVISIBLE
                    if (binding.answer.visibility == View.INVISIBLE) {
                        binding.answer.visibility = View.VISIBLE
                    }
                }
            }

            private fun navigateToQuestionDetail(
                    triviaQuestion: TriviaQuestion,
                    view: View
            ) {
                val direction = QuestionsFragmentDirections.actionQuestionFragmentToQuestionDetailFragment(triviaQuestion.id)
                view.findNavController().navigate(direction)
            }

            fun bind(triviaQuestion: TriviaQuestion) {
                binding.apply {
                    question = triviaQuestion
                    executePendingBindings()
                }
            }
        }
}


private class ProductDiffCallback : DiffUtil.ItemCallback<TriviaQuestion>() {

    override fun areItemsTheSame(oldItem: TriviaQuestion, newItem: TriviaQuestion): Boolean {
        return oldItem.question == newItem.question
    }

    override fun areContentsTheSame(oldItem: TriviaQuestion, newItem: TriviaQuestion): Boolean {
        return oldItem == newItem
    }
}
