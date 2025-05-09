package com.guardianapp.util

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LifecycleCoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

// Context Extensions
fun Context.showToast(message: String,duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}

// String Extensions
fun String.isValidEmail(): Boolean {
    return this.matches(Constants.EMAIL_PATTERN.toRegex())
}

fun String.isValidPhone(): Boolean {
    return this.matches(Constants.PHONE_NUMBER_PATTERN.toRegex())
}

fun String.isValidPassword(): Boolean {
    return this.length >= Constants.MIN_PASSWORD_LENGTH
}

// Flow Extensions
fun <T> Flow<Resource<T>>.collectResource(
    lifecycleScope: LifecycleCoroutineScope,
    onSuccess: (T) -> Unit,
    onError: (String) -> Unit,
    onLoading: () -> Unit = {}
) {
    lifecycleScope.launch {
        this@collectResource.first().let { resource ->
            when (resource) {
                is Resource.Success -> resource.data?.let(onSuccess)
                is Resource.Error -> resource.message?.let(onError)
                is Resource.Loading -> onLoading()
            }
        }
    }
}

// Result Extensions
suspend fun <T> safeApiCall(apiCall: suspend () -> T): Resource<T> {
    return try {
        Resource.Success(apiCall())
    } catch (e: Exception) {
        Resource.Error(e.message ?: "An unknown error occurred")
    }
}

// Date Extensions
fun Long.toFormattedDate(): String {
    return java.text.SimpleDateFormat("dd/MM/yyyy", java.util.Locale.getDefault())
        .format(java.util.Date(this))
}
