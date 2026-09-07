package error

sealed interface AppError

sealed interface DomainError : AppError {
    data object InvalidUser: DomainError
}

sealed interface ApplicationError: AppError {

}

sealed interface DataError: AppError {

}