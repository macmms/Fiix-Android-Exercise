package fiix.challenge.solution.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import fiix.challenge.fiixexercise.R
import fiix.challenge.solution.models.TriviaQuestion
import fiix.challenge.solution.viewmodels.TriviaViewModel
import kotlinx.android.synthetic.main.question_detail_fragment.*

class DetailViewFragment : Fragment() {
    lateinit var triviaItem: TriviaQuestion

    lateinit var triviaViewModel: TriviaViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        triviaViewModel = activity?.run {
            ViewModelProviders.of(this).get(TriviaViewModel::class.java)
        } ?: throw Exception("Activity not found")

        return inflater.inflate(R.layout.question_detail_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        triviaItem = triviaViewModel.currentQuestion.value!!

        questionTextView.text = triviaItem.question
        answer.text = triviaItem.answer
    }
}