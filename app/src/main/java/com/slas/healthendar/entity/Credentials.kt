package com.slas.healthendar.entity

data class RegisterCredentials(
    val username: String,
    val email: String,
    val password: String,
    val repeatPassword: String
)

data class LoginCredentials(
    val email: String,
    val password: String
)
