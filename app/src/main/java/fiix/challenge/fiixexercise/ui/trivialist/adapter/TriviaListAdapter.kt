package fiix.challenge.fiixexercise.ui.trivialist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fiix.challenge.fiixexercise.R
import fiix.challenge.fiixexercise.ui.trivialist.TriviaListViewModel
import fiix.challenge.fiixexercise.ui.TriviaQuestionModel

class TriviaListAdapter(private val viewModel: TriviaListViewModel) : RecyclerView.Adapter<TriviaListItemViewHolder>() {
    private var questionList: List<TriviaQuestionModel> = emptyList()

    fun setQuestionList(list: List<TriviaQuestionModel>) {
        questionList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TriviaListItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.trivia_item, parent, false)
        return TriviaListItemViewHolder(view, viewModel)
    }

    override fun getItemCount() = questionList.size

    override fun onBindViewHolder(holder: TriviaListItemViewHolder, position: Int) {
        val item = questionList[position]
        holder.bind(item)
    }
}