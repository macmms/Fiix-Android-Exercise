package fiix.challenge.fiixexercise.kotlinsample.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import fiix.challenge.fiixexercise.R
import fiix.challenge.fiixexercise.kotlinsample.model.TriviaQuestion
import kotlinx.android.synthetic.main.item_question.view.*

class QuestionsListAdapter(private val context: Context, private val listener: QuestionsListInterface) : RecyclerView.Adapter<QuestionsListAdapter.QuestionViewHolder>() {

    private var items = mutableListOf<TriviaQuestion?>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        return QuestionViewHolder(LayoutInflater.from(context).inflate(R.layout.item_question, parent, false))
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(mHolder: QuestionViewHolder, position: Int) {
        items[position]?.let { question -> mHolder.bind(question) }
    }

    fun setQuestions(questions: List<TriviaQuestion>?) {
        this.items.clear()
        questions?.let { this.items.addAll(it) }
        notifyDataSetChanged()
    }

    inner class QuestionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: TriviaQuestion) = with(itemView) {

            item.question.let {
                TxtQuestion.text = it
            }
            item.answer.let {
                TxtAnswer.text = it
            }
            if (item.showAnswer) {
                TxtAnswer.visibility = View.VISIBLE
                BtnAnswer.visibility = View.VISIBLE
                BtnAnswer.text = context.getString(R.string.hide_answer)
            } else {
                TxtAnswer.visibility = View.GONE
                BtnAnswer.visibility = View.VISIBLE
                BtnAnswer.text = context.getString(R.string.answer)
            }

            itemView.BtnAnswer.setOnClickListener {
                listener.onAnswerClick(item)
            }

            itemView.rootView.setOnClickListener {
                listener.onRowClick(item)
            }
        }
    }
}