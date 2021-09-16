package fiix.challenge.fiixexercise.kotlinsample.ui

import androidx.databinding.DataBindingUtil
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.snackbar.Snackbar
import fiix.challenge.fiixexercise.R
import fiix.challenge.fiixexercise.databinding.ActivityMainBinding
import fiix.challenge.fiixexercise.kotlinsample.viewModels.MainViewModel

class MainActivity : AppCompatActivity() {

    lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    private lateinit var snackbar: Snackbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.mainNavHostFragment) as NavHostFragment
        navController = navHostFragment.navController

        snackbar = Snackbar.make(binding.root.rootView, R.string.loading, Snackbar.LENGTH_INDEFINITE)
        snackbar.show()

        viewModel.triviaItems.observe(this, Observer { it -> if (it.size > 0) {
            snackbar.dismiss()
        }
        })


    }
}
