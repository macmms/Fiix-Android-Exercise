package fiix.challenge.fiixexercise.kotlinsample

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import fiix.challenge.fiixexercise.R
import kotlinx.android.synthetic.main.question_list_item.view.*

class TriviaQuestionAdapter(private val dataList: ArrayList<TriviaQuestion>,val listener: (Int) -> Unit) : RecyclerView.Adapter<TriviaQuestionAdapter.TriviaQuestionViewHolder>() {
    lateinit var  mContext:Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TriviaQuestionViewHolder {
        mContext=parent.context
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.question_list_item, parent, false)
        return TriviaQuestionViewHolder(view)
    }

    override fun onBindViewHolder(holder: TriviaQuestionViewHolder, position: Int) {
        holder.bind(this.dataList[position], position, listener)
        val item=dataList[position]
        holder.questionTextView.text = item.question
        /*If user has seen the answer then it will change the color of selected list item*/
        if(item.seen)
            holder.cardLayout.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.colorPrimary))
        else
            holder.cardLayout.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.colorSkyBlue))
    }


    override fun getItemCount(): Int {
        return dataList.size
    }

    class TriviaQuestionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var questionTextView: TextView = itemView.questionText
        var cardLayout: CardView = itemView.cardLayout
        fun bind(item: TriviaQuestion, pos: Int, listener: (Int) -> Unit) = with(itemView) {
            item_layout.setOnClickListener {
                listener(pos)
            }
        }
    }

}