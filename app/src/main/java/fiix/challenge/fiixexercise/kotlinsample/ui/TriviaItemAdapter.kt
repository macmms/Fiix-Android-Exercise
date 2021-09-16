package fiix.challenge.fiixexercise.kotlinsample.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fiix.challenge.fiixexercise.databinding.QuestionListElementBinding
import fiix.challenge.fiixexercise.kotlinsample.data.TriviaQuestion

class TriviaItemAdapter(private var dataSet: ArrayList<TriviaQuestion>, private val clickListener:(Int) -> Unit) :
    RecyclerView.Adapter<TriviaItemAdapter.ViewHolder>() {

    class ViewHolder(val binding: QuestionListElementBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int, clickListener: (Int) -> Unit) {
                clickListener(position)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val binding = QuestionListElementBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        viewHolder.binding.questionTextView.text =  dataSet[position].question
        viewHolder.binding.answerTextView.text = dataSet[position].answer

        viewHolder.binding.answerButton.setOnClickListener {
            viewHolder.binding.answerTextView.visibility = View.VISIBLE
            viewHolder.binding.answerButton.visibility = View.GONE
        }
        viewHolder.binding.cardView.setOnClickListener {
            viewHolder.bind(
                position,
                clickListener)
        }
    }

    override fun getItemCount() = dataSet.size

}