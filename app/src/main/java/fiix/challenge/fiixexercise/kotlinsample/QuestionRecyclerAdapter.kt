package fiix.challenge.fiixexercise.kotlinsample


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fiix.challenge.fiixexercise.R
import kotlinx.android.synthetic.main.layout_list_item.view.*
import kotlin.collections.ArrayList


class QuestionRecyclerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val TAG: String = "AppDebug"

    interface OnItemClickListener {
        fun onItemClick(item: TriviaQuestion?)
        fun onAnswerButtonClick(item: TriviaQuestion?)
    }

    private var items: List<TriviaQuestion> = ArrayList()
    private var listener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return questionViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.layout_list_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {

            is questionViewHolder -> {
                listener?.let { holder.bind(items.get(position), it) }
            }

        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun submitList(questionList: ArrayList<TriviaQuestion>, itemlistener: OnItemClickListener?) {
        items = questionList
        listener = itemlistener
    }

    class questionViewHolder
    constructor(
            itemView: View
    ) : RecyclerView.ViewHolder(itemView) {

        val question_title = itemView.text_view_question
        val question_answer = itemView.text_view_hideAnswer
        val button_question_answer = itemView.button_answer

        fun bind(question: TriviaQuestion, listener: OnItemClickListener) {

            question_title.setText(question.question)
            question_answer.setText(question.answer)
            if (question.isShowing) {
                question_answer.visibility = View.VISIBLE
                button_question_answer.visibility = View.GONE
            } else {
                question_answer.visibility = View.GONE
                button_question_answer.visibility = View.VISIBLE
            }
            button_question_answer.setOnClickListener {
                listener.onAnswerButtonClick(question)
            }
            itemView.setOnClickListener { listener.onItemClick(question) }

        }

    }

}
