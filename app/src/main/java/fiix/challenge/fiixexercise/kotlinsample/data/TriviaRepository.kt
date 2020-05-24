package fiix.challenge.fiixexercise.kotlinsample.data

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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
    //todo: figure out update item and observe change for singleItem

    val triviaLiveData = MutableLiveData<List<Trivia>>()

    fun getTriviaLiveData(): LiveData<List<Trivia>> {
        return triviaLiveData
    }

    /**
     * Attempts to retrieve trivia from table and populates it if table is empty
     * */
    @SuppressLint("CheckResult")
    fun fetchTrivia() {
        Single.just(triviaDatabase.triviaDao())
                .observeOn(Schedulers.io())
                .subscribe { dao ->
                    val triviaFromDb = dao.getAllTrivia()
                    if (triviaFromDb.isNullOrEmpty()) {
                        populateTriviaInDb()
                    } else {
                        triviaLiveData.postValue(triviaFromDb)
                    }
                }
    }

    /**
     * Populates trivia table with default questions and answers
     * */
    @SuppressLint("CheckResult")
    private fun populateTriviaInDb() {
        val questionsCall = Single.just(DefaultTriviaProvider.getTrivia()).subscribeOn(Schedulers.io())
        val answersCall = Single.just(dataProcessor.getAnswers()).subscribeOn(Schedulers.io())

        Single.zip(questionsCall, answersCall, BiFunction<List<Trivia>, List<String>, List<Trivia>> { trivia, answers ->
            if (trivia.size != answers.size) {
                Log.e("TriviaRepository", "trivia size:${trivia.size} != answers' size:${answers.size} ")
                return@BiFunction emptyList<Trivia>() //todo: make me an error type instead ?
            } else {
                for (i in trivia.indices) {
                    trivia[i].answer = answers[i]
                }
                triviaDatabase.triviaDao().insert(trivia)
                return@BiFunction triviaDatabase.triviaDao().getAllTrivia()
            }
        }).subscribeOn(Schedulers.io()).subscribe { questionsFromDb ->
            triviaLiveData.postValue(questionsFromDb)
        }
    }

}