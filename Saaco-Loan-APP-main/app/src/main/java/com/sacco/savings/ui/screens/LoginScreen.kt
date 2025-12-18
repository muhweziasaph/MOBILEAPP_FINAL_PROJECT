package com.sacco.savings.ui.screens

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.layout.ContentScale
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sacco.savings.R
import com.sacco.savings.ui.theme.AccentGreen
import com.sacco.savings.ui.theme.PrimaryBlue
import com.sacco.savings.ui.theme.PrimaryBlueDark
import com.sacco.savings.ui.theme.PrimaryBlueLight
import com.sacco.savings.ui.viewmodel.AuthViewModel

@Composable
fun LoginScreen(
    authViewModel: AuthViewModel,
    onLoginSuccess: (Long) -> Unit,
    onNavigateToRegister: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    
    val loginState by authViewModel.loginState.collectAsStateWithLifecycle()
    val firstUser by authViewModel.firstUser.collectAsStateWithLifecycle()
    
    // Populate fields with first user's credentials when available
    LaunchedEffect(firstUser) {
        firstUser?.let { user ->
            if (email.isEmpty() && password.isEmpty()) {
                email = user.email
                password = user.password
            }
        }
    }
    
    LaunchedEffect(loginState) {
        when (val state = loginState) {
            is AuthViewModel.LoginState.Success -> {
                onLoginSuccess(state.user.id)
            }
            else -> {}
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        PrimaryBlueDark,
                        PrimaryBlue,
                        PrimaryBlueLight,
                        Color.White
                    ),
                    startY = 0f,
                    endY = Float.POSITIVE_INFINITY
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.height(32.dp))
            
            // App Logo with enhanced design - transparent background to blend with gradient
            Box(
                modifier = Modifier
                    .size(140.dp)
                    .shadow(
                        elevation = 8.dp,
                        shape = CircleShape,
                        spotColor = Color.Black.copy(alpha = 0.2f)
                    )
                    .clip(CircleShape),
                contentAlignment = Alignment.Center
            ) {
                androidx.compose.foundation.Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "BF SACCO Logo",
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp),
                    contentScale = ContentScale.Fit
                )
            }
            
            Spacer(modifier = Modifier.height(40.dp))
            
            // Sign in prompt text
            Text(
                text = "Sign in to continue to your account",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White.copy(alpha = 0.95f),
                textAlign = TextAlign.Center
            )
            
            Spacer(modifier = Modifier.height(56.dp))
            
            // Login Card with enhanced design
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(
                        elevation = 16.dp,
                        shape = RoundedCornerShape(28.dp),
                        spotColor = Color.Black.copy(alpha = 0.2f)
                    )
                    .clip(RoundedCornerShape(28.dp)),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
            ) {
                Column(
                    modifier = Modifier.padding(28.dp),
                    verticalArrangement = Arrangement.spacedBy(24.dp)
                ) {
                    // Email Field with enhanced styling
                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it },
                        label = { 
                            Text(
                                "Email Address",
                                fontWeight = FontWeight.Medium
                            ) 
                        },
                        placeholder = { Text("Enter your email", color = Color.Black.copy(alpha = 0.6f)) },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Email,
                                contentDescription = "Email",
                                tint = PrimaryBlue
                            )
                        },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                        shape = RoundedCornerShape(16.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedTextColor = Color.Black,
                            unfocusedTextColor = Color.Black,
                            focusedBorderColor = PrimaryBlue,
                            focusedLabelColor = PrimaryBlue,
                            unfocusedLabelColor = Color.Black.copy(alpha = 0.7f),
                            unfocusedBorderColor = Color.Gray.copy(alpha = 0.4f),
                            unfocusedContainerColor = Color(0xFFF8F9FA),
                            focusedPlaceholderColor = Color.Black.copy(alpha = 0.6f),
                            unfocusedPlaceholderColor = Color.Black.copy(alpha = 0.6f)
                        )
                    )
                    
                    // Password Field with enhanced styling
                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        label = { 
                            Text(
                                "Password",
                                fontWeight = FontWeight.Medium
                            ) 
                        },
                        placeholder = { Text("Enter your password", color = Color.Black.copy(alpha = 0.6f)) },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Lock,
                                contentDescription = "Password",
                                tint = PrimaryBlue
                            )
                        },
                        trailingIcon = {
                            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                                Icon(
                                    imageVector = if (passwordVisible) 
                                        Icons.Default.Visibility 
                                    else 
                                        Icons.Default.VisibilityOff,
                                    contentDescription = if (passwordVisible) "Hide password" else "Show password",
                                    tint = if (passwordVisible) PrimaryBlue else Color.Gray
                                )
                            }
                        },
                        visualTransformation = if (passwordVisible) 
                            VisualTransformation.None 
                        else 
                            PasswordVisualTransformation(),
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        shape = RoundedCornerShape(16.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedTextColor = Color.Black,
                            unfocusedTextColor = Color.Black,
                            focusedBorderColor = PrimaryBlue,
                            focusedLabelColor = PrimaryBlue,
                            unfocusedLabelColor = Color.Black.copy(alpha = 0.7f),
                            unfocusedBorderColor = Color.Gray.copy(alpha = 0.4f),
                            unfocusedContainerColor = Color(0xFFF8F9FA),
                            focusedPlaceholderColor = Color.Black.copy(alpha = 0.6f),
                            unfocusedPlaceholderColor = Color.Black.copy(alpha = 0.6f)
                        )
                    )
                    
                    // Error Message with better styling
                    val errorState = loginState as? AuthViewModel.LoginState.Error
                    if (errorState != null) {
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            colors = CardDefaults.cardColors(
                                containerColor = Color(0xFFFFEBEE)
                            ),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(12.dp)
                            ) {
                                Text(
                                    text = "⚠",
                                    fontSize = 20.sp,
                                    modifier = Modifier.size(24.dp)
                                )
                                Text(
                                    text = errorState.message,
                                    color = Color(0xFFC62828),
                                    style = MaterialTheme.typography.bodyMedium,
                                    fontWeight = FontWeight.Medium,
                                    modifier = Modifier.weight(1f)
                                )
                            }
                        }
                    }
                    
                    Spacer(modifier = Modifier.height(8.dp))
                    
                    // Login Button with enhanced styling
                    val isLoading = loginState is AuthViewModel.LoginState.Loading
                    val isFormValid = email.isNotBlank() && password.isNotBlank()
                    val buttonBackgroundColor by animateColorAsState(
                        targetValue = if (isFormValid && !isLoading) PrimaryBlue else PrimaryBlue.copy(alpha = 0.6f),
                        animationSpec = tween(300)
                    )
                    
                    Button(
                        onClick = {
                            if (isFormValid) {
                                authViewModel.login(email.trim(), password)
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(58.dp)
                            .shadow(
                                elevation = if (isFormValid && !isLoading) 8.dp else 0.dp,
                                shape = RoundedCornerShape(16.dp),
                                spotColor = PrimaryBlue.copy(alpha = 0.3f)
                            ),
                        enabled = !isLoading && isFormValid,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = buttonBackgroundColor,
                            contentColor = Color.White
                        ),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        if (isLoading) {
                            Row(
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                CircularProgressIndicator(
                                    modifier = Modifier.size(22.dp),
                                    color = Color.White,
                                    strokeWidth = 2.5.dp
                                )
                                Spacer(modifier = Modifier.width(14.dp))
                                Text(
                                    text = "Signing in...",
                                    fontSize = 17.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White,
                                    letterSpacing = 0.5.sp
                                )
                            }
                        } else {
                            Text(
                                text = "SIGN IN",
                                fontSize = 17.sp,
                                fontWeight = FontWeight.Bold,
                                letterSpacing = 1.sp
                            )
                        }
                    }
                    
                    Spacer(modifier = Modifier.height(8.dp))
                    
                    // Divider with "OR"
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Divider(
                            modifier = Modifier.weight(1f),
                            color = Color.Gray.copy(alpha = 0.3f),
                            thickness = 1.dp
                        )
                        Text(
                            text = "  OR  ",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.Gray,
                            modifier = Modifier.padding(horizontal = 8.dp)
                        )
                        Divider(
                            modifier = Modifier.weight(1f),
                            color = Color.Gray.copy(alpha = 0.3f),
                            thickness = 1.dp
                        )
                    }
                    
                    Spacer(modifier = Modifier.height(8.dp))
                    
                    // Register Button with enhanced styling
                    OutlinedButton(
                        onClick = onNavigateToRegister,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(58.dp),
                        colors = ButtonDefaults.outlinedButtonColors(
                            contentColor = PrimaryBlue
                        ),
                        border = ButtonDefaults.outlinedButtonBorder.copy(
                            width = 2.dp,
                            brush = Brush.horizontalGradient(
                                colors = listOf(PrimaryBlue, PrimaryBlueLight)
                            )
                        ),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Text(
                            text = "CREATE ACCOUNT",
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 0.5.sp
                        )
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

