package com.guardianapp.ui.profile

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel() {
    private val _profileImage = MutableStateFlow<Uri?>(null)
    val profileImage = _profileImage.asStateFlow()

    private val _name = MutableStateFlow("")
    val name = _name.asStateFlow()

    private val _email = MutableStateFlow("")
    val email = _email.asStateFlow()

    private val _dateOfBirth = MutableStateFlow("")
    val dateOfBirth = _dateOfBirth.asStateFlow()

    private val _bloodType = MutableStateFlow("")
    val bloodType = _bloodType.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error = _error.asStateFlow()

    private val _isImageUploading = MutableStateFlow(false)
    val isImageUploading = _isImageUploading.asStateFlow()

    init {
        loadProfileData()
    }

    private fun loadProfileData() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                // TODO: Implement actual API calls when backend is ready
                // For now, use mock data
                _name.value = "Yasrib Aman"
                _email.value = "yasribaman@gmail.com"
                _dateOfBirth.value = "January 15, 1990"
                _bloodType.value = "O-"
            } catch (e: Exception) {
                _error.value = "Failed to load profile data"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun updateProfileImage(uri: Uri?) {
        viewModelScope.launch {
            _isImageUploading.value = true
            try {
                // TODO: Implement actual image upload logic
                // For now, just simulate a delay
                kotlinx.coroutines.delay(1000)
                _profileImage.value = uri
            } catch (e: Exception) {
                _error.value = "Failed to upload profile image"
            } finally {
                _isImageUploading.value = false
            }
        }
    }

    fun updateName(value: String) {
        if (validateName(value)) {
            _name.value = value
        }
    }

    fun updateEmail(value: String) {
        if (validateEmail(value)) {
            _email.value = value
        }
    }

    fun updateDateOfBirth(value: String) {
        if (validateDateOfBirth(value)) {
            _dateOfBirth.value = value
        }
    }

    fun updateBloodType(value: String) {
        if (validateBloodType(value)) {
            _bloodType.value = value
        }
    }

    private fun validateName(value: String): Boolean {
        if (value.trim().length < 2) {
            _error.value = "Name must be at least 2 characters long"
            return false
        }
        return true
    }

    private fun validateEmail(value: String): Boolean {
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(value).matches()) {
            _error.value = "Please enter a valid email address"
            return false
        }
        return true
    }

    private fun validateDateOfBirth(value: String): Boolean {
        try {
            val date = java.time.LocalDate.parse(
                value,
                java.time.format.DateTimeFormatter.ofPattern("MMMM d, yyyy")
            )
            val now = java.time.LocalDate.now()
            if (date.isAfter(now)) {
                _error.value = "Date of birth cannot be in the future"
                return false
            }
            return true
        } catch (e: Exception) {
            _error.value = "Please enter a valid date in the format 'Month DD, YYYY'"
            return false
        }
    }

    private fun validateBloodType(value: String): Boolean {
        val validBloodTypes = listOf("A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-")
        if (!validBloodTypes.contains(value)) {
            _error.value = "Please select a valid blood type"
            return false
        }
        return true
    }

    fun saveProfileChanges() {
        if (!validateInput()) return

        viewModelScope.launch {
            _isLoading.value = true
            try {
                // TODO: Implement actual API calls when backend is ready
                // For now, just simulate a delay
                kotlinx.coroutines.delay(1000)
            } catch (e: Exception) {
                _error.value = "Failed to save profile changes"
            } finally {
                _isLoading.value = false
            }
        }
    }

    private fun validateInput(): Boolean {
        return validateName(name.value) &&
               validateEmail(email.value) &&
               validateDateOfBirth(dateOfBirth.value) &&
               validateBloodType(bloodType.value)
    }

    fun clearError() {
        _error.value = null
    }

    override fun onCleared() {
        super.onCleared()
        _profileImage.value = null
    }
}