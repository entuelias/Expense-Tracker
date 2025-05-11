package com.guardianapp.ui.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.delay

class SplashViewModel : ViewModel() {
    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    fun startSplash() {
        viewModelScope.launch {
            delay(2000)
            _isLoading.value = false
        }
    }

    fun checkAuthStatus(): Boolean {
        // TODO: Implement actual authentication check
        return false
    }
}