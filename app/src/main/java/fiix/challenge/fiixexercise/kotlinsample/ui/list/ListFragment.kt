package fiix.challenge.fiixexercise.kotlinsample.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import fiix.challenge.fiixexercise.R
import fiix.challenge.fiixexercise.kotlinsample.data.db.trivia.Trivia
import fiix.challenge.fiixexercise.kotlinsample.ui.BaseFragment
import fiix.challenge.fiixexercise.kotlinsample.ui.list.adapter.TriviaListAdapter
import kotlinx.android.synthetic.main.fragment_list.*

/**
 * Fragment for the list screen
 */
class ListFragment : BaseFragment() {

    companion object {
        fun newInstance(hostCallback: HostCallback): ListFragment {
            return ListFragment().apply {
                this.hostCallback = hostCallback
            }
        }
    }


    private val listAdapter = TriviaListAdapter(object : TriviaListAdapter.Listener {
        override fun onTriviaClicked(trivia: Trivia) {
            hostCallback.getViewModel().setTriviaToEdit(trivia)
        }

        override fun onRevealAnswerClicked(trivia: Trivia) {
            hostCallback.getViewModel().revealTriviaAnswer(trivia)
        }
    })

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initData()
    }

    private fun initViews() {
        list_screen_recyclerview.adapter = listAdapter
    }

    private fun initData() {
        val viewModel = hostCallback.getViewModel()
        viewModel.getAllTrivia().observe(viewLifecycleOwner, Observer { trivia ->
            listAdapter.submitList(trivia)
        })

    }

}