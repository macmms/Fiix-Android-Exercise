package fiix.challenge.fiixexercise.kotlinsample.framework.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import fiix.challenge.fiixexercise.R
import fiix.challenge.fiixexercise.databinding.FragmentHomeBinding
import fiix.challenge.fiixexercise.kotlinsample.buisness.domain.TriviaQuestion
import fiix.challenge.fiixexercise.kotlinsample.framework.presentation.viewmodule.HomeViewModel
import fiix.challenge.fiixexercise.kotlinsample.utils.ItemClickEventHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment
    : Fragment(), ItemClickEventHandler {

    private lateinit var mBinding: FragmentHomeBinding
    private lateinit var mQuestionAdapter: QuestionAdapter
    private lateinit var mTriviaQuestionList: List<TriviaQuestion>
    private val mHomeViewModel: HomeViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, @Nullable container: ViewGroup?, @Nullable savedInstanceState: Bundle?): View? {
        mBinding = FragmentHomeBinding.inflate(layoutInflater)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindAdapter()
        subscribeObservers()
    }

    private fun bindAdapter() {
        mQuestionAdapter = QuestionAdapter(this, ArrayList())
        mBinding.recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        mBinding.recyclerView.adapter = mQuestionAdapter
    }

    private fun subscribeObservers() {
        mTriviaQuestionList = ArrayList()

        CoroutineScope(Dispatchers.IO).launch{
            launch {
                mHomeViewModel.bindQuestionAndAnswer()
            }
        }

        mHomeViewModel.triviaQuestionList.observe(viewLifecycleOwner, Observer {
            mTriviaQuestionList = it
            mQuestionAdapter.setData(mTriviaQuestionList)
            mBinding.progressbar.visibility = View.GONE
            mBinding.recyclerView.visibility = View.VISIBLE
        })


    }

    override fun itemClick(position: Int) {
        mHomeViewModel.setPosition(position)
        Navigation.findNavController(mBinding.root).navigate(R.id.action_homeFragment_to_detailedFragment2).apply { };
    }

}