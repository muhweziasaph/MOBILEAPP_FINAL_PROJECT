package com.sacco.savings

import android.app.Application
import com.sacco.savings.data.database.SaccoDatabase
import com.sacco.savings.repository.SaccoRepository

class SaccoApplication : Application() {
    val database by lazy { SaccoDatabase.getDatabase(this) }
    val repository by lazy {
        SaccoRepository(
            database.userDao(),
            database.accountDao(),
            database.transactionDao()
        )
    }
}

