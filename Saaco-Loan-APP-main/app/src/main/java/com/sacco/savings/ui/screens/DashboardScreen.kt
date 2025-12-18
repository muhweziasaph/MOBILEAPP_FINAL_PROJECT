package com.sacco.savings.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.ui.text.input.KeyboardType
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import android.util.Log
import com.sacco.savings.data.model.Account
import com.sacco.savings.data.model.User
import com.sacco.savings.repository.SaccoRepository
import com.sacco.savings.ui.theme.AccentGreen
import com.sacco.savings.ui.theme.PrimaryBlue
import com.sacco.savings.ui.utils.formatCurrency
import com.sacco.savings.ui.viewmodel.AccountViewModel
import com.sacco.savings.ui.viewmodel.MembersViewModel
import com.sacco.savings.ui.viewmodel.MembersViewModelFactory
import kotlinx.coroutines.flow.first

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    accountViewModel: AccountViewModel,
    userId: Long,
    repository: SaccoRepository,
    onLogout: () -> Unit,
    onNavigateToTransactions: () -> Unit,
    onNavigateToMembers: () -> Unit,
    onNavigateToAccount: () -> Unit,
    onNavigateToSettings: () -> Unit
) {
    var showDepositDialog by remember { mutableStateOf(false) }
    var showLogoutDialog by remember { mutableStateOf(false) }
    val snackbarHostState = remember { SnackbarHostState() }
    var totalDeposits by remember { mutableStateOf(0.0) }
    
    LaunchedEffect(userId) {
        if (userId != -1L) {
            Log.d("DashboardScreen", "Loading accounts for userId: $userId")
            accountViewModel.loadAccounts(userId)
            // Load total deposits
            try {
                val total = repository.getTotalDeposits()
                Log.d("DashboardScreen", "Total deposits: $total")
                totalDeposits = total
            } catch (e: Exception) {
                Log.e("DashboardScreen", "Error loading total deposits: ${e.message}", e)
            }
        } else {
            Log.w("DashboardScreen", "Invalid userId: $userId")
        }
    }
    
    val accounts by accountViewModel.accounts.collectAsStateWithLifecycle()
    val transactionState by accountViewModel.transactionState.collectAsStateWithLifecycle()
    
    val account = accounts.firstOrNull()
    
    // Log accounts when they change
    LaunchedEffect(accounts) {
        Log.d("DashboardScreen", "Accounts updated: count=${accounts.size} for userId: $userId")
        accounts.forEach { acc ->
            Log.d("DashboardScreen", "  - Account: id=${acc.id}, accountNumber=${acc.accountNumber}, userId=${acc.userId}, balance=${acc.balance}")
        }
    }
    
    LaunchedEffect(account) {
        account?.let {
            Log.d("DashboardScreen", "Loading transactions for account: id=${it.id}, accountNumber=${it.accountNumber}, balance=${it.balance}, userId=${it.userId}")
            accountViewModel.loadTransactions(it.id)
        } ?: Log.w("DashboardScreen", "No account found for userId: $userId")
    }
    
    // Reload total deposits when screen becomes visible
    LaunchedEffect(Unit) {
        try {
            val total = repository.getTotalDeposits()
            Log.d("DashboardScreen", "Reloading total deposits on screen focus: $total")
            totalDeposits = total
        } catch (e: Exception) {
            Log.e("DashboardScreen", "Error reloading total deposits on focus: ${e.message}", e)
        }
    }
    
    LaunchedEffect(transactionState) {
        when (val state = transactionState) {
            is AccountViewModel.TransactionState.Success -> {
                showDepositDialog = false
                snackbarHostState.showSnackbar(
                    message = state.message,
                    duration = SnackbarDuration.Short
                )
                // Reload accounts to update balance
                accountViewModel.loadAccounts(userId)
                // Reload transactions for the logged-in user's account
                account?.let {
                    Log.d("DashboardScreen", "Reloading transactions after successful deposit for accountId: ${it.id}")
                    accountViewModel.loadTransactions(it.id)
                } ?: Log.w("DashboardScreen", "Cannot reload: account is null")
                // Reload total deposits
                try {
                    val total = repository.getTotalDeposits()
                    Log.d("DashboardScreen", "Total deposits after deposit: $total")
                    totalDeposits = total
                } catch (e: Exception) {
                    Log.e("DashboardScreen", "Error reloading total deposits: ${e.message}", e)
                }
                accountViewModel.resetTransactionState()
                // Navigate to Transactions screen
                onNavigateToTransactions()
            }
            is AccountViewModel.TransactionState.Error -> {
                snackbarHostState.showSnackbar(
                    message = state.message,
                    duration = SnackbarDuration.Long
                )
                accountViewModel.resetTransactionState()
            }
            else -> {}
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "BF SACCO SAVINGS APP",
                        fontWeight = FontWeight.Bold
                    )
                },
                actions = {
                    IconButton(onClick = onNavigateToAccount) {
                        Icon(
                            imageVector = Icons.Default.AccountCircle,
                            contentDescription = "Account"
                        )
                    }
                    IconButton(onClick = { showLogoutDialog = true }) {
                        Icon(
                            imageVector = Icons.Default.Logout,
                            contentDescription = "Logout"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = PrimaryBlue,
                    titleContentColor = Color.White,
                    actionIconContentColor = Color.White
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
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Account Balance Card
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(20.dp)),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    ),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                brush = Brush.horizontalGradient(
                                    colors = listOf(PrimaryBlue, PrimaryBlue.copy(alpha = 0.8f))
                                )
                            )
                            .padding(24.dp)
                    ) {
                        Column {
                            Text(
                                text = "Total Deposits",
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color.White.copy(alpha = 0.9f)
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = formatCurrency(totalDeposits),
                                style = MaterialTheme.typography.headlineLarge,
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                                maxLines = 1
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            account?.let {
                                Text(
                                    text = "Account: ${it.accountNumber}",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = Color.White.copy(alpha = 0.8f)
                                )
                                Text(
                                    text = "Type: ${it.accountType}",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = Color.White.copy(alpha = 0.8f)
                                )
                            }
                        }
                    }
                }
                
                // Deposit Button
                Button(
                    onClick = { showDepositDialog = true },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = AccentGreen
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Deposit",
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Deposit",
                        fontWeight = FontWeight.SemiBold
                    )
                }
                
                // View Transactions Button
                OutlinedButton(
                    onClick = onNavigateToTransactions,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.History,
                        contentDescription = "Transactions",
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "View Transaction History",
                        fontWeight = FontWeight.SemiBold
                    )
                }
                
                Spacer(modifier = Modifier.height(8.dp))
                
                // Members Management Card
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                        .clickable(onClick = onNavigateToMembers),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    ),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.People,
                                contentDescription = "Members",
                                modifier = Modifier.size(32.dp),
                                tint = PrimaryBlue
                            )
                            Column {
                                Text(
                                    text = "Members Management",
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = "View, add, edit, and delete members",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = Color.Gray
                                )
                            }
                        }
                        Icon(
                            imageVector = Icons.Default.ChevronRight,
                            contentDescription = "Navigate",
                            tint = Color.Gray
                        )
                    }
                }
                
                // Settings Card
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                        .clickable(onClick = onNavigateToSettings),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    ),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.Settings,
                                contentDescription = "Settings",
                                modifier = Modifier.size(32.dp),
                                tint = PrimaryBlue
                            )
                            Column {
                                Text(
                                    text = "Settings",
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = "Manage your account settings",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = Color.Gray
                                )
                            }
                        }
                        Icon(
                            imageVector = Icons.Default.ChevronRight,
                            contentDescription = "Navigate",
                            tint = Color.Gray
                        )
                    }
                }
            }
        }
    }
    
    // Deposit Bottom Sheet
    if (showDepositDialog) {
        DepositBottomSheet(
            repository = repository,
            onDismiss = { 
                showDepositDialog = false
            },
            onConfirm = { selectedUserId, amount, description ->
                accountViewModel.depositForUser(selectedUserId, amount, description)
            }
        )
    }
    
    // Logout Confirmation Dialog
    if (showLogoutDialog) {
        AlertDialog(
            onDismissRequest = { showLogoutDialog = false },
            title = {
                Text(
                    text = "Logout",
                    fontWeight = FontWeight.Bold
                )
            },
            text = {
                Text("Are you sure you want to logout?")
            },
            confirmButton = {
                Button(
                    onClick = {
                        showLogoutDialog = false
                        onLogout()
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFF5722)
                    )
                ) {
                    Text("Logout")
                }
            },
            dismissButton = {
                TextButton(onClick = { showLogoutDialog = false }) {
                    Text("Cancel")
                }
            }
        )
    }
}

