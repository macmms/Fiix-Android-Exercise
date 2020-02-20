package fiix.challenge.fiixexercise.kotlinsample.ui.fragments.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider

import fiix.challenge.fiixexercise.R
import fiix.challenge.fiixexercise.databinding.HomeFragmentBinding
import kotlinx.android.synthetic.main.home_fragment.*

class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Get a reference to the binding object and inflate the fragment views.
        val binding: HomeFragmentBinding = DataBindingUtil.inflate(
                inflater, R.layout.home_fragment, container, false)

        // Create an instance of view model factory
        val viewModelFactory=HomeViewModelFactory()

        // Get reference of the viewModel attached to this fragment
        viewModel = ViewModelProvider(this,viewModelFactory)
                .get(HomeViewModel::class.java)

        // To use the View Model with data binding give the binding object a reference to it.
        binding.homeViewModel = viewModel

        //Lets bind adapter to our recycler view
        val adapter= HomeAdapter()
        binding.recyclerQuestions.adapter=adapter

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

}
