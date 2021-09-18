package fiix.challenge.fiixexercise.kotlinsample.repository

import dagger.hilt.android.scopes.ViewModelScoped
import fiix.challenge.fiixexercise.dp.DataProcessor
import fiix.challenge.fiixexercise.kotlinsample.model.TriviaQuestionUiModel
import javax.inject.Inject

@ViewModelScoped
class MockRepo @Inject constructor(private val dp: DataProcessor) {

    val questions: List<TriviaQuestionUiModel> = dp.questionsWithoutAnswers.map {
        /*
        The repository is responsible for transforming the network model to Ui models.
        In this case, this is just a dummy implementation
        */
        TriviaQuestionUiModel(it.question, it.answer)
    }

    suspend fun getQuestionsWithAnswers(): List<TriviaQuestionUiModel> {
        val answers = dp.getAnswers()
        return dp.questionsWithoutAnswers.mapIndexed { index, triviaQuestion ->
            /*
            The repository is responsible for transforming the network model to Ui models.
            In this case, this is just a dummy implementation
            */
            TriviaQuestionUiModel(triviaQuestion.question, answers[index], index)
        }
    }
}