@Composable
fun TransactionDialog(
    title: String,
    onDismiss: () -> Unit,
    onConfirm: (Double, String) -> Unit
) {
    var amount by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = title,
                fontWeight = FontWeight.Bold
            )
        },
        text = {
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                OutlinedTextField(
                    value = amount,
                    onValueChange = { amount = it },
                    label = { Text("Amount") },
                    placeholder = { Text("Enter amount") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )
                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("Description (Optional)") },
                    placeholder = { Text("Enter description") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    val amountValue = amount.toDoubleOrNull()
                    if (amountValue != null && amountValue > 0) {
                        onConfirm(amountValue, description.ifEmpty { title })
                        onDismiss()
                    }
                }
            ) {
                Text("Confirm")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DepositBottomSheet(
    repository: SaccoRepository,
    onDismiss: () -> Unit,
    onConfirm: (Long, Double, String) -> Unit
) {
    val membersViewModel: MembersViewModel = viewModel(
        factory = MembersViewModelFactory(repository)
    )
    val members by membersViewModel.members.collectAsStateWithLifecycle()
    
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var selectedMember by remember { mutableStateOf<User?>(null) }
    var expanded by remember { mutableStateOf(false) }
    var amount by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    
    LaunchedEffect(Unit) {
        membersViewModel.loadMembers()
    }

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .padding(top = 24.dp)
                .padding(bottom = 5.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Deposit Money",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )
            
            // Cancel and Deposit Buttons at the top
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                OutlinedButton(
                    onClick = onDismiss,
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Cancel")
                }
                
                Button(
                    onClick = {
                        val amountValue = amount.toDoubleOrNull()
                        if (selectedMember != null && amountValue != null && amountValue > 0) {
                            onConfirm(selectedMember!!.id, amountValue, description.ifEmpty { "Deposit" })
                        }
                    },
                    modifier = Modifier.weight(1f),
                    enabled = selectedMember != null && 
                        amount.isNotBlank() && amount.toDoubleOrNull() != null && 
                        amount.toDoubleOrNull()!! > 0,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = AccentGreen
                    )
                ) {
                    Text("Deposit")
                }
            }
            
            // Member Dropdown
            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded }
            ) {
                OutlinedTextField(
                    value = selectedMember?.let { "${it.firstName} ${it.lastName} (${it.memberId})" } ?: "",
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Select Member") },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .menuAnchor()
                )
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    members.forEach { member ->
                        DropdownMenuItem(
                            text = {
                                Column {
                                    Text(
                                        text = "${member.firstName} ${member.lastName}",
                                        fontWeight = FontWeight.SemiBold
                                    )
                                    Text(
                                        text = "${member.memberId} - ${member.email}",
                                        style = MaterialTheme.typography.bodySmall,
                                        color = Color.Gray
                                    )
                                }
                            },
                            onClick = {
                                selectedMember = member
                                expanded = false
                            }
                        )
                    }
                }
            }
            
            // Deposit Amount
            OutlinedTextField(
                value = amount,
                onValueChange = { amount = it },
                label = { Text("Deposit Amount") },
                placeholder = { Text("Enter amount") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                leadingIcon = {
                    Text(
                        text = "UGX",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Gray,
                        modifier = Modifier.padding(start = 16.dp, end = 8.dp)
                    )
                }
            )
            
            // Description (Optional)
            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Description (Optional)") },
                placeholder = { Text("Enter description") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
        }
    }
}


