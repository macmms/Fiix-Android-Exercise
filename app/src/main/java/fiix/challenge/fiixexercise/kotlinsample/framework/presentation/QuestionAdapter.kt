package fiix.challenge.fiixexercise.kotlinsample.framework.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fiix.challenge.fiixexercise.kotlinsample.utils.ItemClickEventHandler
import fiix.challenge.fiixexercise.databinding.ItemFragmentHomeBinding
import fiix.challenge.fiixexercise.kotlinsample.buisness.domain.TriviaQuestion

class QuestionAdapter(private val itemClickEventHandler: ItemClickEventHandler, private var triviaQuestionList: List<TriviaQuestion>) : RecyclerView.Adapter<QuestionAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemFragmentHomeBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
        ))
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(triviaQuestionList[position])
        holder.binding.root.setOnClickListener {
            itemClickEventHandler.itemClick(position)
        }
    }

    override fun getItemCount(): Int = triviaQuestionList.size

    inner class ViewHolder(val binding: ItemFragmentHomeBinding) :
            RecyclerView.ViewHolder(binding.root) {
        fun bind(triviaQuestion: TriviaQuestion) {
            binding.textQuestion.text = triviaQuestion.question.trim()
            binding.textAnswer.text = triviaQuestion.answer.toString().trim()

            binding.btnAnswer.setOnClickListener {
                binding.btnAnswer.visibility = View.GONE
                binding.textAnswer.visibility = View.VISIBLE
            }
        }
    }

    fun setData(triviaList: List<TriviaQuestion>) {
        this.triviaQuestionList = triviaList
        notifyDataSetChanged()
    }
}