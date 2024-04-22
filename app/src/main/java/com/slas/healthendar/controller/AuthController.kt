package com.slas.healthendar.controller

import com.slas.healthendar.computation.auth.IAuthService
import com.slas.healthendar.entity.OperationResult
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
}
