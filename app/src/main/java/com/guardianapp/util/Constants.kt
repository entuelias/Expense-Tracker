package com.guardianapp.util

object Constants {
    // Database
    const val DATABASE_NAME = "guardian_db"

    // API
    const val BASE_URL = "http://10.0.2.2:3000/"  // Local development server
    const val API_TIMEOUT = 30L

    // Shared Preferences
    const val PREF_NAME = "guardian_preferences"
    const val KEY_USER_TOKEN = "user_token"
    const val KEY_USER_ID = "user_id"

    // Validation
    const val MIN_PASSWORD_LENGTH = 6
    const val MAX_NAME_LENGTH = 50
    const val PHONE_NUMBER_PATTERN = "^[+]?[0-9]{10,13}$"
    const val EMAIL_PATTERN = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
}
