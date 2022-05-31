package com.example.fourtaxis_graduateapp.domain.repository

import com.example.fourtaxis_graduateapp.Resource
import kotlinx.coroutines.flow.Flow

interface FirebaseRepository {

    suspend fun registerUser(email: String, password: String): Flow<Resource<Boolean>>

    suspend fun login(email: String, password: String): Flow<Resource<Boolean>>
}