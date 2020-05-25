package fiix.challenge.fiixexercise.ui.trivialist

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import fiix.challenge.fiixexercise.R
import fiix.challenge.fiixexercise.ui.ViewModelFactory
import fiix.challenge.fiixexercise.ui.trivialist.adapter.TriviaListAdapter
import kotlinx.android.synthetic.main.trivia_list_fragment.*

class TriviaListFragment : Fragment() {

    companion object {
        fun newInstance() = TriviaListFragment()
    }

    private val viewModel: TriviaListViewModel by viewModels {
        ViewModelFactory.provideTriviaListViewModelFactory(requireContext())
    }

    private var listener: OnTriviaQuestionSelectedListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as? OnTriviaQuestionSelectedListener

        if (listener == null) {
            throw ClassCastException("Activity must implement OnTriviaQuestionSelectedListener")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.trivia_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val adapter = TriviaListAdapter(viewModel)
        trivia_list.adapter = adapter
        viewModel.triviaListLiveData.observe(viewLifecycleOwner, Observer { triviaList ->
            triviaList?.let {
                adapter.setQuestionList(it)
            }
        })
        viewModel.listener = listener
        viewModel.loadTriviaList()

        toolbar.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.action_reset -> {
                    viewModel.loadTriviaList()
                    true
                }
                else -> false
            }
        }

    }

}
