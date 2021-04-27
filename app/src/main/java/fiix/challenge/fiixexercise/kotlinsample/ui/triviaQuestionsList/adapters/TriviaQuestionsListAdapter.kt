package fiix.challenge.fiixexercise.kotlinsample.ui.triviaQuestionsList.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fiix.challenge.fiixexercise.R
import fiix.challenge.fiixexercise.kotlinsample.models.TriviaQuestion
import fiix.challenge.fiixexercise.kotlinsample.ui.triviaQuestionsList.TriviaQuestionsListContract
import fiix.challenge.fiixexercise.kotlinsample.ui.triviaQuestionsList.viewholders.TriviaQuestionsListViewHolder

class TriviaQuestionsListAdapter(
    newDataList: List<TriviaQuestion>,
    private val listener: TriviaQuestionsListContract.View?
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val dataList: MutableList<TriviaQuestion> = mutableListOf()

    init {
        dataList.clear()
        dataList.addAll(elements = newDataList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_trivia_question, parent, false)
        return TriviaQuestionsListViewHolder(view = view, listener = listener)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? TriviaQuestionsListViewHolder)?.bind(position = position, data = dataList[position])
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun updateData(newDataList: List<TriviaQuestion>) {
        dataList.clear()
        dataList.addAll(elements = newDataList)
        notifyDataSetChanged()
    }
}