package fiix.challenge.fiixexercise.kotlinsample.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import fiix.challenge.fiixexercise.dp.DataProcessor
import fiix.challenge.fiixexercise.kotlinsample.SeedData
import fiix.challenge.fiixexercise.kotlinsample.db.AppDatabase
import fiix.challenge.fiixexercise.kotlinsample.db.dao.QuestionDao
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getDatabase(context)
    }

    @Provides
    fun provideQuestionDao(appDatabase: AppDatabase): QuestionDao {
        return appDatabase.questionDao()
    }

    @Provides
    fun provideDataSource(seedData: SeedData): DataProcessor {
        return DataProcessor(seedData)
    }

    @Provides
    fun provideSeedData(): SeedData {
        return SeedData()
    }
}
