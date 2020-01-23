package fiix.challenge.fiixexercise.kotlinsample.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import fiix.challenge.fiixexercise.R
import fiix.challenge.fiixexercise.kotlinsample.data.TriviaQuestion
import fiix.challenge.fiixexercise.kotlinsample.ui.TriviaQuestionsAdapter.TriviaQuestionHolder
import kotlinx.android.synthetic.main.row_trivia_question.view.answerButton
import kotlinx.android.synthetic.main.row_trivia_question.view.answerTextView
import kotlinx.android.synthetic.main.row_trivia_question.view.progress
import kotlinx.android.synthetic.main.row_trivia_question.view.questionTextView

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
            itemView.apply {
                questionTextView.text = question.question
                question.answer?.let { answer ->
                    answerTextView.text = answer
                }
                answerButton.visibility = (!question.showAnswer).toVisibility()
                progress.visibility = (question.answer == null && question.showAnswer).toVisibility()
                answerTextView.visibility = (question.answer != null && question.showAnswer).toVisibility()
                answerButton.setOnClickListener {
                    listener.onAnswerClicked(question)
                }
                setOnClickListener {
                    listener.onQuestionEditClicked(question.id)
                }
            }
        }
    }

    private fun Boolean.toVisibility(): Int = if (this) View.VISIBLE else View.GONE

    interface TriviaQuestionListener {
        fun onAnswerClicked(question: TriviaQuestion)
        fun onQuestionEditClicked(questionId: Int)
    }
}
