package com.example.triviaquizapp.Util


import fiix.challenge.fiixexercise.javasample.Listners.DataSource
import fiix.challenge.fiixexercise.javasample.Model.TriviaQuestion
import kotlinx.coroutines.*
import kotlin.random.Random


class DataProcessor(private val source: DataSource) {

    //DO NOT MODIFY
    val scope = CoroutineScope(Dispatchers.Default)
    //DO NOT MODIFY
    val delayModifier = Random.nextLong(2, 30)


    fun getQustionAnswers(): List<TriviaQuestion> {
        return runBlocking {
            processDataAsync().await()
        }
    }

    //DO NOT MODIFY THIS FUNCTION
    private suspend fun processDataAsync()= scope.async {
        delay(1000 * delayModifier)
        source.getData()
    }

}


