package fiix.challenge.fiixexercise.di

import fiix.challenge.fiixexercise.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val ViewModelModule = module {

    viewModel {
        MainViewModel(get())
    }
}