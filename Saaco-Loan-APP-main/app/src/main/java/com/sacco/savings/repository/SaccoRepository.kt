package com.sacco.savings.repository

import android.util.Log
import com.sacco.savings.data.dao.AccountDao
import com.sacco.savings.data.dao.TransactionDao
import com.sacco.savings.data.dao.UserDao
import com.sacco.savings.data.model.Account
import com.sacco.savings.data.model.Transaction
import com.sacco.savings.data.model.User
import kotlinx.coroutines.flow.Flow
import java.util.UUID

class SaccoRepository(
    private val userDao: UserDao,
    private val accountDao: AccountDao,
    private val transactionDao: TransactionDao
) {
    // User operations
    suspend fun login(email: String, password: String): User? {
        return userDao.login(email, password)
    }

    suspend fun registerUser(user: User): Long {
        return userDao.insertUser(user)
    }

    fun getUserById(userId: Long): Flow<User?> {
        return userDao.getUserById(userId)
    }

    suspend fun getUserByEmail(email: String): User? {
        return userDao.getUserByEmail(email)
    }

    fun getAllUsers(): Flow<List<User>> {
        return userDao.getAllUsers()
    }

    suspend fun updateUser(user: User) {
        userDao.updateUser(user)
    }

    suspend fun deleteUser(user: User) {
        userDao.deleteUser(user)
    }

    // Account operations
    suspend fun createAccount(userId: Long, accountType: String = "Savings"): Long {
        val accountNumber = generateAccountNumber()
        val account = Account(
            userId = userId,
            accountNumber = accountNumber,
            accountType = accountType
        )
        return accountDao.insertAccount(account)
    }

    fun getAccountsByUserId(userId: Long): Flow<List<Account>> {
        return accountDao.getAccountsByUserId(userId)
    }

    fun getAccountById(accountId: Long): Flow<Account?> {
        return accountDao.getAccountById(accountId)
    }

    suspend fun deposit(accountId: Long, amount: Double, description: String): Boolean {
        return try {
            Log.d("SaccoRepository", "Deposit: accountId=$accountId, amount=$amount, description=$description")
            accountDao.deposit(accountId, amount)
            val currentDate = System.currentTimeMillis()
            val transaction = Transaction(
                accountId = accountId,
                transactionType = "DEPOSIT",
                amount = amount,
                description = description,
                transactionDate = currentDate,
                referenceNumber = generateReferenceNumber()
            )
            val transactionId = transactionDao.insertTransaction(transaction)
            Log.d("SaccoRepository", "Transaction inserted successfully: id=$transactionId, accountId=$accountId, date=$currentDate")
            true
        } catch (e: Exception) {
            Log.e("SaccoRepository", "Deposit failed: ${e.message}", e)
            false
        }
    }

    suspend fun withdraw(accountId: Long, amount: Double, description: String): Boolean {
        return try {
            val account = accountDao.getAccountByIdSync(accountId)
            if (account != null && account.balance >= amount) {
                accountDao.withdraw(accountId, amount)
                val currentDate = System.currentTimeMillis()
                val transaction = Transaction(
                    accountId = accountId,
                    transactionType = "WITHDRAWAL",
                    amount = amount,
                    description = description,
                    transactionDate = currentDate,
                    referenceNumber = generateReferenceNumber()
                )
                val transactionId = transactionDao.insertTransaction(transaction)
                Log.d("SaccoRepository", "Withdrawal transaction inserted: id=$transactionId, accountId=$accountId")
                true
            } else {
                Log.w("SaccoRepository", "Withdrawal failed: insufficient balance or account not found")
                false
            }
        } catch (e: Exception) {
            Log.e("SaccoRepository", "Withdrawal failed: ${e.message}", e)
            false
        }
    }

    // Transaction operations
    fun getTransactionsByAccountId(accountId: Long): Flow<List<Transaction>> {
        Log.d("SaccoRepository", "Getting transactions for accountId: $accountId")
        return transactionDao.getTransactionsByAccountId(accountId)
    }

    fun getRecentTransactions(accountId: Long, limit: Int = 10): Flow<List<Transaction>> {
        return transactionDao.getRecentTransactions(accountId, limit)
    }

    fun getAllDeposits(): Flow<List<Transaction>> {
        Log.d("SaccoRepository", "Getting all deposits")
        return transactionDao.getAllDeposits()
    }

    suspend fun getTotalDeposits(): Double {
        return transactionDao.getTotalDeposits()
    }

    private fun generateAccountNumber(): String {
        return "SAC${System.currentTimeMillis().toString().takeLast(10)}"
    }

    private fun generateReferenceNumber(): String {
        return "REF${UUID.randomUUID().toString().replace("-", "").take(12).uppercase()}"
    }
}

