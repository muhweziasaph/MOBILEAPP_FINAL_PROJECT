package com.sacco.savings.data.dao

import androidx.room.*
import com.sacco.savings.data.model.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionDao {
    @Query("SELECT * FROM transactions WHERE accountId = :accountId ORDER BY transactionDate DESC")
    fun getTransactionsByAccountId(accountId: Long): Flow<List<Transaction>>

    @Query("SELECT * FROM transactions WHERE accountId = :accountId ORDER BY transactionDate DESC LIMIT :limit")
    fun getRecentTransactions(accountId: Long, limit: Int = 10): Flow<List<Transaction>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTransaction(transaction: Transaction): Long

    @Query("SELECT SUM(amount) FROM transactions WHERE accountId = :accountId AND transactionType = 'DEPOSIT'")
    suspend fun getTotalDeposits(accountId: Long): Double?

    @Query("SELECT SUM(amount) FROM transactions WHERE accountId = :accountId AND transactionType = 'WITHDRAWAL'")
    suspend fun getTotalWithdrawals(accountId: Long): Double?

    @Query("SELECT * FROM transactions WHERE transactionType = 'DEPOSIT' ORDER BY transactionDate DESC")
    fun getAllDeposits(): Flow<List<Transaction>>

    @Query("SELECT COALESCE(SUM(amount), 0) FROM transactions WHERE transactionType = 'DEPOSIT'")
    suspend fun getTotalDeposits(): Double
}

