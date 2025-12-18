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
fun AboutUsScreen(
    onBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "About Us",
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
                            text = "BF SACCO Savings",
                            style = MaterialTheme.typography.headlineMedium,
                            fontWeight = FontWeight.Bold,
                            color = PrimaryBlue
                        )
                        
                        Text(
                            text = "Version 1.0.0",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.Black.copy(alpha = 0.7f)
                        )
                        
                            Divider(color = Color.Gray.copy(alpha = 0.3f))
                        
                        Text(
                            text = "About Our Organization",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                        
                        Text(
                            text = "BF SACCO (Benevolent Fund Savings and Credit Cooperative Organization) is a member-owned financial cooperative dedicated to empowering our community through accessible savings and credit services.",
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color.Black
                        )
                        
                        Text(
                            text = "Our Mission",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                        
                        Text(
                            text = "To provide accessible, affordable, and reliable financial services that promote savings culture and support the economic growth of our members and the wider community.",
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color.Black
                        )
                        
                        Text(
                            text = "Our Vision",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                        
                        Text(
                            text = "To be the leading financial cooperative in our region, recognized for excellence in service delivery, member satisfaction, and sustainable growth.",
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color.Black
                        )
                        
                        Text(
                            text = "Our Values",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                        
                        Column(
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text("• Integrity and Transparency", style = MaterialTheme.typography.bodyLarge, color = Color.Black)
                            Text("• Member Focus", style = MaterialTheme.typography.bodyLarge, color = Color.Black)
                            Text("• Financial Inclusion", style = MaterialTheme.typography.bodyLarge, color = Color.Black)
                            Text("• Innovation and Excellence", style = MaterialTheme.typography.bodyLarge, color = Color.Black)
                            Text("• Community Development", style = MaterialTheme.typography.bodyLarge, color = Color.Black)
                        }
                        
                            Divider(color = Color.Gray.copy(alpha = 0.3f))
                        
                        Text(
                            text = "Contact Information",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                        
                        Text(
                            text = "Email: info@bfsacco.co.ug\nPhone: +256 XXX XXX XXX\nAddress: Kampala, Uganda",
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color.Black
                        )
                    }
                }
            }
        }
    }
}