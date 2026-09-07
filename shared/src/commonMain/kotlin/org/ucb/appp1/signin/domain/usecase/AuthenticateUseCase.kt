package org.ucb.appp1.signin.domain.usecase

import arrow.core.Either
import arrow.core.left
import arrow.core.raise.either
import arrow.core.right
import error.AppError
import error.DomainError
import org.ucb.appp1.signin.domain.model.Email
import org.ucb.appp1.signin.domain.model.Password

class AuthenticateUseCase {
    suspend fun invoke(email: Email, password: Password) : Either<AppError, Boolean> = either {
        return if (email.value == "calyr.software@gmail.com" && password.value == "123456") {
            Either.Right(true)
        } else {
            Either.Left(DomainError.InvalidUser)
        }
    }
}
