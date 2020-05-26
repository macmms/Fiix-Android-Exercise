package fiix.challenge.fiixexercise.kotlinsample.ui.list.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fiix.challenge.fiixexercise.R
import fiix.challenge.fiixexercise.kotlinsample.data.db.trivia.Trivia
import fiix.challenge.fiixexercise.kotlinsample.util.SimpleCallback
import fiix.challenge.fiixexercise.kotlinsample.util.hide
import fiix.challenge.fiixexercise.kotlinsample.util.setThrottledClickListener
import fiix.challenge.fiixexercise.kotlinsample.util.show

/**
 * The ViewHolder for the list item
 */
class TriviaViewHolder private constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
    companion object {
        fun get(parent: ViewGroup): TriviaViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.view_trivia_list_item, parent, false)
            return TriviaViewHolder(view)
        }
    }

    private val revealButton = itemView.findViewById<Button>(R.id.list_item_reveal_button)
    private val questionView = itemView.findViewById<TextView>(R.id.list_item_question_view)
    private val answerView = itemView.findViewById<TextView>(R.id.list_item_answer_view)

    fun bind(trivia: Trivia, revealButtonClicked: SimpleCallback, itemClicked: SimpleCallback) {
        questionView.text = trivia.question
        answerView.text = trivia.answer
        if (trivia.isAnswerRevealed) {
            revealButton.hide()
            answerView.show()
        } else {
            revealButton.show()
            answerView.hide()
        }
        itemView.setThrottledClickListener {
            itemClicked()
        }
        revealButton.setThrottledClickListener {
            revealButtonClicked()
        }
    }

}