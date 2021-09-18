package fiix.challenge.fiixexercise.kotlinsample.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import fiix.challenge.fiixexercise.R
import fiix.challenge.fiixexercise.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Inflate layout through view binding available as a part of data binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpActionBar()
    }

    // Handles back navigation in the detail fragment
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    // Set up action bar
    private fun setUpActionBar() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }
}
