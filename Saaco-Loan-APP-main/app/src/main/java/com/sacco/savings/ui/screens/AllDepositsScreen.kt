package com.sacco.savings.ui.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sacco.savings.data.model.Account
import com.sacco.savings.data.model.Transaction
import com.sacco.savings.data.model.User
import com.sacco.savings.repository.SaccoRepository
import com.sacco.savings.ui.theme.AccentGreen
import com.sacco.savings.ui.theme.PrimaryBlue
import com.sacco.savings.ui.utils.formatCurrency
import kotlinx.coroutines.flow.first
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AllDepositsScreen(
    repository: SaccoRepository,
    onBack: () -> Unit
) {
    val deposits = remember { mutableStateOf<List<Transaction>>(emptyList()) }
    val accountsMap = remember { mutableStateOf<Map<Long, Account>>(emptyMap()) }
    val usersMap = remember { mutableStateOf<Map<Long, User>>(emptyMap()) }
    var isLoading by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        try {
            Log.d("AllDepositsScreen", "Loading all deposits")
            repository.getAllDeposits().collect { depositList ->
                Log.d("AllDepositsScreen", "Received ${depositList.size} deposits")
                deposits.value = depositList
                
                // Load account details for each deposit
                val accountIds = depositList.map { it.accountId }.distinct()
                val accounts = mutableMapOf<Long, Account>()
                accountIds.forEach { accountId ->
                    try {
                        val account = repository.getAccountById(accountId).first()
                        account?.let {
                            accounts[accountId] = it
                            Log.d("AllDepositsScreen", "Loaded account: id=${it.id}, accountNumber=${it.accountNumber}, userId=${it.userId}")
                        }
                    } catch (e: Exception) {
                        Log.e("AllDepositsScreen", "Error loading account $accountId: ${e.message}")
                    }
                }
                accountsMap.value = accounts
                
                // Load user details for each account
                val userIds = accounts.values.map { it.userId }.distinct()
                val users = mutableMapOf<Long, User>()
                userIds.forEach { userId ->
                    try {
                        val user = repository.getUserById(userId).first()
                        user?.let {
                            users[userId] = it
                            Log.d("AllDepositsScreen", "Loaded user: id=${it.id}, name=${it.firstName} ${it.lastName}")
                        }
                    } catch (e: Exception) {
                        Log.e("AllDepositsScreen", "Error loading user $userId: ${e.message}")
                    }
                }
                usersMap.value = users
                isLoading = false
            }
        } catch (e: Exception) {
            Log.e("AllDepositsScreen", "Error loading deposits: ${e.message}", e)
            isLoading = false
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Transactions",
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = PrimaryBlue,
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White
                )
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color(0xFFF5F5F5))
        ) {
            if (isLoading) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = PrimaryBlue)
                }
            } else if (deposits.value.isEmpty()) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(32.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "No deposits yet",
                        style = MaterialTheme.typography.titleLarge,
                        color = Color.Gray
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "All deposits you make will appear here",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Gray
                    )
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(deposits.value) { deposit ->
                        val account = accountsMap.value[deposit.accountId]
                        val user = account?.let { usersMap.value[it.userId] }
                        DepositItem(
                            transaction = deposit,
                            account = account,
                            user = user
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun DepositItem(
    transaction: Transaction,
    account: Account?,
    user: User?
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp)),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "DEPOSIT",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = AccentGreen
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = transaction.description,
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Gray
                    )
                    if (user != null) {
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "${user.firstName} ${user.lastName} (${user.memberId})",
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.Medium,
                            color = PrimaryBlue
                        )
                    }
                    if (account != null) {
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = "Account: ${account.accountNumber}",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.Gray
                        )
                    }
                }
                Text(
                    text = "+${formatCurrency(transaction.amount)}",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = AccentGreen
                )
            }
            Divider(color = Color.Gray.copy(alpha = 0.2f))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Ref: ${transaction.referenceNumber}",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
                Text(
                    text = formatDepositDate(transaction.transactionDate),
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
            }
        }
    }
}

private fun formatDepositDate(timestamp: Long): String {
    val dateFormat = SimpleDateFormat("dd MMM yyyy, HH:mm", Locale.getDefault())
    return dateFormat.format(Date(timestamp))
}
