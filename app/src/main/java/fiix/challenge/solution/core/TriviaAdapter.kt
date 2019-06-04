package fiix.challenge.solution.core

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import fiix.challenge.fiixexercise.R
import fiix.challenge.solution.models.TriviaQuestion
import kotlinx.android.synthetic.main.item_trivia_question.view.*

class TriviaAdapter(
        private val listener: (TriviaQuestion) -> Unit
) : ListAdapter<TriviaQuestion, TriviaAdapter.QuestionHolder>(DiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionHolder {
        return QuestionHolder(LayoutInflater
                .from(parent.context)
                .inflate(
                        R.layout.item_trivia_question,
                        parent,
                        false
                ))
    }

    override fun onBindViewHolder(holder: QuestionHolder, position: Int) {
        holder.bind(getItem(position), listener)
    }

    class QuestionHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: TriviaQuestion, action: (TriviaQuestion) -> Unit) = with(itemView) {
            questionTextView.text = item.question
            if (item.isCheckedOrAnswered) {
                image.visibility = View.VISIBLE
            }

            itemView.setOnClickListener {
                action(item)
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<TriviaQuestion>() {
        override fun areItemsTheSame(oldItem: TriviaQuestion, newItem: TriviaQuestion): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: TriviaQuestion, newItem: TriviaQuestion): Boolean {
            return oldItem.question == newItem.question
        }
    }
}