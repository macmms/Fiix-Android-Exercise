package fiix.challenge.fiixexercise.kotlinsample.ui.views

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import fiix.challenge.fiixexercise.R
import fiix.challenge.fiixexercise.kotlinsample.model.TriviaQuestion

@Composable
fun TriviaQuestionItemView(triviaQuestion: TriviaQuestion, navController: NavController) {
    var isRevealed by remember { mutableStateOf(false) }
    Card(
        Modifier
            .padding(dimensionResource(id = R.dimen.extra_small_padding))
            .clickable {
                navController.navigate("trivia_detail/${triviaQuestion.id}")
            }
    ) {
        Column {
            Row(Modifier.padding(dimensionResource(R.dimen.medium_padding))) {
                Text(
                    triviaQuestion.question,
                    modifier = Modifier.padding(dimensionResource(id = R.dimen.extra_small_padding))
                )
            }
            Row(Modifier.padding(dimensionResource(R.dimen.medium_padding))) {
                Crossfade(targetState = isRevealed) {
                    Row(
                        modifier = Modifier
                            .defaultMinSize(minHeight = dimensionResource(id = R.dimen.trivia_answer_size)),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        if (it) {
                            Text(
                                text = triviaQuestion.answer.orEmpty(),
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .fillMaxWidth()
                            )
                        } else {
                            Button(modifier = Modifier.fillMaxSize(),
                                onClick = { isRevealed = true }
                            ) {
                                Text(
                                    text = stringResource(R.string.trivia_answer_button_text),
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}