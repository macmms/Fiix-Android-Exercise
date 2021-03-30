import fiix.challenge.fiixexercise.kotlinsample.buisness.datasource.cache.abstraction.DataSourceService
import fiix.challenge.fiixexercise.kotlinsample.buisness.domain.TriviaQuestion
import fiix.challenge.fiixexercise.kotlinsample.framework.datasource.cache.abstraction.DataSource

class DataSourceServiceImpl (val dataSource: DataSource) : DataSourceService {
    override fun getData(): List<String> {
        return dataSource.getData()
    }

    override fun getQuestion(): List<TriviaQuestion> {
        return dataSource.getQuestion()
    }
}