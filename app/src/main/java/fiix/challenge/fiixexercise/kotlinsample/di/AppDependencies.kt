package fiix.challenge.fiixexercise.kotlinsample.di

import DataSourceServiceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import fiix.challenge.fiixexercise.kotlinsample.buisness.datasource.cache.abstraction.DataSourceService
import fiix.challenge.fiixexercise.kotlinsample.framework.datasource.cache.implementation.LocalDataSourceImpl
import fiix.challenge.fiixexercise.kotlinsample.framework.datasource.cache.MockRepo
import fiix.challenge.fiixexercise.kotlinsample.framework.datasource.cache.abstraction.DataSource
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppDependencies {

    @Provides
    @Singleton
    fun provideLocalDataSource(mockRepo: MockRepo): DataSource {
        return LocalDataSourceImpl(mockRepo)
    }

    @Provides
    fun provideMockRepo(): MockRepo {
        return MockRepo()
    }

    @Provides
    @Singleton
    fun provideLocalDataSourceService(dataSource: DataSource):DataSourceService{
        return DataSourceServiceImpl(dataSource)
    }
}