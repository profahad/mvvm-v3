package com.versatilogics.apps.mvvm_advanced.network.middlewares

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import com.versatilogics.apps.mvvm_advanced.network.enums.Response
import com.versatilogics.apps.mvvm_advanced.network.enums.Status

fun <T> processResponse(
    networkCall: suspend () -> Response<T>
): LiveData<Response<T>> =
    liveData(Dispatchers.IO) {
        emit(Response.loading(null))
        val responseStatus = networkCall.invoke()
        when (responseStatus.status) {
            Status.SUCCESS -> {
                emit(Response.success(responseStatus.data!!))
            }
            else -> {
                emit(Response.error(data = null, message = responseStatus.message!!))
            }
        }
    }

fun <T> flowResponse(
    networkCall: suspend () -> Response<T>
): Flow<Response<T>> =
    flow {
        emit(Response.loading())
        val responseStatus = networkCall.invoke()
        when (responseStatus.status) {
            Status.SUCCESS -> {
                emit(Response.success(responseStatus.data!!))
            }
            else -> {
                emit(Response.error(data = null, message = responseStatus.message!!))
            }
        }
    }