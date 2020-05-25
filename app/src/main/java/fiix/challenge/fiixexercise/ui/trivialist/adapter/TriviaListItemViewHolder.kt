package fiix.challenge.fiixexercise.ui.trivialist.adapter

import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fiix.challenge.fiixexercise.ui.trivialist.TriviaListViewModel
import fiix.challenge.fiixexercise.ui.TriviaQuestionModel
import kotlinx.android.synthetic.main.trivia_item.view.*

class TriviaListItemViewHolder(view: View, private val viewModel: TriviaListViewModel) : RecyclerView.ViewHolder(view) {
    private val triviaText: TextView = view.question
    private val answerButton: Button = view.trivia_answer
    private val content: View = view.content

    fun bind(model: TriviaQuestionModel) {
        if (model.isShowingAnswer) {
            answerButton.visibility = View.GONE
            triviaText.text = model.answer
        } else {
            answerButton.visibility = View.VISIBLE
            triviaText.text = model.question
        }

        answerButton.setOnClickListener {
            viewModel.showAnswer(model.id)
        }

        content.setOnClickListener{ viewModel.editQuestion(model.id)}
    }

}