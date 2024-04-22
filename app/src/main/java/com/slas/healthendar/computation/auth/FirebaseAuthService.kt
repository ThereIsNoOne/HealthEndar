package com.slas.healthendar.computation.auth

import com.slas.healthendar.entity.OperationResult
import com.slas.healthendar.entity.RegisterCredentials
import com.google.firebase.auth.FirebaseAuth

class FirebaseAuthService : IAuthService {
    override fun registerUser(
        credentials: RegisterCredentials,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {

        FirebaseAuth.getInstance()
            .createUserWithEmailAndPassword(credentials.email, credentials.password)
            .addOnCompleteListener {
                if (!it.isSuccessful) {
                    onError(it.exception!!.message.toString())
                    return@addOnCompleteListener
                }
                FirebaseAuth.getInstance().signOut()
                onSuccess()
            }

    }

}