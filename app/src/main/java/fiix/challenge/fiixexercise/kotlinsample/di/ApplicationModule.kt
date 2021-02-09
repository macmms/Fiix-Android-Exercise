package fiix.challenge.fiixexercise.kotlinsample.di

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
class ApplicationModule {

    @Provides
    @Singleton
    fun provideSharedPreferences(
            @ApplicationContext context: Context
    ): SharedPreferences {
        return context.getSharedPreferences("default", Context.MODE_PRIVATE)
    }
}
