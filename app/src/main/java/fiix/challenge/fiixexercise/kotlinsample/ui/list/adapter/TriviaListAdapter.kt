package fiix.challenge.fiixexercise.kotlinsample.ui.list.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import fiix.challenge.fiixexercise.kotlinsample.data.db.trivia.Trivia

/**
 * Responsible for rendering the list.
 * */
class TriviaListAdapter(private val listener: Listener)
    : ListAdapter<Trivia, TriviaViewHolder>(ListAdapterDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TriviaViewHolder {
        return TriviaViewHolder.get(parent)
    }

    override fun onBindViewHolder(holder: TriviaViewHolder, position: Int) {
        val trivia = getItem(position)
        holder.bind(
                trivia = trivia,
                itemClicked = { listener.onTriviaClicked(trivia) },
                revealButtonClicked = { listener.onRevealAnswerClicked(trivia) }
        )
    }

    interface Listener {
        fun onTriviaClicked(trivia: Trivia)
        fun onRevealAnswerClicked(trivia: Trivia)
    }
}