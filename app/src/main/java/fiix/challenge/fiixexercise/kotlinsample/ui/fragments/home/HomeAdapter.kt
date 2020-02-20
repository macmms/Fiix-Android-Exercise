package fiix.challenge.fiixexercise.kotlinsample.ui.fragments.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fiix.challenge.fiixexercise.R
import fiix.challenge.fiixexercise.kotlinsample.model.TriviaQuestion

class HomeAdapter(private val clickListener: HomeListener) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    var data = listOf<TriviaQuestion>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    fun showHideAnswer(index: Int) {
        data[index].showAnswer = !data[index].showAnswer
        notifyItemChanged(index)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent, clickListener)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    /*ViewHolder implementation with companion object for easy initialisation*/

    class ViewHolder private constructor(itemView: View, clickListener: HomeListener) : RecyclerView.ViewHolder(itemView) {
        private val tvQuestion: TextView = itemView.findViewById(R.id.tvQuestion)
        private val tvAnswer: TextView = itemView.findViewById(R.id.tvAnswer)
       private val buttonAnswer: Button = itemView.findViewById(R.id.buttonAnswer)

        init {
            itemView.setOnClickListener {
                clickListener.onClick(adapterPosition)
            }

            buttonAnswer.setOnClickListener {
                clickListener.showHideAnswer(adapterPosition)
            }
        }

        @SuppressLint("SetTextI18n")
        fun bind(triviaQuestion: TriviaQuestion) {
            tvQuestion.text = "Q. ${triviaQuestion.question}"
            tvAnswer.text ="Ans. ${triviaQuestion.answer}"
            if(triviaQuestion.showAnswer){
                buttonAnswer.text=itemView.context.getString(R.string.hide)
                tvAnswer.visibility=View.VISIBLE
            }else{
                buttonAnswer.text=itemView.context.getString(R.string.answer)
                tvAnswer.visibility=View.GONE

            }
        }

        companion object {
            fun from(parent: ViewGroup, clickListener: HomeListener): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater
                        .inflate(R.layout.list_item_home, parent, false)
                return ViewHolder(view, clickListener)
            }
        }
    }
}

// Callbacks from adapter to fragment
interface HomeListener {
    fun onClick(index: Int)
    fun showHideAnswer(index: Int)
}