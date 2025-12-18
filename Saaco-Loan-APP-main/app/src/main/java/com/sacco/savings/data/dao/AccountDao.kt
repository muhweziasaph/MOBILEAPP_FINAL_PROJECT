package com.sacco.savings.data.dao

import androidx.room.*
import com.sacco.savings.data.model.Account
import kotlinx.coroutines.flow.Flow

@Dao
interface AccountDao {
    @Query("SELECT * FROM accounts WHERE userId = :userId")
    fun getAccountsByUserId(userId: Long): Flow<List<Account>>

    @Query("SELECT * FROM accounts WHERE id = :accountId")
    fun getAccountById(accountId: Long): Flow<Account?>

    @Query("SELECT * FROM accounts WHERE id = :accountId")
    suspend fun getAccountByIdSync(accountId: Long): Account?

    @Query("SELECT * FROM accounts WHERE accountNumber = :accountNumber")
    suspend fun getAccountByNumber(accountNumber: String): Account?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAccount(account: Account): Long

    @Update
    suspend fun updateAccount(account: Account)

    @Query("UPDATE accounts SET balance = balance + :amount WHERE id = :accountId")
    suspend fun deposit(accountId: Long, amount: Double)

    @Query("UPDATE accounts SET balance = balance - :amount WHERE id = :accountId")
    suspend fun withdraw(accountId: Long, amount: Double)

    @Delete
    suspend fun deleteAccount(account: Account)
}

