package com.example.fourtaxis_graduateapp.data.repository

import com.example.fourtaxis_graduateapp.Resource
import com.example.fourtaxis_graduateapp.domain.repository.FirebaseRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : FirebaseRepository {

    override suspend fun registerUser(email: String, password: String) = flow {
        try {
            emit(Resource.Loading())
            firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            emit(Resource.Success(true))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Error"))
        }
    }

    override suspend fun login(email: String, password: String) = flow {
        try {
            emit(Resource.Loading())
            firebaseAuth.signInWithEmailAndPassword(email, password).await()
            emit(Resource.Success(true))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Error"))
        }
    }
}