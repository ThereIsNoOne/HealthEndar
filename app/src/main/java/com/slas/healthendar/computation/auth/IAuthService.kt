package com.slas.healthendar.computation.auth

import com.slas.healthendar.entity.LoginCredentials
import com.slas.healthendar.entity.RegisterCredentials

interface IAuthService {
    fun registerUser(
        credentials: RegisterCredentials,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    )

    fun loginUser(
        loginCredentials: LoginCredentials,
        onWrongCredentials: (String) -> Unit,
        onValidCredentials: (String) -> Unit
    )
}