package fiix.challenge.fiixexercise.kotlinsample.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import fiix.challenge.fiixexercise.R
import fiix.challenge.fiixexercise.kotlinsample.ui.screens.TriviaQuestionDetailScreen
import fiix.challenge.fiixexercise.kotlinsample.ui.screens.TriviaQuestionListScreen
import fiix.challenge.fiixexercise.kotlinsample.viewmodel.TriviaViewModel

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<TriviaViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                TriviaQuestionApp(triviaQuestionViewModel = viewModel)
            }
        }
        supportActionBar?.hide()
    }
}

@Composable
fun AppTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colors = MaterialTheme.colors.copy(
            primary = Color(ContextCompat.getColor(LocalContext.current, R.color.colorPrimary)),
            primaryVariant = Color(
                ContextCompat.getColor(
                    LocalContext.current,
                    R.color.colorPrimaryDark
                )
            ),
            secondary = Color(ContextCompat.getColor(LocalContext.current, R.color.colorAccent))
        ),
        content = content
    )
}

@Composable
fun TriviaQuestionApp(triviaQuestionViewModel: TriviaViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "trivia_list") {
        composable("trivia_list") {
            TriviaQuestionListScreen(triviaQuestionViewModel, navController)
        }
        composable("trivia_detail/{triviaId}") {
            TriviaQuestionDetailScreen(
                triviaQuestionViewModel,
                it.arguments?.getString("triviaId"),
                navController
            )
        }
    }
}