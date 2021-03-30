package fiix.challenge.fiixexercise.kotlinsample.buisness.datasource.cache.abstraction

import fiix.challenge.fiixexercise.kotlinsample.buisness.domain.TriviaQuestion

interface DataSourceService {

    fun getData(): List<String>
    fun getQuestion(): List<TriviaQuestion>
}