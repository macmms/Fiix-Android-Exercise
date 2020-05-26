package fiix.challenge.fiixexercise.kotlinsample.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

/**
 * Base fragment to have common object and behaviours for List and Edit Fragments.
 */
abstract class BaseFragment : Fragment() {

    protected lateinit var viewModel: TriviaScreensViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(TriviaScreensViewModel::class.java)
    }
}