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
fun PrivacyPolicyScreen(
    onBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Privacy Policy",
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
                            text = "Introduction",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                        
                        Text(
                            text = "BF SACCO is committed to protecting your privacy and ensuring the security of your personal information. This Privacy Policy explains how we collect, use, disclose, and safeguard your information when you use our mobile application and services.",
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color.Black
                        )
                        
                        Text(
                            text = "1. Information We Collect",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                        
                        Text(
                            text = "1.1. Personal Information: We collect personal information that you provide directly to us, including:\n• Name and contact information (email, phone number, address)\n• Identification documents\n• Financial information (account numbers, transaction history)\n• Authentication credentials (passwords, PINs)\n\n1.2. Usage Information: We automatically collect information about how you use our application, including:\n• Device information (device type, operating system, unique device identifiers)\n• Log information (IP address, access times, app usage patterns)\n• Location information (if you grant location permissions)",
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color.Black
                        )
                        
                        Text(
                            text = "2. How We Use Your Information",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                        
                        Text(
                            text = "We use the information we collect to:\n• Provide, maintain, and improve our services\n• Process transactions and manage your accounts\n• Authenticate your identity and prevent fraud\n• Communicate with you about your account and our services\n• Comply with legal obligations and regulatory requirements\n• Analyze usage patterns to improve user experience\n• Send you important updates and notifications",
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color.Black
                        )
                        
                        Text(
                            text = "3. Information Sharing and Disclosure",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                        
                        Text(
                            text = "We do not sell your personal information. We may share your information only in the following circumstances:\n\n3.1. Service Providers: We may share information with third-party service providers who perform services on our behalf, such as payment processing, data analytics, and cloud storage.\n\n3.2. Legal Requirements: We may disclose information if required by law, court order, or regulatory authority.\n\n3.3. Business Transfers: In the event of a merger, acquisition, or sale of assets, your information may be transferred to the new entity.\n\n3.4. With Your Consent: We may share information with your explicit consent for specific purposes.",
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color.Black
                        )
                        
                        Text(
                            text = "4. Data Security",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                        
                        Text(
                            text = "We implement appropriate technical and organizational measures to protect your personal information against unauthorized access, alteration, disclosure, or destruction. These measures include:\n• Encryption of sensitive data in transit and at rest\n• Secure authentication mechanisms\n• Regular security assessments and updates\n• Access controls and employee training\n• However, no method of transmission over the internet or electronic storage is 100% secure, and we cannot guarantee absolute security.",
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color.Black
                        )
                        
                        Text(
                            text = "5. Data Retention",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                        
                        Text(
                            text = "We retain your personal information for as long as necessary to fulfill the purposes outlined in this Privacy Policy, unless a longer retention period is required or permitted by law. When we no longer need your information, we will securely delete or anonymize it.",
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color.Black
                        )
                        
                        Text(
                            text = "6. Your Rights",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                        
                        Text(
                            text = "You have the following rights regarding your personal information:\n• Access: Request a copy of your personal information\n• Correction: Request correction of inaccurate or incomplete information\n• Deletion: Request deletion of your personal information (subject to legal requirements)\n• Objection: Object to processing of your personal information\n• Portability: Request transfer of your information to another service provider\n• Withdrawal of Consent: Withdraw consent where processing is based on consent",
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color.Black
                        )
                        
                        Text(
                            text = "7. Cookies and Tracking Technologies",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                        
                        Text(
                            text = "Our application may use cookies and similar tracking technologies to enhance your experience, analyze usage patterns, and improve our services. You can control cookie preferences through your device settings.",
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color.Black
                        )
                        
                        Text(
                            text = "8. Children's Privacy",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                        
                        Text(
                            text = "Our services are not directed to individuals under the age of 18. We do not knowingly collect personal information from children. If we become aware that we have collected information from a child, we will take steps to delete such information promptly.",
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color.Black
                        )
                        
                        Text(
                            text = "9. Changes to This Privacy Policy",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                        
                        Text(
                            text = "We may update this Privacy Policy from time to time. We will notify you of any material changes by posting the new Privacy Policy in the application and updating the \"Last Updated\" date. Your continued use of our services after such changes constitutes acceptance of the updated policy.",
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color.Black
                        )
                        
                        Text(
                            text = "10. Contact Us",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                        
                        Text(
                            text = "If you have any questions, concerns, or requests regarding this Privacy Policy or our data practices, please contact us at:\n\nEmail: privacy@bfsacco.co.ug\nPhone: +256 XXX XXX XXX\nAddress: Kampala, Uganda",
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color.Black
                        )
                    }
                }
            }
        }
    }
}