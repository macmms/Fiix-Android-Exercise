package fiix.challenge.fiixexercise.kotlinsample.hilt

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import fiix.challenge.fiixexercise.dp.DataSource
import fiix.challenge.fiixexercise.kotlinsample.repository.LocalDataSource

@InstallIn(ViewModelComponent::class)
@Module
abstract class DataSourceModule {

    @Binds
    abstract fun bindDataSource(impl: LocalDataSource): DataSource
}