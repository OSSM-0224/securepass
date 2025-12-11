package com.oysm.securepass

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModelProvider
import com.oysm.securepass.data.local.PasswordDatabase
import com.oysm.securepass.data.models.PasswordEntity
import com.oysm.securepass.data.repository.PasswordRepository
import com.oysm.securepass.ui.screens.AccountDetailsBottomSheet
import com.oysm.securepass.ui.screens.AuthenticationScreen
import com.oysm.securepass.ui.screens.AddPasswordBottomSheet
import com.oysm.securepass.ui.screens.EditPasswordScreen
import com.oysm.securepass.ui.screens.HomeScreen
import com.oysm.securepass.ui.theme.SecurePassTheme
import com.oysm.securepass.viewmodel.PasswordViewModel
import androidx.compose.runtime.collectAsState

class MainActivity : ComponentActivity() {

    private lateinit var viewModel: PasswordViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val database = PasswordDatabase.getDatabase(this)
        val repository = PasswordRepository(database.passwordDao())
        viewModel = ViewModelProvider(this, PasswordViewModelFactory(repository))
            .get(PasswordViewModel::class.java)

        setContent {
            SecurePassTheme {

                var isAuthenticated by remember { mutableStateOf(false) }
                var currentScreen by remember { mutableStateOf<Screen>(Screen.Home) }

                var selectedPassword by remember { mutableStateOf<PasswordEntity?>(null) }
                var showAddSheet by remember { mutableStateOf(false) }
                var showDetailsSheet by remember { mutableStateOf(false) }
                val decryptedPassword = viewModel.decryptedPassword.collectAsState().value

                if (!isAuthenticated) {
                    AuthenticationScreen(
                        onAuthSuccess = { isAuthenticated = true },
                        isBiometricAvailable = true
                    )
                } else {

                    when (currentScreen) {

                        Screen.Home -> {
                            HomeScreen(
                                viewModel = viewModel,
                                onAddClick = { showAddSheet = true },
                                onPasswordClick = { password ->
                                    selectedPassword = password
                                    viewModel.selectPassword(password)
                                    showDetailsSheet = true
                                },
                                onEditClick = { password ->
                                    selectedPassword = password
                                    currentScreen = Screen.EditPassword
                                }
                            )


                            // Add password bottom sheet
                            if (showAddSheet) {
                                AddPasswordBottomSheet(
                                    viewModel = viewModel,
                                    onDismiss = {
                                        showAddSheet = false
                                    }
                                )
                            }

                            // Full details bottom sheet
                            if (showDetailsSheet && selectedPassword != null) {
                                AccountDetailsBottomSheet(
                                    password = selectedPassword!!,
                                    decryptedPassword = decryptedPassword,
                                    viewModel = viewModel,
                                    onDismiss = {
                                        showDetailsSheet = false
                                        viewModel.clearSelectedPassword()
                                    },
                                    onEditClick = {
                                        currentScreen = Screen.EditPassword
                                        showDetailsSheet = false
                                    }
                                )
                            }
                        }

                        Screen.EditPassword -> {
                            selectedPassword?.let {
                                EditPasswordScreen(
                                    password = it,
                                    viewModel = viewModel,
                                    onBackClick = { currentScreen = Screen.Home },
                                    onSuccess = { currentScreen = Screen.Home }
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    sealed class Screen {
        object Home : Screen()
        object EditPassword : Screen()
    }
}

class PasswordViewModelFactory(private val repository: PasswordRepository) :
    androidx.lifecycle.ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : androidx.lifecycle.ViewModel> create(modelClass: Class<T>): T {
        return PasswordViewModel(repository) as T
    }
}
