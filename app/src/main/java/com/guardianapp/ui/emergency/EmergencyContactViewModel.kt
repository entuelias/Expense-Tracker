package com.guardianapp.ui.emergency

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class EmergencyContactViewModel : ViewModel() {
    private val _contactName = MutableStateFlow("")
    val contactName = _contactName.asStateFlow()

    private val _relationship = MutableStateFlow("")
    val relationship = _relationship.asStateFlow()

    private val _phoneNumber = MutableStateFlow("")
    val phoneNumber = _phoneNumber.asStateFlow()

    private val _email = MutableStateFlow("")
    val email = _email.asStateFlow()

    private val _address = MutableStateFlow("")
    val address = _address.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _isError = MutableStateFlow(false)
    val isError = _isError.asStateFlow()

    private val _errorMessage = MutableStateFlow("")
    val errorMessage = _errorMessage.asStateFlow()

    init {
        loadEmergencyContact()
    }

    private fun loadEmergencyContact() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                // TODO: Implement actual API calls when backend is ready
                // For now, use mock data
                _contactName.value = "John Doe"
                _relationship.value = "Father"
                _phoneNumber.value = "+1234567890"
                _email.value = "john.doe@example.com"
                _address.value = "123 Main St, City"
            } catch (e: Exception) {
                _errorMessage.value = "Failed to load emergency contact"
                _isError.value = true
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun updateContactName(value: String) {
        if (validateContactName(value)) {
            _contactName.value = value
        }
    }

    fun updateRelationship(value: String) {
        if (validateRelationship(value)) {
            _relationship.value = value
        }
    }

    fun updatePhoneNumber(value: String) {
        if (validatePhoneNumber(value)) {
            _phoneNumber.value = value
        }
    }

    fun updateEmail(value: String) {
        if (validateEmail(value)) {
            _email.value = value
        }
    }

    fun updateAddress(value: String) {
        _address.value = value
    }

    private fun validateContactName(value: String): Boolean {
        if (value.trim().length < 2) {
            _errorMessage.value = "Contact name must be at least 2 characters long"
            _isError.value = true
            return false
        }
        return true
    }

    private fun validateRelationship(value: String): Boolean {
        if (value.trim().isEmpty()) {
            _errorMessage.value = "Please specify the relationship"
            _isError.value = true
            return false
        }
        return true
    }

    private fun validatePhoneNumber(value: String): Boolean {
        val phonePattern = Regex("^\\+?[1-9]\\d{1,14}$")
        if (!phonePattern.matches(value)) {
            _errorMessage.value = "Please enter a valid phone number (E.164 format)"
            _isError.value = true
            return false
        }
        return true
    }

    private fun validateEmail(value: String): Boolean {
        if (value.isNotEmpty() && 
            !android.util.Patterns.EMAIL_ADDRESS.matcher(value).matches()) {
            _errorMessage.value = "Please enter a valid email address"
            _isError.value = true
            return false
        }
        return true
    }

    fun saveEmergencyContact() {
        if (!validateInput()) return

        viewModelScope.launch {
            _isLoading.value = true
            _isError.value = false

            try {
                // TODO: Implement actual API calls when backend is ready
                // For now, just simulate a delay
                kotlinx.coroutines.delay(1000)
            } catch (e: Exception) {
                _errorMessage.value = "Failed to save emergency contact"
                _isError.value = true
            } finally {
                _isLoading.value = false
            }
        }
    }

    private fun validateInput(): Boolean {
        return validateContactName(contactName.value) &&
               validateRelationship(relationship.value) &&
               validatePhoneNumber(phoneNumber.value) &&
               validateEmail(email.value)
    }

    fun clearError() {
        _isError.value = false
        _errorMessage.value = ""
    }

    fun refreshEmergencyContact() {
        loadEmergencyContact()
    }
}