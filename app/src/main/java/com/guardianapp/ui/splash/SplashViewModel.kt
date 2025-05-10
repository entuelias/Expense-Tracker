package com.guardianapp.ui.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SplashViewModel : ViewModel() {
    private val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()

    // TODO: Replace with actual API call when infrastructure is ready
    fun checkAuthStatus(): Boolean {
        return false // Mock: Return false to always navigate to login
    }

    fun startSplash() {
        viewModelScope.launch {
            delay(2000) // Splash delay
            _isLoading.value = false
        }
    }
}