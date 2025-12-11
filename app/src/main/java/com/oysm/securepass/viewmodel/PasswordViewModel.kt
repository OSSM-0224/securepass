package com.oysm.securepass.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oysm.securepass.data.models.PasswordEntity
import com.oysm.securepass.data.repository.PasswordRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PasswordViewModel(private val repository: PasswordRepository) : ViewModel() {

    val allPasswords: Flow<List<PasswordEntity>> = repository.allPasswords

    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState

    private val _selectedPassword = MutableStateFlow<PasswordEntity?>(null)
    val selectedPassword: StateFlow<PasswordEntity?> = _selectedPassword

    private val _decryptedPassword = MutableStateFlow("")
    val decryptedPassword: StateFlow<String> = _decryptedPassword

    fun addPassword(accountType: String, username: String, password: String) {
        if (!validateInputs(accountType, username, password)) {
            _uiState.value = UiState.Error("Please fill all fields")
            return
        }

        viewModelScope.launch {
            try {
                repository.insertPassword(accountType, username, password)
                _uiState.value = UiState.Success("Password added successfully")
            } catch (e: Exception) {
                _uiState.value = UiState.Error("Error adding password: ${e.message}")
            }
        }
    }

    fun updatePassword(id: Int, accountType: String, username: String, password: String) {
        if (!validateInputs(accountType, username, password)) {
            _uiState.value = UiState.Error("Please fill all fields")
            return
        }

        viewModelScope.launch {
            try {
                repository.updatePassword(id, accountType, username, password)
                _uiState.value = UiState.Success("Password updated successfully")
            } catch (e: Exception) {
                _uiState.value = UiState.Error("Error updating password: ${e.message}")
            }
        }
    }

    fun deletePassword(id: Int) {
        viewModelScope.launch {
            try {
                repository.deletePassword(id)
                _selectedPassword.value = null
                _uiState.value = UiState.Success("Password deleted successfully")
            } catch (e: Exception) {
                _uiState.value = UiState.Error("Error deleting password: ${e.message}")
            }
        }
    }

    fun selectPassword(password: PasswordEntity) {
        _selectedPassword.value = password
        _decryptedPassword.value = repository.getDecryptedPassword(password.encryptedPassword)
    }

    fun clearSelectedPassword() {
        _selectedPassword.value = null
        _decryptedPassword.value = ""
    }

    fun clearUiState() {
        _uiState.value = UiState.Loading
    }
    fun getDecryptedPassword(encrypted: String): String {
        return repository.getDecryptedPassword(encrypted)
    }

    private fun validateInputs(accountType: String, username: String, password: String): Boolean {
        return accountType.isNotBlank() && username.isNotBlank() && password.isNotBlank()
    }

    sealed class UiState {
        object Loading : UiState()
        data class Success(val message: String) : UiState()
        data class Error(val message: String) : UiState()
    }
}