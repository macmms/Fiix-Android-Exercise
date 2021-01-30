package fiix.challenge.fiixexercise.kotlinsample

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fiix.challenge.fiixexercise.R
import kotlinx.android.synthetic.main.trivia_question_row.view.*

class TriviaQuestionsAdapter(private val itemClickListener: ItemClickListener) : RecyclerView.Adapter<TriviaQuestionsAdapter.MyViewHolder>() {

    var triviaQuestionsList = ArrayList<TriviaQuestion>()

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.trivia_question_row, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = triviaQuestionsList[holder.adapterPosition]

        holder.itemView.setOnClickListener {
            itemClickListener.onItemClick(holder.adapterPosition)
        }

        holder.itemView.questionText.text = currentItem.question
        holder.itemView.answerText.text = currentItem.answer

        when {
            currentItem.isVisible -> {
                holder.itemView.answerText.visibility = View.VISIBLE
                holder.itemView.checkAnswerButton.visibility = View.INVISIBLE
            }
            else -> {
                holder.itemView.answerText.visibility = View.INVISIBLE
                holder.itemView.checkAnswerButton.visibility = View.VISIBLE
            }
        }

        holder.itemView.checkAnswerButton.setOnClickListener {
            itemClickListener.onAnswerButtonClick(holder.adapterPosition)
        }

        if (currentItem.answer != null && holder.itemView.progressBar.visibility == View.VISIBLE) {
            holder.itemView.progressBar.visibility = View.GONE
        }
    }

    override fun getItemCount(): Int {
        return triviaQuestionsList.size
    }

    fun getTriviaAtPosition(position: Int) : TriviaQuestion {
        return triviaQuestionsList[position]
    }

    fun setQuestionsData(triviaQuestionsList: ArrayList<TriviaQuestion>) {
        this.triviaQuestionsList = triviaQuestionsList
        notifyDataSetChanged()
    }

    fun updateItem(trivia : TriviaQuestion, position: Int) {
        trivia.isVisible = false
        triviaQuestionsList[position] = trivia
        notifyItemChanged(position)
    }

    /**
     * Sets answers to its corresponding question in the trivia list
     */
    fun setAnswersData(answers: List<String>) {
        for (trivia in triviaQuestionsList) {
            // Map question to answer
            val index = triviaQuestionsList.indexOf(trivia)
            triviaQuestionsList[index].answer = answers[index]
        }
        notifyDataSetChanged()
    }

    interface ItemClickListener {
        fun onItemClick(position : Int)
        fun onAnswerButtonClick(position : Int)
    }
}