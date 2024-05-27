package com.slas.healthendar.controller

import com.slas.healthendar.computation.auth.IAuthService
import com.slas.healthendar.entity.LoginCredentials
import com.slas.healthendar.entity.RegisterCredentials

class AuthController(
    private val service: IAuthService
) {
    fun registerUser(
        credentials: RegisterCredentials,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        service.registerUser(credentials, onSuccess, onError)
    }

    fun loginUser(
        loginCredentials: LoginCredentials,
        onWrongCredentials: (String) -> Unit,
        onValidCredentials: (String) -> Unit
    ) {
        service.loginUser(loginCredentials, onWrongCredentials, onValidCredentials)
    }
}
