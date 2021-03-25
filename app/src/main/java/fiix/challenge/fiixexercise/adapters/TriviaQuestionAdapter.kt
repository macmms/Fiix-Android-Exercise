package fiix.challenge.fiixexercise.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fiix.challenge.fiixexercise.databinding.CustomQuestionRowBinding
import fiix.challenge.fiixexercise.interfaces.ClickEventHandler
import fiix.challenge.fiixexercise.model.TriviaQuestion

class TriviaQuestionAdapter(private val questionList: List<TriviaQuestion>, private val clickEventHandler: ClickEventHandler)
    :RecyclerView.Adapter<TriviaQuestionAdapter.QuestionsViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionsViewHolder {
        val binding = CustomQuestionRowBinding.inflate(LayoutInflater.from(parent.context))
        return QuestionsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: QuestionsViewHolder, position: Int) {
        holder.bind(questionList[position])
        holder.binding.tvAns.visibility = View.GONE
        holder.binding.btnAnswers.visibility = View.VISIBLE

        holder.itemView.setOnClickListener{
            clickEventHandler.itemClick(position)
        }

        holder.binding.btnAnswers.setOnClickListener{
            holder.binding.tvAns.visibility = View.VISIBLE
            holder.binding.btnAnswers.visibility = View.GONE
        }
    }

    override fun getItemCount() = questionList.size

    class QuestionsViewHolder(val binding: CustomQuestionRowBinding)
        : RecyclerView.ViewHolder(binding.root) {
        fun bind(question: TriviaQuestion) {
            binding.tvQuestions.text = question.question.trim()
            binding.tvAns.text = question.answer?.trim()
        }
    }
}