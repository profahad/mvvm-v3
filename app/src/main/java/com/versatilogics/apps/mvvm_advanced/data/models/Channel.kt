package com.versatilogics.apps.mvvm_advanced.data.models

import com.google.gson.annotations.SerializedName

data class Channel(
    @SerializedName("id") var id: Int?,
    @SerializedName("title") var title: String?,
    @SerializedName("logo_url") var logoUrl: String?
)