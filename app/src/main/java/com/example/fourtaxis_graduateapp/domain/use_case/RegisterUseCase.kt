package com.example.fourtaxis_graduateapp.domain.use_case

import com.example.fourtaxis_graduateapp.Resource
import com.example.fourtaxis_graduateapp.domain.repository.FirebaseRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val repository: FirebaseRepository
) {
    suspend operator fun invoke(email: String, password: String): Flow<Resource<Boolean>> {
        return repository.registerUser(email, password)
    }
}