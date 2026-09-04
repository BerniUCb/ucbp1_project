package org.ucb.appp1.signin.domain.usecase

import org.ucb.appp1.signin.domain.model.Email
import org.ucb.appp1.signin.domain.model.Password

class AuthenticateUseCase {
    suspend fun invoke(email: Email, password: Password) : Result<Boolean> {
        return if (email.value == "calyr.software@gmail.com" && password.value == "123456") {
            Result.success(true)
        } else {
            Result.failure(Exception("Invalid credential"))
        }
    }
}