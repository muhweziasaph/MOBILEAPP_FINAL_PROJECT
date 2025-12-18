package com.sacco.savings.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "accounts",
    foreignKeys = [
        ForeignKey(
            entity = User::class,
            parentColumns = ["id"],
            childColumns = ["userId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["userId"])]
)
data class Account(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val userId: Long,
    val accountNumber: String,
    val balance: Double = 0.0,
    val accountType: String = "Savings", // Savings, Fixed Deposit, etc.
    val createdAt: Long = System.currentTimeMillis()
)

