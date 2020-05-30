package fiix.challenge.fiixexercise.kotlinsample

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import fiix.challenge.fiixexercise.R

class MainRvAdapter(private var qnas: List<TriviaQuestion> = emptyList()) : RecyclerView.Adapter<MainRvAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvQuestion : TextView by lazy {
            itemView.findViewById<TextView>(R.id.tv_question)
        }
        fun bind(triviaQuestion: TriviaQuestion) {
            tvQuestion.text = triviaQuestion.question
        }

    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.item_qna, p0, false))
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