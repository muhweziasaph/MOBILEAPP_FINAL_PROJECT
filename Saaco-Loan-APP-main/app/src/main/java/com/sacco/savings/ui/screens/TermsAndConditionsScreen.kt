package com.sacco.savings.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.sacco.savings.ui.theme.PrimaryBlue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TermsAndConditionsScreen(
    onBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Terms and Conditions",
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
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp)),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    ),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Text(
                            text = "Last Updated: January 2024",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.Black.copy(alpha = 0.7f)
                        )
                        
                        Divider(color = Color.Gray.copy(alpha = 0.3f))
                        
                        Text(
                            text = "1. Membership",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                        
                        Text(
                            text = "1.1. Membership in BF SACCO is open to all individuals who meet the eligibility criteria as outlined in our bylaws.\n\n1.2. Members must complete the registration process and agree to these terms and conditions.\n\n1.3. Each member is required to maintain a minimum share capital as determined by the cooperative.",
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color.Black
                        )
                        
                        Text(
                            text = "2. Savings Accounts",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                        
                        Text(
                            text = "2.1. Members may open one or more savings accounts as permitted by the cooperative's policies.\n\n2.2. All deposits are subject to verification and may take up to 24 hours to reflect in your account.\n\n2.3. Interest rates on savings accounts are determined by the Board of Directors and are subject to change with prior notice.\n\n2.4. Members are responsible for maintaining the confidentiality of their account credentials.",
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color.Black
                        )
                        
                        Text(
                            text = "3. Withdrawals",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                        
                        Text(
                            text = "3.1. Withdrawal requests are subject to the cooperative's policies and may require prior notice for large amounts.\n\n3.2. The cooperative reserves the right to verify the identity of the account holder before processing withdrawals.\n\n3.3. Withdrawal limits may apply based on account type and membership duration.",
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color.Black
                        )
                        
                        Text(
                            text = "4. Credit Services",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                        
                        Text(
                            text = "4.1. Credit facilities are available to eligible members subject to credit assessment and approval.\n\n4.2. All loans are subject to interest rates and terms as agreed upon at the time of disbursement.\n\n4.3. Members are required to provide adequate collateral or guarantors as per the cooperative's lending policies.\n\n4.4. Late payment fees and penalties may apply for overdue loans.",
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color.Black
                        )
                        
                        Text(
                            text = "5. Member Responsibilities",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                        
                        Text(
                            text = "5.1. Members must provide accurate and up-to-date information during registration and account management.\n\n5.2. Members are responsible for promptly reporting any unauthorized transactions or security breaches.\n\n5.3. Members must comply with all applicable laws and regulations.\n\n5.4. Members are expected to participate in general meetings and vote on important matters.",
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color.Black
                        )
                        
                        Text(
                            text = "6. Data Protection",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                        
                        Text(
                            text = "6.1. The cooperative collects and processes member data in accordance with applicable data protection laws.\n\n6.2. Member information is kept confidential and is only shared with authorized personnel or as required by law.\n\n6.3. Members have the right to access, correct, or delete their personal information as provided by law.",
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color.Black
                        )
                        
                        Text(
                            text = "7. Termination",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                        
                        Text(
                            text = "7.1. Membership may be terminated by the member or the cooperative in accordance with the bylaws.\n\n7.2. Upon termination, the member's account balance will be refunded after settling any outstanding obligations.",
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color.Black
                        )
                        
                        Text(
                            text = "8. Amendments",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                        
                        Text(
                            text = "8.1. These terms and conditions may be amended from time to time with notice to members.\n\n8.2. Continued use of services after amendments constitutes acceptance of the new terms.",
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color.Black
                        )
                        
                        Text(
                            text = "9. Dispute Resolution",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                        
                        Text(
                            text = "9.1. Any disputes arising from these terms shall be resolved through internal dispute resolution mechanisms.\n\n9.2. If internal resolution fails, disputes may be referred to arbitration or courts of competent jurisdiction.",
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color.Black
                        )
                        
                        Text(
                            text = "10. Contact",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                        
                        Text(
                            text = "For questions about these terms and conditions, please contact us at legal@bfsacco.co.ug",
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color.Black
                        )
                    }
                }
            }
        }
    }
}