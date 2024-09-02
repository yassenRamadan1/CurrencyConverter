package org.example.currency_converter.data.remote.model

sealed interface RequestState<out T> {
    data object Idle : RequestState<Nothing>
    data object Loading : RequestState<Nothing>
    data class Success<T>(val data: T) : RequestState<T>
    data class Error(val message: String) : RequestState<Nothing>

    fun isLoading() = this is Loading
    fun isIdle() = this is Idle
    fun isSuccess() = this is Success
    fun isError() = this is Error

    fun getSuccessData () = (this as? Success)?.data
    fun getErrorData () = (this as? Error)?.message
}