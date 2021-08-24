package com.versatilogics.apps.mvvm_advanced.network.response

import com.google.gson.annotations.SerializedName

data class ApiResponse<T>(
    @SerializedName("success") var success: Boolean?,
    @SerializedName("data") var data: T,
    @SerializedName("message") var message: String?,
)