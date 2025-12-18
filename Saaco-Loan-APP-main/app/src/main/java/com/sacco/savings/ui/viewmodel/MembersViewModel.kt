package com.sacco.savings.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.sacco.savings.repository.SaccoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MembersViewModel(private val repository: SaccoRepository) : ViewModel() {
    private val _members = MutableStateFlow<List<com.sacco.savings.data.model.User>>(emptyList())
    val members: StateFlow<List<com.sacco.savings.data.model.User>> = _members

    private val _operationState = MutableStateFlow<OperationState>(OperationState.Idle)
    val operationState: StateFlow<OperationState> = _operationState

    init {
        loadMembers()
    }

    fun loadMembers() {
        viewModelScope.launch {
            repository.getAllUsers().collect { userList ->
                // Filter out registered users - only show members created via members screen
                _members.value = userList.filter { !it.isRegistered }
            }
        }
    }

    fun createMember(
        firstName: String,
        lastName: String,
        email: String,
        phoneNumber: String
    ) {
        viewModelScope.launch {
            _operationState.value = OperationState.Loading
            try {
                val existingUser = repository.getUserByEmail(email)
                if (existingUser != null) {
                    _operationState.value = OperationState.Error("Email already exists")
                    return@launch
                }

                val memberId = "MEM${System.currentTimeMillis().toString().takeLast(8)}"
                // Generate a default password (can be changed later)
                val defaultPassword = "Sacco${memberId}"
                val user = com.sacco.savings.data.model.User(
                    memberId = memberId,
                    firstName = firstName,
                    lastName = lastName,
                    email = email,
                    phoneNumber = phoneNumber,
                    password = defaultPassword,
                    isRegistered = false // Mark as member (not registered)
                )
                val userId = repository.registerUser(user)
                
                // Create a default savings account for the new member
                repository.createAccount(userId, "Savings")
                
                _operationState.value = OperationState.Success("Member created successfully")
            } catch (e: Exception) {
                _operationState.value = OperationState.Error(e.message ?: "Failed to create member")
            }
        }
    }

    fun updateMember(user: com.sacco.savings.data.model.User) {
        viewModelScope.launch {
            _operationState.value = OperationState.Loading
            try {
                repository.updateUser(user)
                _operationState.value = OperationState.Success("Member updated successfully")
            } catch (e: Exception) {
                _operationState.value = OperationState.Error(e.message ?: "Failed to update member")
            }
        }
    }

    fun deleteMember(user: com.sacco.savings.data.model.User) {
        viewModelScope.launch {
            _operationState.value = OperationState.Loading
            try {
                repository.deleteUser(user)
                _operationState.value = OperationState.Success("Member deleted successfully")
            } catch (e: Exception) {
                _operationState.value = OperationState.Error(e.message ?: "Failed to delete member")
            }
        }
    }

    fun resetOperationState() {
        _operationState.value = OperationState.Idle
    }

    sealed class OperationState {
        object Idle : OperationState()
        object Loading : OperationState()
        data class Success(val message: String) : OperationState()
        data class Error(val message: String) : OperationState()
    }
}

class MembersViewModelFactory(private val repository: SaccoRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MembersViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MembersViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

