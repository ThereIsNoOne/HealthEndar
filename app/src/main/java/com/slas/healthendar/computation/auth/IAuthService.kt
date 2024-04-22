package com.slas.healthendar.computation.auth

import com.slas.healthendar.entity.OperationResult
import com.slas.healthendar.entity.RegisterCredentials

interface IAuthService {
    fun registerUser(
        credentials: RegisterCredentials,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    )
}