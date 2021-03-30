package fiix.challenge.fiixexercise.kotlinsample.framework.datasource.cache.abstraction

import fiix.challenge.fiixexercise.kotlinsample.buisness.domain.TriviaQuestion

interface DataSource {
        fun getData(): List<String>
        fun getQuestion(): List<TriviaQuestion>
}