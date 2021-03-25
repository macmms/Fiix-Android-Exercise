package fiix.challenge.fiixexercise

import fiix.challenge.fiixexercise.dp.DataSource
import fiix.challenge.fiixexercise.model.TriviaQuestion
import fiix.challenge.fiixexercise.repositories.MockRepo

class MockDataSource(private val mockRepo: MockRepo): DataSource {

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
        return mockRepo.triviaQuestions
    }
}