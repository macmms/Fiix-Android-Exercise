package fiix.challenge.fiixexercise.kotlinsample.data

import android.util.Log
import fiix.challenge.fiixexercise.dp.DataProcessor
import fiix.challenge.fiixexercise.kotlinsample.data.db.TriviaDatabase
import fiix.challenge.fiixexercise.kotlinsample.data.db.trivia.DefaultTriviaProvider
import fiix.challenge.fiixexercise.kotlinsample.data.db.trivia.Trivia
import io.reactivex.Single
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers

/**
 * This repo is responsible for providing and updating data
 */
class TriviaRepository(
        private val triviaDatabase: TriviaDatabase,
        private val dataProcessor: DataProcessor
) {

    /**
     * Attempts to retrieve trivia from table and populates it if table is empty
     * */
    fun getTrivia(): Single<List<Trivia>> {
        return triviaDatabase.triviaDao().getAllTrivia()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .flatMap { triviaFromDb ->
                    if (!triviaFromDb.isNullOrEmpty()) {
                        return@flatMap Single.just(triviaFromDb)
                    }
                    return@flatMap populateTriviaInDb()
                }
    }

    /**
     * Populates trivia table with default questions and answers
     * @return The Trivia in the db
     * */
    private fun populateTriviaInDb(): Single<List<Trivia>> {
        val questionsCall = Single.just(DefaultTriviaProvider.getTrivia()).subscribeOn(Schedulers.io())
        val answersCall = Single.just(dataProcessor.getAnswers()).subscribeOn(Schedulers.io())

        return Single.zip(questionsCall, answersCall, BiFunction<List<Trivia>, List<String>, Unit> { trivia, answers ->
            return@BiFunction buildAndInsertTriviaIntoDb(trivia, answers)
        }).subscribeOn(Schedulers.io()).flatMap {
            return@flatMap triviaDatabase.triviaDao().getAllTrivia() //todo: return an error object
        }
    }

    /**
     * This function updates the trivia with correct answers and inserts them into db.
     * */
    private fun buildAndInsertTriviaIntoDb(trivia: List<Trivia>, answers: List<String>) {
        if (trivia.size != answers.size) {
            Log.e("TriviaRepository", "trivia size:${trivia.size} != answers' size:${answers.size} ")
            return
        }
        for (i in trivia.indices) {
            trivia[i].answer = answers[i]
        }
        triviaDatabase.triviaDao().insert(trivia)
    }

}