package fiix.challenge.fiixexercise.kotlinsample.framework.datasource.cache.implementation

import fiix.challenge.fiixexercise.kotlinsample.buisness.domain.TriviaQuestion
import fiix.challenge.fiixexercise.kotlinsample.framework.datasource.cache.MockRepo
import fiix.challenge.fiixexercise.kotlinsample.framework.datasource.cache.abstraction.DataSource

class LocalDataSourceImpl (private val mMockRepo : MockRepo) : DataSource{

    override fun getData(): List<String> {
        return listOf(
                "7",
                "Dr. Seuss",
                "A Study in Scarlet",
                "88",
                "1986",
                "My Neighbor Totoro",
                "True",
                "San Fransokyo",
                "Camp Crystal Lake",
                "32",
                "10",
                "Crete"
        )
    }

    override fun getQuestion(): List<TriviaQuestion> {
        return mMockRepo.triviaQuestions
    }

}