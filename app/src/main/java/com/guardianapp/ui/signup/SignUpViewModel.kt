package com.guardianapp.ui.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.delay
import android.util.Patterns
import java.util.Calendar
import java.text.SimpleDateFormat
import java.util.Locale


class SignUpViewModel : ViewModel(){
    private val _username = MutableStateFlow("")
    val username: StateFlow<String> = _username.asStateFlow()

    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email.asStateFlow()

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password.asStateFlow()

    private val _selectedMonth = MutableStateFlow("")
    val selectedMonth: StateFlow<String> = _selectedMonth.asStateFlow()

    private val _selectedDate = MutableStateFlow("")
    val selectedDate: StateFlow<String> = _selectedDate.asStateFlow()

    private val _selectedYear = MutableStateFlow("")
    val selectedYear: StateFlow<String> = _selectedYear.asStateFlow()

    private val _selectedBloodType = MutableStateFlow("")
    val selectedBloodType: StateFlow<String> = _selectedBloodType.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _isError = MutableStateFlow(false)
    val isError: StateFlow<Boolean> = _isError.asStateFlow()

    private val _errorMessage = MutableStateFlow("")
    val errorMessage: StateFlow<String> = _errorMessage.asStateFlow()

    private val _signupSuccess = MutableStateFlow(false)
    val signupSuccess: StateFlow<Boolean> = _signupSuccess.asStateFlow()

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
            try {
                _isLoading.value = true
                _isError.value = false
                _errorMessage.value = ""

                // TODO: Implement actual signup logic when backend is ready
                delay(1000)
                _signupSuccess.value = true

            } catch (e: Exception) {
                _errorMessage.value = e.message ?: "Signup failed"
                _isError.value = true
            } finally {
                _isLoading.value = false
            }
        }
    }

    private fun validateInput(): Boolean {
        if (username.value.isEmpty() || email.value.isEmpty() || password.value.isEmpty() ||
            selectedMonth.value.isEmpty() || selectedDate.value.isEmpty() ||
            selectedYear.value.isEmpty() || selectedBloodType.value.isEmpty()
        ) {
            _errorMessage.value = "Please fill in all fields"
            _isError.value = true
            return false
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email.value).matches()) {
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
            val dateStr = "${selectedMonth.value} ${selectedDate.value}, ${selectedYear.value}"
            val sdf = SimpleDateFormat("MMMM dd, yyyy", Locale.US)
            sdf.isLenient = false
            val inputDate = sdf.parse(dateStr)
            val calendar = Calendar.getInstance()

            if (inputDate == null || inputDate.after(calendar.time)) {
                _errorMessage.value = "Date of birth cannot be in the future"
                _isError.value = true
                return false
            }

            // Validate minimum age (optional)
            calendar.add(Calendar.YEAR, -13) // For example, minimum age of 13
            if (inputDate.after(calendar.time)) {
                _errorMessage.value = "You must be at least 13 years old"
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

    fun resetSignupSuccess() {
        _signupSuccess.value = false
    }
}