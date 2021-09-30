package fiix.challenge.fiixexercise.kotlinsample.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.navigation.NavController
import fiix.challenge.fiixexercise.R
import fiix.challenge.fiixexercise.kotlinsample.viewmodel.TriviaViewModel

@Composable
fun TriviaQuestionDetailScreen(
    triviaQuestionViewModel: TriviaViewModel,
    triviaId: String?,
    navController: NavController
) {
    Column {
        TopAppBar(title = { Text("Trivia Questions") },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = "Close button",
                        tint = Color.White,
                    )
                }
            }
        )
        triviaQuestionViewModel.getTriviaQuestionById(triviaId)?.let { _triviaQuestion ->
            var questionText by remember { mutableStateOf(_triviaQuestion.question) }
            var answerText by remember { mutableStateOf(_triviaQuestion.answer.orEmpty()) }
            val focusRequester = remember { FocusRequester() }
            fun saveTriviaQuestion() {
                triviaQuestionViewModel.saveQuestion(
                    _triviaQuestion.id,
                    questionText,
                    answerText
                )
                navController.popBackStack()
            }
            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                Column(
                    Modifier.padding(dimensionResource(id = R.dimen.large_padding)),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    OutlinedTextField(
                        value = questionText,
                        onValueChange = { questionText = it },
                        Modifier.padding(vertical = dimensionResource(id = R.dimen.extra_small_padding)),
                        label = { Text(stringResource(id = R.string.trivia_question_label)) },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                        keyboardActions = KeyboardActions(onNext = { focusRequester.requestFocus() })
                    )
                    OutlinedTextField(
                        value = answerText,
                        onValueChange = { answerText = it },
                        modifier = Modifier
                            .padding(vertical = dimensionResource(id = R.dimen.extra_small_padding))
                            .focusRequester(focusRequester),
                        label = { Text(stringResource(id = R.string.trivia_answer_label)) },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                        keyboardActions = KeyboardActions(onDone = { saveTriviaQuestion() })
                    )
                    Button(content = { Text(stringResource(id = R.string.save)) },
                        modifier = Modifier.padding(dimensionResource(id = R.dimen.medium_padding)),
                        onClick = { saveTriviaQuestion() }
                    )
                }
            }
        } ?: run {
            Toast.makeText(
                LocalContext.current,
                stringResource(id = R.string.trivia_detail_not_found),
                Toast.LENGTH_LONG
            )
        }
    }
}
