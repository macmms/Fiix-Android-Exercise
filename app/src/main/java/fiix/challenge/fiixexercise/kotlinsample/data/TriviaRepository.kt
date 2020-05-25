package fiix.challenge.fiixexercise.kotlinsample.data

import android.annotation.SuppressLint
import android.util.Log
import fiix.challenge.fiixexercise.dp.DataProcessor
import fiix.challenge.fiixexercise.kotlinsample.data.db.TriviaDatabase
import fiix.challenge.fiixexercise.kotlinsample.data.db.trivia.DefaultTriviaProvider
import fiix.challenge.fiixexercise.kotlinsample.data.db.trivia.Trivia
import io.reactivex.Observable
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

    @SuppressLint("CheckResult")
    fun updateTrivia(trivia: Trivia) {
        Observable.just(triviaDatabase.triviaDao())
                .observeOn(Schedulers.io())
                .subscribe { dao ->
                    dao.update(trivia)
                }
    }

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

        return Single.zip(questionsCall, answersCall, BiFunction<List<Trivia>, List<String>, List<Trivia>> { trivia, answers ->
            if (trivia.size != answers.size) {
                Log.e("TriviaRepository", "trivia size:${trivia.size} != answers' size:${answers.size} ")
                return@BiFunction emptyList<Trivia>()
            }
            return@BiFunction updateTriviaWithAnswers(trivia, answers)
        }).subscribeOn(Schedulers.io()).flatMap { updatedTrivia ->
            if (updatedTrivia.isEmpty()) {
                return@flatMap Single.just(updatedTrivia)
            }
            return@flatMap triviaDatabase.triviaDao()
                    .insert(updatedTrivia)
                    .flatMap {
                        triviaDatabase.triviaDao().getAllTrivia()
                    }
        }
    }

    /**
     * This function updates the trivia with correct answers and returns the new list.
     * */
    private fun updateTriviaWithAnswers(trivia: List<Trivia>, answers: List<String>): List<Trivia> {
        val updatedTrivia = arrayListOf<Trivia>()
        for (i in trivia.indices) {
            updatedTrivia.add(trivia[i].copy(answer = answers[i]))
        }
        return updatedTrivia
    }

}