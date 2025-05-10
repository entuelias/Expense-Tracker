package com.guardianapp.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private val _email = MutableStateFlow("")
    val email = _email.asStateFlow()

    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()

    private val _rememberMe = MutableStateFlow(false)
    val rememberMe = _rememberMe.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _isError = MutableStateFlow(false)
    val isError = _isError.asStateFlow()

    private val _errorMessage = MutableStateFlow("")
    val errorMessage = _errorMessage.asStateFlow()

    fun updateEmail(value: String) {
        _email.value = value
    }

    fun updatePassword(value: String) {
        _password.value = value
    }

    fun updateRememberMe(value: Boolean) {
        _rememberMe.value = value
    }

    fun login() {
        if (!validateInput()) return

        viewModelScope.launch {
            _isLoading.value = true
            _isError.value = false

            try {
                // TODO: Implement actual login logic when backend is ready
                // For now, simulate a delay and always succeed
                kotlinx.coroutines.delay(1000)
            } catch (e: Exception) {
                _errorMessage.value = "Login failed. Please try again."
                _isError.value = true
            } finally {
                _isLoading.value = false
            }
        }
    }

    private fun validateInput(): Boolean {
        if (email.value.isEmpty() || password.value.isEmpty()) {
            _errorMessage.value = "Please fill in all fields"
            _isError.value = true
            return false
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email.value).matches()) {
            _errorMessage.value = "Please enter a valid email address"
            _isError.value = true
            return false
        }

        if (password.value.length < 8) {
            _errorMessage.value = "Password must be at least 8 characters long"
            _isError.value = true
            return false
        }

        if (!password.value.matches(Regex(".*[A-Z].*"))) {
            _errorMessage.value = "Password must contain at least one uppercase letter"
            _isError.value = true
            return false
        }

        if (!password.value.matches(Regex(".*[a-z].*"))) {
            _errorMessage.value = "Password must contain at least one lowercase letter"
            _isError.value = true
            return false
        }

        if (!password.value.matches(Regex(".*\\d.*"))) {
            _errorMessage.value = "Password must contain at least one number"
            _isError.value = true
            return false
        }

        if (!password.value.matches(Regex(".*[!@#$%^&*(),.?\":{}|<>].*"))) {
            _errorMessage.value = "Password must contain at least one special character"
            _isError.value = true
            return false
        }

        return true
    }

    fun clearError() {
        _isError.value = false
        _errorMessage.value = ""
    }
}