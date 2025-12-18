package com.sacco.savings.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.sacco.savings.repository.SaccoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class AccountViewModel(private val repository: SaccoRepository) : ViewModel() {
    private val _accounts = MutableStateFlow<List<com.sacco.savings.data.model.Account>>(emptyList())
    val accounts: StateFlow<List<com.sacco.savings.data.model.Account>> = _accounts

    private val _transactions = MutableStateFlow<List<com.sacco.savings.data.model.Transaction>>(emptyList())
    val transactions: StateFlow<List<com.sacco.savings.data.model.Transaction>> = _transactions

    private val _transactionState = MutableStateFlow<TransactionState>(TransactionState.Idle)
    val transactionState: StateFlow<TransactionState> = _transactionState

    fun loadAccounts(userId: Long) {
        viewModelScope.launch {
            Log.d("AccountViewModel", "Loading accounts for userId: $userId")
            repository.getAccountsByUserId(userId).collect { accountList ->
                Log.d("AccountViewModel", "Received ${accountList.size} accounts for userId: $userId")
                accountList.forEach { account ->
                    Log.d("AccountViewModel", "Account: id=${account.id}, accountNumber=${account.accountNumber}, userId=${account.userId}, balance=${account.balance}")
                }
                _accounts.value = accountList
            }
        }
    }

    fun loadTransactions(accountId: Long) {
        viewModelScope.launch {
            Log.d("AccountViewModel", "Loading transactions for accountId: $accountId")
            repository.getTransactionsByAccountId(accountId).collect { transactionList ->
                Log.d("AccountViewModel", "Received ${transactionList.size} transactions for accountId: $accountId")
                transactionList.forEach { transaction ->
                    Log.d("AccountViewModel", "Transaction: id=${transaction.id}, type=${transaction.transactionType}, amount=${transaction.amount}, date=${transaction.transactionDate}, accountId=${transaction.accountId}")
                }
                _transactions.value = transactionList
            }
        }
    }

    fun deposit(accountId: Long, amount: Double, description: String) {
        viewModelScope.launch {
            _transactionState.value = TransactionState.Loading
            try {
                val success = repository.deposit(accountId, amount, description)
                if (success) {
                    _transactionState.value = TransactionState.Success("Deposit successful")
                    // Flow will automatically update when database changes
                } else {
                    _transactionState.value = TransactionState.Error("Deposit failed")
                }
            } catch (e: Exception) {
                _transactionState.value = TransactionState.Error(e.message ?: "Deposit failed")
            }
        }
    }

    fun depositForUser(userId: Long, amount: Double, description: String) {
        viewModelScope.launch {
            _transactionState.value = TransactionState.Loading
            try {
                Log.d("AccountViewModel", "depositForUser: userId=$userId, amount=$amount, description=$description")
                val accounts = repository.getAccountsByUserId(userId).first()
                Log.d("AccountViewModel", "Found ${accounts.size} accounts for userId: $userId")
                val account = accounts.firstOrNull()
                if (account != null) {
                    Log.d("AccountViewModel", "Depositing to accountId: ${account.id}, accountNumber: ${account.accountNumber}")
                    val success = repository.deposit(account.id, amount, description)
                    if (success) {
                        Log.d("AccountViewModel", "Deposit successful for accountId: ${account.id}")
                        _transactionState.value = TransactionState.Success("Deposit successful")
                        // Reload transactions for the account that received the deposit
                        // This ensures the transaction appears in that account's transaction list
                        loadTransactions(account.id)
                        // Also reload accounts to update balance
                        loadAccounts(userId)
                    } else {
                        Log.e("AccountViewModel", "Deposit failed for accountId: ${account.id}")
                        _transactionState.value = TransactionState.Error("Deposit failed")
                    }
                } else {
                    Log.e("AccountViewModel", "No account found for userId: $userId")
                    _transactionState.value = TransactionState.Error("No account found for this member")
                }
            } catch (e: Exception) {
                Log.e("AccountViewModel", "Deposit error: ${e.message}", e)
                _transactionState.value = TransactionState.Error(e.message ?: "Deposit failed")
            }
        }
    }

    fun withdraw(accountId: Long, amount: Double, description: String) {
        viewModelScope.launch {
            _transactionState.value = TransactionState.Loading
            try {
                val success = repository.withdraw(accountId, amount, description)
                if (success) {
                    _transactionState.value = TransactionState.Success("Withdrawal successful")
                    // Flow will automatically update when database changes
                } else {
                    _transactionState.value = TransactionState.Error("Insufficient balance or withdrawal failed")
                }
            } catch (e: Exception) {
                _transactionState.value = TransactionState.Error(e.message ?: "Withdrawal failed")
            }
        }
    }

    fun resetTransactionState() {
        _transactionState.value = TransactionState.Idle
    }

    sealed class TransactionState {
        object Idle : TransactionState()
        object Loading : TransactionState()
        data class Success(val message: String) : TransactionState()
        data class Error(val message: String) : TransactionState()
    }
}

class AccountViewModelFactory(private val repository: SaccoRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AccountViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AccountViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

