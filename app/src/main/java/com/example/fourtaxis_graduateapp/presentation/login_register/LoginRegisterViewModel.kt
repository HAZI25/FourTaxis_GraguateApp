package com.example.fourtaxis_graduateapp.presentation.login_register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fourtaxis_graduateapp.domain.use_case.LoginUseCase
import com.example.fourtaxis_graduateapp.domain.use_case.RegisterUseCase
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginRegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase,
    private val loginUseCase: LoginUseCase,
    private val firebaseAuth: FirebaseAuth
) : ViewModel() {

    private val _errorInputName = MutableLiveData<Boolean>()
    val errorInputName: LiveData<Boolean> get() = _errorInputName

    private val _errorInputPhone = MutableLiveData<Boolean>()
    val errorInputPhone: LiveData<Boolean> get() = _errorInputPhone

    private val _errorInputEmail = MutableLiveData<Boolean>()
    val errorInputEmail: LiveData<Boolean> get() = _errorInputEmail

    private val _errorInputPassword = MutableLiveData<Boolean>()
    val errorInputPassword: LiveData<Boolean> get() = _errorInputPassword

    private val _isUserAuthenticated = MutableLiveData<Boolean>()
    val isUserAuthenticated: LiveData<Boolean> get() = _isUserAuthenticated

    init {
        checkUserAuthorization()
    }

    private fun checkUserAuthorization() {
        _isUserAuthenticated.value = firebaseAuth.currentUser != null
    }

    fun registerUser(
        name: String, phone: String, email: String, password: String
    ) {
        val fieldsValid = validateFields(name, phone, email, password)
        if (fieldsValid)
            viewModelScope.launch {
                registerUseCase(email, password).collect {
                    when (it.data) {
                        true -> _isUserAuthenticated.value = true
                        else -> _isUserAuthenticated.value = false
                    }
                }
            }
    }

    fun login(email: String, password: String) {
        val fieldsValid = validateFields(email, password)
        if (fieldsValid)
            viewModelScope.launch {
                loginUseCase.invoke(email, password).collect {
                    when (it.data) {
                        true -> _isUserAuthenticated.value = true
                        else -> _isUserAuthenticated.value = false
                    }
                }
            }
    }

    private fun validateFields(email: String, password: String): Boolean {
        var result = true

        if (email.isBlank()) {
            _errorInputEmail.value = true
            result = false
        }
        if (password.isBlank()) {
            _errorInputPassword.value = true
            result = false
        }
        return result
    }

    private fun validateFields(
        name: String,
        phone: String,
        email: String,
        password: String
    ): Boolean {

        var result = true

        if (name.isBlank()) {
            _errorInputName.value = true
            result = false
        }
        if (phone.isBlank()) {
            _errorInputPhone.value = true
            result = false
        }
        if (email.isBlank()) {
            _errorInputEmail.value = true
            result = false
        }
        if (password.isBlank()) {
            _errorInputPassword.value = true
            result = false
        }
        return result
    }

    fun resetErrorInputName() {
        _errorInputName.value = false
    }

    fun resetErrorInputPhone() {
        _errorInputPhone.value = false
    }

    fun resetErrorInputEmail() {
        _errorInputEmail.value = false
    }

    fun resetErrorInputPassword() {
        _errorInputPassword.value = false
    }
}