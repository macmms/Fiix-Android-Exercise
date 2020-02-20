package fiix.challenge.fiixexercise.kotlinsample.ui.fragments.home

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import fiix.challenge.fiixexercise.R
import fiix.challenge.fiixexercise.databinding.HomeFragmentBinding
import fiix.challenge.fiixexercise.dp.DataProcessor
import fiix.challenge.fiixexercise.kotlinsample.LocalDataSource
import fiix.challenge.fiixexercise.kotlinsample.ui.MainActivityViewModel
import fiix.challenge.fiixexercise.kotlinsample.ui.MainViewModelFactory
import kotlinx.android.synthetic.main.home_fragment.*

class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var adapter: HomeAdapter
    private lateinit var activityViewModel: MainActivityViewModel
    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Get a reference to the binding object and inflate the fragment views.
        val binding: HomeFragmentBinding = DataBindingUtil.inflate(
                inflater, R.layout.home_fragment, container, false)

        // Create an instance of view model factory
        val viewModelFactory = HomeViewModelFactory()

        // Get reference of the viewModel attached to this fragment
        viewModel = ViewModelProvider(this, viewModelFactory)
                .get(HomeViewModel::class.java)

        //Get Reference of MainActivity ViewModel
        val factory = MainViewModelFactory(DataProcessor(LocalDataSource()))
         activityViewModel = activity.run {
            ViewModelProvider(this!!.viewModelStore, factory)
                    .get(MainActivityViewModel::class.java)
        }

        // To use the View Model with data binding give the binding object a reference to it.
        binding.homeViewModel = viewModel


        //Lets bind adapter to our recycler view
         adapter = HomeAdapter(object : HomeListener {
            override fun onClick(index: Int) {
//                Toast.makeText(context, "hello click", Toast.LENGTH_LONG).show()
                activityViewModel.selectedItemIndex=index
                viewModel.listItemClicked(index)

            }

            override fun showHideAnswer(index: Int) {
                adapter.showHideAnswer(index)
            }
        })
        binding.recyclerQuestions.itemAnimator=null
        binding.recyclerQuestions.adapter = adapter


        //Observe for any changes in navigation variables
        viewModel.navigateToEditQuestion.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                this.findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToEditQuestionFragment())

                viewModel.doneNavigating()
            }
        })

        activityViewModel.isLoadingData.observe(viewLifecycleOwner, Observer {
            if(it==true){
                progressHome.visibility=View.VISIBLE
                recyclerQuestions.visibility=View.GONE
            }else{
                progressHome.visibility=View.GONE
                recyclerQuestions.visibility=View.VISIBLE
            }
        })

        activityViewModel.triviaList.observe(viewLifecycleOwner, Observer {
            if(it.isNotEmpty()){
                adapter.data=it
            }
        })

        activityViewModel.isQuestionEdited.observe(viewLifecycleOwner, Observer {
            if(it==true){
                adapter.notifyItemChanged(activityViewModel.selectedItemIndex)
                activityViewModel.isQuestionEdited.value=false
            }
        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activityViewModel.prepareTriviaList()
    }


    override fun onResume() {
        super.onResume()
        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
        (requireActivity() as AppCompatActivity).supportActionBar?.title = getString(R.string.app_name)

        // Hide keyboard
        val inputMethodManager = context?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view?.windowToken, 0)
    }


}
