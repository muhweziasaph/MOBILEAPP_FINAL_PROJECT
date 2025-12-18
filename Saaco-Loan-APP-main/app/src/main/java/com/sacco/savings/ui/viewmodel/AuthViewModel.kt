package com.sacco.savings.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.sacco.savings.repository.SaccoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class AuthViewModel(private val repository: SaccoRepository) : ViewModel() {
    private val _loginState = MutableStateFlow<LoginState>(LoginState.Idle)
    val loginState: StateFlow<LoginState> = _loginState

    private val _registerState = MutableStateFlow<RegisterState>(RegisterState.Idle)
    val registerState: StateFlow<RegisterState> = _registerState

    private val _firstUser = MutableStateFlow<com.sacco.savings.data.model.User?>(null)
    val firstUser: StateFlow<com.sacco.savings.data.model.User?> = _firstUser

    init {
        loadFirstUser()
    }

    private fun loadFirstUser() {
        viewModelScope.launch {
            try {
                val users = repository.getAllUsers().first()
                _firstUser.value = users.firstOrNull()
            } catch (e: Exception) {
                // Ignore errors, firstUser will remain null
            }
        }
    }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _loginState.value = LoginState.Loading
            try {
                val user = repository.login(email, password)
                if (user != null) {
                    _loginState.value = LoginState.Success(user)
                } else {
                    _loginState.value = LoginState.Error("Invalid email or password")
                }
            } catch (e: Exception) {
                _loginState.value = LoginState.Error(e.message ?: "Login failed")
            }
        }
    }

    fun register(
        firstName: String,
        lastName: String,
        email: String,
        phoneNumber: String,
        password: String
    ) {
        viewModelScope.launch {
            _registerState.value = RegisterState.Loading
            try {
                val existingUser = repository.getUserByEmail(email)
                if (existingUser != null) {
                    _registerState.value = RegisterState.Error("Email already registered")
                    return@launch
                }

                // Generate member ID automatically based on email prefix and timestamp
                val memberId = "MEM${System.currentTimeMillis().toString().takeLast(8)}"
                
                val user = com.sacco.savings.data.model.User(
                    memberId = memberId,
                    firstName = firstName,
                    lastName = lastName,
                    email = email,
                    phoneNumber = phoneNumber,
                    password = password,
                    isRegistered = true // Mark as registered user
                )
                val userId = repository.registerUser(user)
                
                // Create a default savings account for the new user
                repository.createAccount(userId, "Savings")
                
                _registerState.value = RegisterState.Success(userId)
            } catch (e: Exception) {
                _registerState.value = RegisterState.Error(e.message ?: "Registration failed")
            }
        }
    }

    sealed class LoginState {
        object Idle : LoginState()
        object Loading : LoginState()
        data class Success(val user: com.sacco.savings.data.model.User) : LoginState()
        data class Error(val message: String) : LoginState()
    }

    sealed class RegisterState {
        object Idle : RegisterState()
        object Loading : RegisterState()
        data class Success(val userId: Long) : RegisterState()
        data class Error(val message: String) : RegisterState()
    }
}

class AuthViewModelFactory(private val repository: SaccoRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AuthViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

