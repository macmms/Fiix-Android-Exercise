package fiix.challenge.fiixexercise.kotlinsample

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import fiix.challenge.fiixexercise.R

class MainRvAdapter(private var qnas: List<TriviaQuestion> = emptyList(), val selectListener: QnaSelectListener) : RecyclerView.Adapter<MainRvAdapter.ViewHolder>() {
    interface QnaSelectListener {
        fun onItemSelected(triviaQuestion: TriviaQuestion)
    }

    class ViewHolder(itemView: View, private val selectListener: QnaSelectListener) : RecyclerView.ViewHolder(itemView) {
        private val tvQuestion: TextView by lazy {
            itemView.findViewById<TextView>(R.id.tv_question)
        }
        private val ctaAnswer: Button by lazy {
            itemView.findViewById<Button>(R.id.cta_answer)
        }
        private val tvAnswer: TextView by lazy {
            itemView.findViewById<TextView>(R.id.tv_answer)
        }

        fun bind(triviaQuestion: TriviaQuestion) {
            tvQuestion.text = triviaQuestion.question
            tvAnswer.text = triviaQuestion.answer
            ctaAnswer.setOnClickListener {
                tvAnswer.visibility = View.VISIBLE
                ctaAnswer.visibility = View.GONE
            }
            itemView.setOnClickListener {
                selectListener.onItemSelected(triviaQuestion)
            }
        }

    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.item_qna, p0, false), selectListener)
    }

    override fun getItemCount(): Int = qnas.size

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.bind(qnas[p1])
    }

    fun updateData(questions: List<TriviaQuestion>) {
        this.qnas = questions
        notifyDataSetChanged()
    }
}