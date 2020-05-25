package fiix.challenge.fiixexercise.kotlinsample.util

import android.view.View

/**
 * Extension functions for the View classes for convenience
 */
fun View.hide() {
    visibility = View.GONE
}

fun View.show() {
    visibility = View.VISIBLE
}