package com.example.fourtaxis_graduateapp.di

import androidx.lifecycle.ViewModel
import com.example.fourtaxis_graduateapp.presentation.login_register.LoginRegisterViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(LoginRegisterViewModel::class)
    fun bindLoginRegisterViewModel(viewModel: LoginRegisterViewModel): ViewModel
}