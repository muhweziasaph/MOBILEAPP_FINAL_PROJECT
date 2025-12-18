package com.sacco.savings.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sacco.savings.data.dao.AccountDao
import com.sacco.savings.data.dao.TransactionDao
import com.sacco.savings.data.dao.UserDao
import com.sacco.savings.data.model.Account
import com.sacco.savings.data.model.Transaction
import com.sacco.savings.data.model.User

@Database(
    entities = [User::class, Account::class, Transaction::class],
    version = 2,
    exportSchema = false
)
abstract class SaccoDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun accountDao(): AccountDao
    abstract fun transactionDao(): TransactionDao

    companion object {
        @Volatile
        private var INSTANCE: SaccoDatabase? = null

        fun getDatabase(context: Context): SaccoDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SaccoDatabase::class.java,
                    "sacco_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}

