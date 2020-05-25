package fiix.challenge.fiixexercise.kotlinsample.ui.list.adapter

import androidx.recyclerview.widget.DiffUtil
import fiix.challenge.fiixexercise.kotlinsample.data.db.trivia.Trivia

/**
 * Diff to help update the list adapter
 */
class ListAdapterDiffCallback : DiffUtil.ItemCallback<Trivia>() {

    override fun areItemsTheSame(oldItem: Trivia, newItem: Trivia): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Trivia, newItem: Trivia): Boolean {
        return (oldItem.question == newItem.question
                && oldItem.answer == newItem.answer
                && oldItem.isAnswerRevealed == newItem.isAnswerRevealed)
    }

}