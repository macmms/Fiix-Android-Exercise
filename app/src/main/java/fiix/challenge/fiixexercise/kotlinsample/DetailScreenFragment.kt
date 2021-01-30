package fiix.challenge.fiixexercise.kotlinsample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import fiix.challenge.fiixexercise.R

class DetailScreenFragment : Fragment() {

    private lateinit var triviaViewModel: TriviaViewModel

    private lateinit var questionEditText: EditText
    private lateinit var answerEditText: EditText

    private lateinit var trivia : TriviaQuestion
    private var position : Int = -1

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_screen_details_layout, container, false)

        trivia = arguments?.getSerializable(getString(R.string.TRIVIA_BUNDLE_KEY)) as TriviaQuestion
        position = arguments?.getInt(getString(R.string.POSITION_BUNDLE_KEY))!!

        questionEditText = view.findViewById(R.id.questionText)
        answerEditText = view.findViewById(R.id.answerText)

        triviaViewModel = ViewModelProvider(this).get(TriviaViewModel::class.java)
        setAnswerObserver()

        questionEditText.setText(trivia.question)

        if (trivia.answer == null) {
            triviaViewModel.retrieveAnswers()
            Toast.makeText(context, "Answer is currently loading", Toast.LENGTH_LONG).show()
        } else {
            answerEditText.setText(trivia.answer)
        }

        onSaveButtonClick(view)
        return view
    }

    private fun setAnswerObserver() {
        triviaViewModel.triviaAnswersLiveData.observe(this, Observer<List<String>> {
            answers ->
            trivia.answer = answers[position]
            answerEditText.setText(trivia.answer)
            Toast.makeText(context, "Answer loaded", Toast.LENGTH_LONG).show()
        })
    }

    private fun onSaveButtonClick(v : View) {
        v.findViewById<Button>(R.id.saveButton).setOnClickListener {
            trivia.question = questionEditText.text.toString()
            trivia.answer = answerEditText.text.toString()
            (requireActivity() as MainActivity).editTrivia(trivia, position)
        }
    }
}