package com.guardianapp.ui.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SignupViewModel : ViewModel() {
    private val _username = MutableStateFlow("")
    val username = _username.asStateFlow()

    private val _email = MutableStateFlow("")
    val email = _email.asStateFlow()

    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()

    private val _selectedMonth = MutableStateFlow("")
    val selectedMonth = _selectedMonth.asStateFlow()

    private val _selectedDate = MutableStateFlow("")
    val selectedDate = _selectedDate.asStateFlow()

    private val _selectedYear = MutableStateFlow("")
    val selectedYear = _selectedYear.asStateFlow()

    private val _selectedBloodType = MutableStateFlow("")
    val selectedBloodType = _selectedBloodType.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _isError = MutableStateFlow(false)
    val isError = _isError.asStateFlow()

    private val _errorMessage = MutableStateFlow("")
    val errorMessage = _errorMessage.asStateFlow()

    fun updateUsername(value: String) {
        _username.value = value
    }

    fun updateEmail(value: String) {
        _email.value = value
    }

    fun updatePassword(value: String) {
        _password.value = value
    }

    fun updateMonth(value: String) {
        _selectedMonth.value = value
    }

    fun updateDate(value: String) {
        _selectedDate.value = value
    }

    fun updateYear(value: String) {
        _selectedYear.value = value
    }

    fun updateBloodType(value: String) {
        _selectedBloodType.value = value
    }

    fun signup() {
        if (!validateInput()) return

        viewModelScope.launch {
            _isLoading.value = true
            _isError.value = false

            // TODO: Implement actual signup logic when backend is ready
            // For now, simulate a delay and always succeed
            kotlinx.coroutines.delay(1000)

            _isLoading.value = false
        }
    }

    private fun validateInput(): Boolean {
        if (username.value.isEmpty() || email.value.isEmpty() || password.value.isEmpty() ||
            selectedMonth.value.isEmpty() || selectedDate.value.isEmpty() || 
            selectedYear.value.isEmpty() || selectedBloodType.value.isEmpty()) {
            _errorMessage.value = "Please fill in all fields"
            _isError.value = true
            return false
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email.value).matches()) {
            _errorMessage.value = "Please enter a valid email address"
            _isError.value = true
            return false
        }

        if (password.value.length < 6) {
            _errorMessage.value = "Password must be at least 6 characters long"
            _isError.value = true
            return false
        }

        try {
            val date = java.time.LocalDate.of(
                selectedYear.value.toInt(),
                java.time.Month.valueOf(selectedMonth.value.uppercase()),
                selectedDate.value.toInt()
            )
            val now = java.time.LocalDate.now()
            if (date.isAfter(now)) {
                _errorMessage.value = "Date of birth cannot be in the future"
                _isError.value = true
                return false
            }
        } catch (e: Exception) {
            _errorMessage.value = "Please enter a valid date of birth"
            _isError.value = true
            return false
        }

        val validBloodTypes = listOf("A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-")
        if (!validBloodTypes.contains(selectedBloodType.value)) {
            _errorMessage.value = "Please select a valid blood type"
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