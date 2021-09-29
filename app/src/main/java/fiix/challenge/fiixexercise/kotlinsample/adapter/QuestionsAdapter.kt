package fiix.challenge.fiixexercise.kotlinsample.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import fiix.challenge.fiixexercise.databinding.ItemViewBinding
import fiix.challenge.fiixexercise.kotlinsample.TriviaQuestion
import kotlinx.android.synthetic.main.item_view.view.*

class QuestionsAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    inner class QuestionsViewHolder(val binding: ItemViewBinding): RecyclerView.ViewHolder(binding.root)

    private val diffCallBack = object: DiffUtil.ItemCallback<TriviaQuestion>(){
        override fun areItemsTheSame(oldItem: TriviaQuestion, newItem: TriviaQuestion): Boolean {
           return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: TriviaQuestion, newItem: TriviaQuestion): Boolean {
            return oldItem == newItem
        }
    }

    val diff = AsyncListDiffer(this, diffCallBack)
    lateinit var binding: ItemViewBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        binding = ItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return QuestionsViewHolder(binding)


    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = diff.currentList[position]
        holder.itemView.apply{
            tvQuestion.text = item.question
            tvAnswer.text = item.answer
            if (item.answerShown) {
                tvAnswer.visibility = View.VISIBLE
                btnAnswer.visibility = View.INVISIBLE
            } else {
                tvAnswer.visibility = View.INVISIBLE
                btnAnswer.visibility = View.VISIBLE
            }

            btnAnswer.setOnClickListener{
                tvAnswer.visibility = View.VISIBLE
                btnAnswer.visibility = View.INVISIBLE
                onAnswerBtnClickListener?.let{
                    it(item)
                }
            }

            setOnClickListener{
                onItemClickListener?.let{
                    it(item)
                }
            }


        }
    }

    override fun getItemCount(): Int {
        return diff.currentList.size
    }

    private var onItemClickListener: ((TriviaQuestion)->Unit)? = null

    private var onAnswerBtnClickListener: ((TriviaQuestion)->Unit)? = null

    fun setOnListItemClickListener(listener: (TriviaQuestion)->Unit){
        onItemClickListener = listener
    }

    fun setOnAnswerBtnClickListener(listener: (TriviaQuestion)->Unit){
        onAnswerBtnClickListener = listener
    }
}