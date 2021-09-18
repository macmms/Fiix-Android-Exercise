package fiix.challenge.fiixexercise.kotlinsample.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fiix.challenge.fiixexercise.databinding.QuestionRowItemBinding
import fiix.challenge.fiixexercise.kotlinsample.model.TriviaQuestion

class TriviaQuestionsAdapter : RecyclerView.Adapter<TriviaQuestionsAdapter.TriviaDescViewHolder>() {

    var triviaQuestionsList: List<TriviaQuestion> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TriviaDescViewHolder {
        val binding = QuestionRowItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TriviaDescViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TriviaDescViewHolder, position: Int) = holder.bindQuestion(triviaQuestionsList[position].question)

    override fun getItemCount(): Int = triviaQuestionsList.size

    class TriviaDescViewHolder(private val binding: QuestionRowItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindQuestion(question: String) {
            binding.question = question
        }
    }
}