package com.oysm.securepass.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.oysm.securepass.data.models.PasswordEntity
import com.oysm.securepass.ui.components.PasswordListItem
import com.oysm.securepass.viewmodel.PasswordViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: PasswordViewModel,
    onAddClick: () -> Unit,
    onPasswordClick: (PasswordEntity) -> Unit,
    onEditClick: (PasswordEntity) -> Unit
) {

    // ----- STATES -----
    var showAddSheet by remember { mutableStateOf(false) }
    var showDetailSheet by remember { mutableStateOf<PasswordEntity?>(null) }

    val passwords by viewModel.allPasswords.collectAsState(initial = emptyList())

    // ----- UI -----
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Password Manager",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFF5F5F5)
                )
            )
        },

        floatingActionButton = {
            FloatingActionButton(
                onClick = { showAddSheet = true },
                containerColor = Color(0xFF2196F3),
                contentColor = Color.White,
                shape = RoundedCornerShape(16.dp)
            ) {
                Icon(Icons.Filled.Add, contentDescription = "Add Password")
            }
        },

        containerColor = Color(0xFFF5F5F5)
    ) { innerPadding ->

        // EMPTY STATE
        if (passwords.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "No passwords saved yet\nTap + to add one",
                    fontSize = 16.sp,
                    color = Color.Gray
                )
            }
        } else {

            // PASSWORD LIST
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(passwords) { password ->
                    PasswordListItem(
                        password = password,
                        onClick = { showDetailSheet = password }
                    )
                }
            }
        }

        // ----- ADD PASSWORD BOTTOM SHEET -----
        if (showAddSheet) {
            AddPasswordBottomSheet(
                viewModel = viewModel,
                onDismiss = { showAddSheet = false }
            )
        }

        // ----- DETAILS BOTTOM SHEET -----
        showDetailSheet?.let { password ->
            AccountDetailsBottomSheet(
                password = password,
                decryptedPassword = viewModel.getDecryptedPassword(password.encryptedPassword),
                viewModel = viewModel,
                onDismiss = { showDetailSheet = null },
                onEditClick = {
                    showDetailSheet = null
                    onEditClick(password)     // OPEN EDIT SCREEN
                }
            )
        }

    }
}
