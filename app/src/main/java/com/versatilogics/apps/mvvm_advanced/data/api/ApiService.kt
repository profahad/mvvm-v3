package com.versatilogics.apps.mvvm_advanced.data.api

import com.versatilogics.apps.mvvm_advanced.config.CONSTANTS
import com.versatilogics.apps.mvvm_advanced.data.models.Video
import com.versatilogics.apps.mvvm_advanced.network.response.ApiResponse
import com.versatilogics.apps.mvvm_advanced.network.response.PagingData
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @GET(CONSTANTS.API.GET_VIDEOS)
    suspend fun getVideos(): Response<ApiResponse<PagingData<Video>>>
}