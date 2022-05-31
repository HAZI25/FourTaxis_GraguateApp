package com.example.fourtaxis_graduateapp.di

import com.example.fourtaxis_graduateapp.data.repository.FirebaseRepositoryImpl
import com.example.fourtaxis_graduateapp.domain.repository.FirebaseRepository
import dagger.Binds
import dagger.Module

@Module
interface DataModule {

    @Binds
    fun bindFirebaseRepository(impl: FirebaseRepositoryImpl): FirebaseRepository

}