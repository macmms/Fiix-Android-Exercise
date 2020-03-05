package fiix.challenge.fiixexercise.kotlinsample.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fiix.challenge.fiixexercise.R
import fiix.challenge.fiixexercise.kotlinsample.db.model.TriviaQuestion
import fiix.challenge.fiixexercise.kotlinsample.ui.interfaces.QuestionActionListener

class TriviaAdapter(private val listener: QuestionActionListener) : RecyclerView.Adapter<TriviaAdapter.NoteHolder>() {
    private var notes: List<TriviaQuestion> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item, parent, false)
        return NoteHolder(itemView)
    }

    override fun onBindViewHolder(holder: NoteHolder, position: Int) {
        holder.bindView(notes[position])
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    fun setNotes(notes: List<TriviaQuestion>) {
        this.notes = notes
        notifyDataSetChanged()
    }

    inner class NoteHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textViewTitle: TextView = itemView.findViewById(R.id.question)
        var answerbutton: Button = itemView.findViewById(R.id.ans_button)
        var answer: TextView = itemView.findViewById(R.id.answer)
        val progressBar: ProgressBar = itemView.findViewById(R.id.progress_circular)
        val linearLayout: LinearLayout = itemView.findViewById(R.id.button_layout)


        fun bindView(trivia: TriviaQuestion) {
            textViewTitle.text = trivia.question
            answer.text = "Answer: ${trivia.answer}"
            itemView.isEnabled = (trivia.answer != null)
            answerbutton.isEnabled = trivia.answer != null

            setViewVisibility(progressBar, (trivia.answer == null))
            setViewVisibility(answer, (trivia.answer != null && trivia.answerRevealed))
            setViewVisibility(linearLayout, (!trivia.answerRevealed))

            answerbutton.setOnClickListener(fun(it: View) {
                listener.onAnswerClicked(trivia)

            })
            itemView.setOnClickListener {
                listener.onQuestionEditClicked(trivia.id)
            }
        }
    }

    private fun setViewVisibility(view: View, shouldShow: Boolean) {
        view.visibility = if (shouldShow) View.VISIBLE else View.GONE
    }
}
