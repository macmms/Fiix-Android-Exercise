package fiix.challenge.fiixexercise.kotlinsample.ui.activities

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import fiix.challenge.fiixexercise.R

open class BaseActivity : AppCompatActivity() {

    private val fragmentManager = supportFragmentManager

    fun addFragment(fragment: Fragment) {
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainer, fragment)
        fragmentTransaction.commit()
    }

    fun replaceFragment(newFragment: Fragment) {
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainer, newFragment)
        fragmentTransaction.addToBackStack("")
        fragmentTransaction.commit()
    }
}