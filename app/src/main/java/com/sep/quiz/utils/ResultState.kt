package com.sep.quiz.utils

import com.sep.quiz.utils.callAdapter.BaseNetworkResponse
import com.sep.quiz.utils.callAdapter.NetworkResponse
import com.sep.quiz.utils.callAdapter.ServerError
import retrofit2.Response


sealed class ResultState<out T> {
    data class Success<T>(val data: T) : ResultState<T>()

    data class Failure(val code: String, val error: String) : ResultState<Nothing>()

    data class Exception(val error: Throwable) : ResultState<Nothing>() {
        init {
            error.printStackTrace()
        }
    }

    val isSuccess: Boolean
        get() = this is Success
}


suspend fun <T : BaseNetworkResponse, S> NetworkResponse<T>.toResultState(
    onFailure: (suspend (error: ServerError) -> Unit)? = null,
    onSuccess: suspend (T) -> ResultState.Success<S>
): ResultState<S> {
    return try {
        when (val result = this) {
            is NetworkResponse.ApiError -> {
                onFailure?.invoke(result.error)
                com.sep.quiz.utils.ResultState.Failure(
                    result.error.error.orEmpty(),
                    error = result.error.path.orEmpty()
                )
            }

            is NetworkResponse.Exception -> {
                com.sep.quiz.utils.ResultState.Exception(result.throwable)
            }

            is NetworkResponse.Success -> {
                onSuccess(result.data!!) // if data is null, catch as Exception
            }
        }
    } catch (e: Exception) {
        com.sep.quiz.utils.ResultState.Exception(Throwable(e))
    }
}