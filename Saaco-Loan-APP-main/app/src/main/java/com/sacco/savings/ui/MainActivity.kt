package com.sacco.savings.ui

import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sacco.savings.SaccoApplication
import com.sacco.savings.ui.screens.AboutUsScreen
import com.sacco.savings.ui.screens.AccountScreen
import com.sacco.savings.ui.screens.AllDepositsScreen
import com.sacco.savings.ui.screens.ChangePasswordScreen
import com.sacco.savings.ui.screens.DashboardScreen
import com.sacco.savings.ui.screens.LoginScreen
import com.sacco.savings.ui.screens.MembersScreen
import com.sacco.savings.ui.screens.PrivacyPolicyScreen
import com.sacco.savings.ui.screens.RegisterScreen
import com.sacco.savings.ui.screens.SettingsScreen
import com.sacco.savings.ui.screens.TermsAndConditionsScreen
import com.sacco.savings.ui.theme.SaccoSavingsTheme
import com.sacco.savings.ui.viewmodel.AccountViewModel
import com.sacco.savings.ui.viewmodel.AccountViewModelFactory
import com.sacco.savings.ui.viewmodel.AuthViewModel
import com.sacco.savings.ui.viewmodel.AuthViewModelFactory

class MainActivity : ComponentActivity() {
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferences = getSharedPreferences("SaccoPrefs", MODE_PRIVATE)
        
        setContent {
            SaccoSavingsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SaccoSavingsApp(
                        sharedPreferences = sharedPreferences,
                        application = application as SaccoApplication
                    )
                }
            }
        }
    }
}

@Composable
fun SaccoSavingsApp(
    sharedPreferences: SharedPreferences,
    application: SaccoApplication
) {
    val navController = rememberNavController()
    val repository = application.repository
    
    val authViewModel: AuthViewModel = viewModel(
        factory = AuthViewModelFactory(repository)
    )
    
    val isLoggedIn = remember {
        mutableStateOf(sharedPreferences.getLong("userId", -1) != -1L)
    }

    NavHost(
        navController = navController,
        startDestination = if (isLoggedIn.value) "dashboard" else "login"
    ) {
        composable("login") {
            LoginScreen(
                authViewModel = authViewModel,
                onLoginSuccess = { userId ->
                    sharedPreferences.edit().putLong("userId", userId).apply()
                    isLoggedIn.value = true
                    navController.navigate("dashboard") {
                        popUpTo("login") { inclusive = true }
                    }
                },
                onNavigateToRegister = {
                    navController.navigate("register")
                }
            )
        }
        
        composable("register") {
            RegisterScreen(
                authViewModel = authViewModel,
                onRegisterSuccess = { userId ->
                    sharedPreferences.edit().putLong("userId", userId).apply()
                    isLoggedIn.value = true
                    navController.navigate("dashboard") {
                        popUpTo("register") { inclusive = true }
                    }
                }
            )
        }
        
        composable("dashboard") {
            val accountViewModel: AccountViewModel = viewModel(
                factory = AccountViewModelFactory(repository)
            )
            val userId = sharedPreferences.getLong("userId", -1)
            
            DashboardScreen(
                accountViewModel = accountViewModel,
                userId = userId,
                repository = repository,
                onLogout = {
                    sharedPreferences.edit().clear().apply()
                    isLoggedIn.value = false
                    // Clear the entire back stack and navigate to login
                    navController.navigate("login") {
                        // Pop all destinations from the back stack
                        popUpTo(0) { inclusive = true }
                        // Prevent multiple instances of login in the back stack
                        launchSingleTop = true
                        // Restore state when navigating back to login
                        restoreState = false
                    }
                },
                onNavigateToTransactions = {
                    navController.navigate("transactions")
                },
                onNavigateToMembers = {
                    navController.navigate("members")
                },
                onNavigateToAccount = {
                    navController.navigate("account")
                },
                onNavigateToSettings = {
                    navController.navigate("settings")
                }
            )
        }
        
        composable("transactions") {
            AllDepositsScreen(
                repository = repository,
                onBack = {
                    navController.popBackStack()
                }
            )
        }
        
        composable("members") {
            MembersScreen(
                application = application,
                onBack = {
                    navController.popBackStack()
                }
            )
        }
        
        composable("account") {
            val userId = sharedPreferences.getLong("userId", -1)
            AccountScreen(
                repository = repository,
                userId = userId,
                onBack = {
                    navController.popBackStack()
                }
            )
        }
        
        composable("settings") {
            SettingsScreen(
                onBack = {
                    navController.popBackStack()
                },
                onNavigateToChangePassword = {
                    navController.navigate("changePassword")
                },
                onNavigateToAboutUs = {
                    navController.navigate("aboutUs")
                },
                onNavigateToTermsAndConditions = {
                    navController.navigate("termsAndConditions")
                },
                onNavigateToPrivacyPolicy = {
                    navController.navigate("privacyPolicy")
                }
            )
        }
        
        composable("changePassword") {
            ChangePasswordScreen(
                onBack = {
                    navController.popBackStack()
                },
                onPasswordChanged = {
                    navController.popBackStack()
                }
            )
        }
        
        composable("aboutUs") {
            AboutUsScreen(
                onBack = {
                    navController.popBackStack()
                }
            )
        }
        
        composable("termsAndConditions") {
            TermsAndConditionsScreen(
                onBack = {
                    navController.popBackStack()
                }
            )
        }
        
        composable("privacyPolicy") {
            PrivacyPolicyScreen(
                onBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}
