package fiix.challenge.fiixexercise.kotlinsample.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fiix.challenge.fiixexercise.R
import fiix.challenge.fiixexercise.kotlinsample.dto.TriviaDTO
import fiix.challenge.fiixexercise.kotlinsample.util.Constants
import kotlinx.android.synthetic.main.item_question.view.*

class QuestionsAdapter(
    private val items: List<TriviaDTO>,
    private val onItemClick: (TriviaDTO) -> Unit,
    private val onButtonClick: (TriviaDTO) -> Unit
) : RecyclerView.Adapter<QuestionsAdapter.QuestionsHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionsHolder {
        val layout = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_question, parent, false)
        return QuestionsHolder(layout)
    }

    override fun onBindViewHolder(holder: QuestionsHolder, position: Int) {
        val triviaDTO = items[position]
        holder.tvQuestion.text = triviaDTO.question
        holder.tvAnswer.text = triviaDTO.answer
        holder.btAnswer.visibility = if (triviaDTO.status == Constants.STATUS_REVEALED)
            View.GONE else View.VISIBLE
        holder.tvAnswer.visibility =
            if (triviaDTO.status == Constants.STATUS_REVEALED) View.VISIBLE else View.GONE
        holder.btAnswer.setOnClickListener { onButtonClick(triviaDTO) }
        holder.itemView.setOnClickListener { onItemClick(triviaDTO) }
    }

    class QuestionsHolder(rootView: View) : RecyclerView.ViewHolder(rootView) {
        val tvQuestion: TextView = rootView.tvQuestion
        val tvAnswer: TextView = rootView.tvAnswer
        val btAnswer: Button = rootView.btAnswer
    }

    override fun getItemId(position: Int): Long {
        return super.getItemId(position)
    }

    override fun getItemCount(): Int = items.size
}