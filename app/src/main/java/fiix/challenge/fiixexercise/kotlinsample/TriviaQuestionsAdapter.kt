package fiix.challenge.fiixexercise.kotlinsample

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import fiix.challenge.fiixexercise.R
import fiix.challenge.fiixexercise.kotlinsample.TriviaQuestionsAdapter.TriviaQuestionHolder

class TriviaQuestionsAdapter(private val listener: TriviaQuestionListener) : Adapter<TriviaQuestionHolder>() {

    private val questions = mutableListOf<TriviaQuestion>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TriviaQuestionHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.row_trivia_question, parent, false)
        return TriviaQuestionHolder(view)
    }

    override fun getItemCount(): Int = questions.size

    override fun onBindViewHolder(holder: TriviaQuestionHolder, position: Int) {
        holder.bindView(questions[position])
    }

    fun updateQuestions(questions: List<TriviaQuestion>) {
        this.questions.clear()
        this.questions.addAll(questions)
        notifyDataSetChanged()
    }

    inner class TriviaQuestionHolder(itemView: View): ViewHolder(itemView) {
        fun bindView(question: TriviaQuestion) {

        }
    }

    interface TriviaQuestionListener {
        fun onAnswerClicked(question: TriviaQuestion)
    }
}
