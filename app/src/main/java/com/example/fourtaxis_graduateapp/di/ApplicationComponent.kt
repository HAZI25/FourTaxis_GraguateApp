package com.example.fourtaxis_graduateapp.di

import com.example.fourtaxis_graduateapp.presentation.MainActivity
import com.example.fourtaxis_graduateapp.presentation.login_register.LoginFragment
import com.example.fourtaxis_graduateapp.presentation.login_register.RegisterFragment
import dagger.Component

@Component(modules = [ViewModelModule::class, FirebaseModule::class, DataModule::class])
interface ApplicationComponent {

    fun inject(fragment: LoginFragment)
    fun inject(fragment: RegisterFragment)
    fun inject(activity: MainActivity)
}