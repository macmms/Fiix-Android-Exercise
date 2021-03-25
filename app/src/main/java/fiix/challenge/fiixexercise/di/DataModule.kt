package fiix.challenge.fiixexercise.di

import fiix.challenge.fiixexercise.dp.BaseDataProcessor
import fiix.challenge.fiixexercise.dp.DataProcessor
import fiix.challenge.fiixexercise.dp.DataSource
import fiix.challenge.fiixexercise.repositories.LocalDataSource
import fiix.challenge.fiixexercise.repositories.MockRepo
import org.koin.dsl.module

val DataModule = module{
    single {
        DataProcessor(get())
    }

    single<DataSource>{
        return@single LocalDataSource(get())
    }

    single {
        MockRepo()
    }

    single<BaseDataProcessor> {
        DataProcessor(get())
    }
}