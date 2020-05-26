package fiix.challenge.fiixexercise.kotlinsample.ui

import androidx.fragment.app.Fragment

/**
 * Base fragment to have common things
 */
abstract class BaseFragment : Fragment() {

    protected lateinit var hostCallback: HostCallback


    interface HostCallback {
        fun getViewModel(): TriviaScreensViewModel
    }
}