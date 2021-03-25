package fiix.challenge.fiixexercise

import fiix.challenge.fiixexercise.dp.BaseDataProcessor
import fiix.challenge.fiixexercise.model.TriviaQuestion

class MockDataProcessor(private val mockDataSource: MockDataSource):BaseDataProcessor{
    override fun getAnswers(): List<String> {
        return mockDataSource.getData()
    }

    override fun getQuestions(): List<TriviaQuestion> {
       return mockDataSource.getQuestion()
    }
}