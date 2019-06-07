package fiix.challenge.fiixexercise.kotlinsample

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import fiix.challenge.fiixexercise.R

class TriviaQuestionsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder> {
    val questionsList: ArrayList<TriviaQuestion>
    val context: Context
    val layoutInflater: LayoutInflater

    constructor(context: Context, questionsList: ArrayList<TriviaQuestion>) {
        this.context = context
        this.questionsList = questionsList
        this.layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        val view = layoutInflater.inflate(R.layout.question_rowview, null, false)
        return RowViewHolder(view)
    }

    override fun getItemCount(): Int {
        return questionsList.size
    }

    fun getItem(position: Int): TriviaQuestion {
        return questionsList.get(position)
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        val rowViewHolder = viewHolder as RowViewHolder
        val triviaQuestion = getItem(position)
        rowViewHolder.questionTextView.text = triviaQuestion.question
        val isAnswered = RawData.questionAnswered!!.get(triviaQuestion.questionId)
        if (isAnswered == true) {
            rowViewHolder.answerShownImageView.visibility = View.VISIBLE
        } else {
            rowViewHolder.answerShownImageView.visibility = View.INVISIBLE
        }

        rowViewHolder.itemView.setTag(R.integer.tag_id, triviaQuestion.questionId);
        rowViewHolder.itemView.setTag(R.integer.tag_index, position)

        rowViewHolder.itemView.setOnClickListener { v ->
            val questionId = v.getTag(R.integer.tag_id) as Int
            RawData.questionAnswered!!.put(questionId, true)
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("id", questionId)
            intent.putExtra("index", v.getTag(R.integer.tag_index) as Int)
            context.startActivity(intent)
        }

    }

    inner class RowViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var questionTextView: TextView
        var answerShownImageView: ImageView

        init {
            questionTextView = view.findViewById(R.id.questionTextView)
            answerShownImageView = view.findViewById(R.id.answerShown)
        }

    }
}