package com.guardianapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.delay

class HomeViewModel : ViewModel() {
    private val _userName = MutableStateFlow("")
    val userName: StateFlow<String> = _userName.asStateFlow()

    private val _nextAppointment = MutableStateFlow<String?>(null)
    val nextAppointment: StateFlow<String?> = _nextAppointment.asStateFlow()

    private val _bloodType = MutableStateFlow<String?>(null)
    val bloodType: StateFlow<String?> = _bloodType.asStateFlow()

    private val _notifications = MutableStateFlow<List<String>>(emptyList())
    val notifications: StateFlow<List<String>> = _notifications.asStateFlow()

    private val _upcomingAppointments = MutableStateFlow<List<String>>(emptyList())
    val upcomingAppointments: StateFlow<List<String>> = _upcomingAppointments.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    init {
        loadUserData()
    }

    private fun loadUserData() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                // Simulate network delay
                delay(1000)

                // Mock data
                _userName.value = "Entisar"
                _nextAppointment.value = "Tomorrow at 10 AM"
                _bloodType.value = "O+"
                _notifications.value = listOf(
                    "Reminder: Take your medication at 2 PM",
                    "Your next appointment is tomorrow",
                    "New health tips available"
                )
                _upcomingAppointments.value = listOf(
                    "General Checkup - Tomorrow at 10 AM",
                    "Dental Appointment - Next Week Tuesday at 2 PM",
                    "Eye Examination - Next Month 15th at 11 AM"
                )
            } catch (e: Exception) {
                _error.value = "Failed to load user data"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun refreshUserData() {
        loadUserData()
    }

    fun clearError() {
        _error.value = null
    }

    fun dismissNotification(notificationIndex: Int) {
        val currentNotifications = _notifications.value.toMutableList()
        if (notificationIndex in currentNotifications.indices) {
            currentNotifications.removeAt(notificationIndex)
            _notifications.value = currentNotifications
        }
    }

    fun clearAllNotifications() {
        _notifications.value = emptyList()
    }
}