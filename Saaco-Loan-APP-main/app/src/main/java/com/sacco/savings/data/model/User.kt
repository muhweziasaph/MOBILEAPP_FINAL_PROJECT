package com.sacco.savings.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val memberId: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val phoneNumber: String,
    val password: String, // In production, this should be hashed
    val createdAt: Long = System.currentTimeMillis(),
    val isRegistered: Boolean = false // true for users registered via registration screen, false for members created via members screen
)

