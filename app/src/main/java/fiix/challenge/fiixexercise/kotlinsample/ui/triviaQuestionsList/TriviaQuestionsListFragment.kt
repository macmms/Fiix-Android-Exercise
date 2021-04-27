package fiix.challenge.fiixexercise.kotlinsample.ui.triviaQuestionsList

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fiix.challenge.fiixexercise.R
import fiix.challenge.fiixexercise.kotlinsample.activities.main.MainActivityContract
import fiix.challenge.fiixexercise.kotlinsample.models.TriviaQuestion
import fiix.challenge.fiixexercise.kotlinsample.ui.triviaQuestionsList.adapters.TriviaQuestionsListAdapter
import fiix.challenge.fiixexercise.kotlinsample.viewmodels.TEViewModel

class TriviaQuestionsListFragment(@LayoutRes resId: Int): Fragment(resId), TriviaQuestionsListContract.View {

    private var listRecyclerView: RecyclerView? = null
    private var listAdapter: TriviaQuestionsListAdapter? = null

    private val teViewModel: TEViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        observeListData()
    }

    // region TriviaQuestionsListContract.View Methods
    override fun onAnswerClick(id: Int) {
        teViewModel.updateTriviaQuestion(id = id, showAnswer = true)
    }

    override fun onItemClick(id: Int) {
        teViewModel.selectTriviaQuestion(id = id)
        (activity as? MainActivityContract.Router)?.goToTriviaQuestionDetailsScreen()
    }
    // endregion

    // region Private Methods
    private fun initViews() {
        listRecyclerView = view?.findViewById(R.id.fe_trivia_questions_list_recycler_view)
    }

    private fun observeListData() {
        teViewModel.triviaQuestionsListData.observe(viewLifecycleOwner, { mList ->
            updateRecyclerView(dataList = mList)
        })
    }

    private fun updateRecyclerView(dataList: List<TriviaQuestion>) {
        if (listAdapter != null && listRecyclerView?.adapter != null){
            listAdapter?.updateData(newDataList = dataList)
        } else {
            listAdapter = TriviaQuestionsListAdapter(newDataList = dataList, listener = this)
            listRecyclerView?.layoutManager = LinearLayoutManager(requireContext())
            listRecyclerView?.adapter = listAdapter
        }
    }
    // endregion
}