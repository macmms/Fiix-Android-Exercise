package fiix.challenge.fiixexercise.kotlinsample.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import fiix.challenge.fiixexercise.databinding.QuestionRowItemBinding
import fiix.challenge.fiixexercise.kotlinsample.model.TriviaQuestionUiModel

class TriviaQuestionsAdapter : RecyclerView.Adapter<TriviaQuestionsAdapter.TriviaDescViewHolder>() {

    var triviaQuestionsList: List<TriviaQuestionUiModel> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TriviaDescViewHolder {
        val binding = QuestionRowItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TriviaDescViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TriviaDescViewHolder, position: Int) = holder.bindQuestion(triviaQuestionsList[position])

    override fun getItemCount(): Int = triviaQuestionsList.size

    class TriviaDescViewHolder(private val binding: QuestionRowItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindQuestion(question: TriviaQuestionUiModel) {
            binding.apply {
                triviaQuestion = question
                answerButton.setOnClickListener { button ->
                    question.answer?.let {
                        button.visibility = View.INVISIBLE
                        answerTextView.visibility = View.VISIBLE
                    } ?: Toast.makeText(button.context, "Please wait till the answer is available", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}