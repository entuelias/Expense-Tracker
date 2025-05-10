package com.guardianapp.ui.medical

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MedicalInfoViewModel : ViewModel() {
    private val _height = MutableStateFlow("")
    val height = _height.asStateFlow()

    private val _weight = MutableStateFlow("")
    val weight = _weight.asStateFlow()

    private val _bloodPressure = MutableStateFlow("")
    val bloodPressure = _bloodPressure.asStateFlow()

    private val _allergies = MutableStateFlow("")
    val allergies = _allergies.asStateFlow()

    private val _medications = MutableStateFlow("")
    val medications = _medications.asStateFlow()

    private val _chronicConditions = MutableStateFlow("")
    val chronicConditions = _chronicConditions.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _isError = MutableStateFlow(false)
    val isError = _isError.asStateFlow()

    private val _errorMessage = MutableStateFlow("")
    val errorMessage = _errorMessage.asStateFlow()

    init {
        loadMedicalInfo()
    }

    private fun loadMedicalInfo() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                // TODO: Implement actual API calls when backend is ready
                // For now, use mock data
                _height.value = "170"
                _weight.value = "65"
                _bloodPressure.value = "120/80"
                _allergies.value = "None"
                _medications.value = "None"
                _chronicConditions.value = "None"
            } catch (e: Exception) {
                _errorMessage.value = "Failed to load medical information"
                _isError.value = true
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun updateHeight(value: String) {
        if (validateHeight(value)) {
            _height.value = value
        }
    }

    fun updateWeight(value: String) {
        if (validateWeight(value)) {
            _weight.value = value
        }
    }

    fun updateBloodPressure(value: String) {
        if (validateBloodPressure(value)) {
            _bloodPressure.value = value
        }
    }

    fun updateAllergies(value: String) {
        _allergies.value = value
    }

    fun updateMedications(value: String) {
        _medications.value = value
    }

    fun updateChronicConditions(value: String) {
        _chronicConditions.value = value
    }

    private fun validateHeight(value: String): Boolean {
        return try {
            val heightValue = value.toFloat()
            if (heightValue < 50 || heightValue > 250) {
                _errorMessage.value = "Height must be between 50 and 250 cm"
                _isError.value = true
                false
            } else {
                true
            }
        } catch (e: NumberFormatException) {
            _errorMessage.value = "Please enter a valid height in centimeters"
            _isError.value = true
            false
        }
    }

    private fun validateWeight(value: String): Boolean {
        return try {
            val weightValue = value.toFloat()
            if (weightValue < 20 || weightValue > 300) {
                _errorMessage.value = "Weight must be between 20 and 300 kg"
                _isError.value = true
                false
            } else {
                true
            }
        } catch (e: NumberFormatException) {
            _errorMessage.value = "Please enter a valid weight in kilograms"
            _isError.value = true
            false
        }
    }

    private fun validateBloodPressure(value: String): Boolean {
        val bpPattern = Regex("^\\d{2,3}/\\d{2,3}$")
        if (!bpPattern.matches(value)) {
            _errorMessage.value = "Blood pressure must be in format systolic/diastolic (e.g., 120/80)"
            _isError.value = true
            return false
        }

        val (systolic, diastolic) = value.split("/").map { it.toInt() }
        if (systolic < 70 || systolic > 200 || diastolic < 40 || diastolic > 130) {
            _errorMessage.value = "Blood pressure values are out of normal range"
            _isError.value = true
            return false
        }

        return true
    }

    fun saveMedicalInfo() {
        if (!validateInput()) return

        viewModelScope.launch {
            _isLoading.value = true
            _isError.value = false

            try {
                // TODO: Implement actual API calls when backend is ready
                // For now, just simulate a delay
                kotlinx.coroutines.delay(1000)
            } catch (e: Exception) {
                _errorMessage.value = "Failed to save medical information"
                _isError.value = true
            } finally {
                _isLoading.value = false
            }
        }
    }

    private fun validateInput(): Boolean {
        if (height.value.isEmpty() || weight.value.isEmpty() || bloodPressure.value.isEmpty()) {
            _errorMessage.value = "Please fill in all required fields"
            _isError.value = true
            return false
        }

        return validateHeight(height.value) && 
               validateWeight(weight.value) && 
               validateBloodPressure(bloodPressure.value)
    }

    fun clearError() {
        _isError.value = false
        _errorMessage.value = ""
    }

    fun refreshMedicalInfo() {
        loadMedicalInfo()
    }
}