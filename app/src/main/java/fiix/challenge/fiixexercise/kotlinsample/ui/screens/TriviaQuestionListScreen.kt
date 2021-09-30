package fiix.challenge.fiixexercise.kotlinsample.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import fiix.challenge.fiixexercise.R
import fiix.challenge.fiixexercise.kotlinsample.ui.views.TriviaQuestionItemView
import fiix.challenge.fiixexercise.kotlinsample.viewmodel.TriviaViewModel

@Composable
fun TriviaQuestionListScreen(
    triviaQuestionViewModel: TriviaViewModel,
    navController: NavController
) {
    Column {
        TopAppBar(title = { Text("Trivia Questions") })
        val triviaQuestions = triviaQuestionViewModel.triviaQuestions.collectAsState()
        if (triviaQuestions.value.isEmpty()) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    CircularProgressIndicator(strokeWidth = dimensionResource(id = R.dimen.progress_indicator_stroke_width))
                    Text(
                        stringResource(id = R.string.trivia_loading), Modifier.padding(
                            top = dimensionResource(id = R.dimen.extra_small_padding)
                        )
                    )
                }
            }
        } else {
            Column(
                Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(dimensionResource(id = R.dimen.medium_padding))) {
                triviaQuestions.value.forEach {
                    TriviaQuestionItemView(triviaQuestion = it, navController = navController)
                }
            }
        }
    }
}