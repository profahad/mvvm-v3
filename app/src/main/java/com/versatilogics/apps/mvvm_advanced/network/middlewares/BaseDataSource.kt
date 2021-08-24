package com.versatilogics.apps.mvvm_advanced.network.middlewares

import com.google.gson.stream.MalformedJsonException
import com.versatilogics.apps.mvvm_advanced.config.CONSTANTS
import com.versatilogics.apps.mvvm_advanced.network.enums.Response
import com.versatilogics.apps.mvvm_advanced.network.exceptions.*
import retrofit2.HttpException
import timber.log.Timber
import java.net.ConnectException
import java.net.HttpURLConnection
import java.net.SocketTimeoutException
import java.net.UnknownHostException

abstract class BaseDataSource {

    protected suspend fun <T> getResult(call: suspend () -> retrofit2.Response<T>): Response<T> {
        val response: retrofit2.Response<T>
        try {
            response = call()
        } catch (e: MalformedJsonException) {
            return error(InternalServerException().message)
        } catch (t: Throwable) {
            return error(mapToNetworkError(t).message!!)
        }
        if (response.isSuccessful) {
            val body = response.body()
            if (body != null) return Response.success(
                body
            )
        } else {
            val errorBody = response.errorBody()
            return if (errorBody != null) {
                error(mapApiException(response.code()).message!!)
            } else {
                error(mapApiException(0).message!!)
            }
        }
        return error(HttpException(response).message!!)
    }

    private fun mapApiException(code: Int): Exception {
        return when (code) {
            HttpURLConnection.HTTP_NOT_FOUND -> NotFoundException()
            HttpURLConnection.HTTP_UNAUTHORIZED -> UnAuthorizedException()
            HttpURLConnection.HTTP_INTERNAL_ERROR -> InternalServerException()
            else -> UnKnownException()
        }
    }

    private fun mapToNetworkError(t: Throwable): Exception {
        return when (t) {
            is SocketTimeoutException -> SocketTimeoutException("Connection Timed Out")
            is ConnectException -> NoInternetException()
            is UnknownHostException -> NoInternetException()
            else -> UnKnownException()
        }
    }

    private fun <T> error(message: String): Response<T> {
        if (!CONSTANTS.CONFIG.isProductionMode()) Timber.d(message)
        return Response.error(
            message = CONSTANTS.NETWORK.SOMETHING_WENT_WRONG.format(
                message
            )
        )
    }

}