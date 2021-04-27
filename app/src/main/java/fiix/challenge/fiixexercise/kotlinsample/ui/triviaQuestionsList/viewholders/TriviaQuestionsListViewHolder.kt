package fiix.challenge.fiixexercise.kotlinsample.ui.triviaQuestionsList.viewholders

import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.google.android.material.textview.MaterialTextView
import fiix.challenge.fiixexercise.R
import fiix.challenge.fiixexercise.kotlinsample.models.TriviaQuestion
import fiix.challenge.fiixexercise.kotlinsample.ui.triviaQuestionsList.TriviaQuestionsListContract

class TriviaQuestionsListViewHolder(
    private val view: View,
    private val listener: TriviaQuestionsListContract.View?
): RecyclerView.ViewHolder(view) {

    private var rootConstraintLayout: ConstraintLayout? = null
    private var questionTitleTextView: MaterialTextView? = null
    private var questionContentTextView: MaterialTextView? = null
    private var answerTitleTextView: MaterialTextView? = null
    private var answerContentTextView: MaterialTextView? = null
    private var answerButton: MaterialButton? = null

    private var defaultDate: TriviaQuestion? = null

    init {
        // Wire up layouts
        rootConstraintLayout = view.findViewById(R.id.fe_trivia_questions_list_item_root_constraint_layout)
        questionTitleTextView = view.findViewById(R.id.fe_trivia_questions_list_item_question_title_text_view)
        questionContentTextView = view.findViewById(R.id.fe_trivia_questions_list_item_question_content_text_view)
        answerTitleTextView = view.findViewById(R.id.fe_trivia_questions_list_item_answer_title_text_view)
        answerContentTextView = view.findViewById(R.id.fe_trivia_questions_list_item_answer_content_text_view)
        answerButton = view.findViewById(R.id.fe_trivia_questions_list_item_answer_button)
        // Set up actions
        rootConstraintLayout?.setOnClickListener { _ ->
            defaultDate?.let { mItem ->
                listener?.onItemClick(id = mItem.id)
            }
        }
        answerButton?.setOnClickListener { _ ->
            defaultDate?.let { mItem ->
                listener?.onAnswerClick(id = mItem.id)
            }
        }
    }

    fun bind(position: Int, data: TriviaQuestion) {
        // Update data reference
        defaultDate = data
        // Update question texts
        questionTitleTextView?.text = String.format(view.resources.getString(R.string.question_title_text), (position + 1).toString())
        questionContentTextView?.text = data.question
        // Update answer texts
        answerContentTextView?.text = data.answer
        // Update answer visibility
        answerTitleTextView?.visibility = if (data.isShownAnswer) View.VISIBLE else View.GONE
        answerContentTextView?.visibility = if (data.isShownAnswer) View.VISIBLE else View.GONE
        answerButton?.visibility = if (data.isShownAnswer) View.GONE else View.VISIBLE
    }
}