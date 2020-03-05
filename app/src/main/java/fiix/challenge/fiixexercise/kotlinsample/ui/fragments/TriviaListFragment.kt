package fiix.challenge.fiixexercise.kotlinsample.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fiix.challenge.fiixexercise.R
import fiix.challenge.fiixexercise.kotlinsample.db.model.TriviaQuestion
import fiix.challenge.fiixexercise.kotlinsample.ui.adapters.TriviaAdapter
import fiix.challenge.fiixexercise.kotlinsample.ui.interfaces.OnClickListener
import fiix.challenge.fiixexercise.kotlinsample.ui.interfaces.QuestionActionListener
import fiix.challenge.fiixexercise.kotlinsample.ui.viewmodels.TriviaViewModel
import fiix.challenge.fiixexercise.kotlinsample.utilities.Injector

class TriviaListFragment : Fragment(), QuestionActionListener {

    private val adapter = TriviaAdapter(this)
    private lateinit var triviaViewModel: TriviaViewModel
    private lateinit var onClickListener: OnClickListener


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_trivia_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi(view)
    }

    fun initUi(view: View) {

        val factory = Injector.provideTriviaViewModelFactory(requireContext())
        triviaViewModel = ViewModelProviders.of(requireActivity(), factory)
                .get(TriviaViewModel::class.java)

        triviaViewModel.getTriviaQuestions().observe(viewLifecycleOwner, Observer { triviaList ->
            if (triviaList.first().answer == null) {
                triviaViewModel.insertTriviaQuestions(triviaList)
            }

            adapter.setNotes(triviaList)
        })

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter
    }

    override fun onAnswerClicked(question: TriviaQuestion) {
        triviaViewModel.updateAnswerRevealed(question.id, true)
    }

    override fun onQuestionEditClicked(questionId: Int) {
        if (onClickListener != null) {
            onClickListener.onClick(questionId);
        }
    }

    fun setOnClickListener(onClickListener: OnClickListener) {
        this.onClickListener = onClickListener;
    }
}