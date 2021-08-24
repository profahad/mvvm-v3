package com.versatilogics.apps.mvvm_advanced.network.response

import com.google.gson.annotations.SerializedName

data class PagingData<T>(

    @SerializedName("current_page") var currentPage: Int,
    @SerializedName("data") var data: List<T>,
    @SerializedName("first_page_url") var firstPageUrl: String,
    @SerializedName("from") var from: Int,
    @SerializedName("last_page") var lastPage: Int,
    @SerializedName("last_page_url") var lastPageUrl: String,
    @SerializedName("next_page_url") var nextPageUrl: String?,
    @SerializedName("path") var path: String,
    @SerializedName("per_page") var perPage: Int,
    @SerializedName("prev_page_url") var prevPageUrl: String,
    @SerializedName("to") var to: Int,
    @SerializedName("total") var total: Int

)
