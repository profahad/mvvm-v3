package com.versatilogics.apps.mvvm_advanced.data.repository

import com.versatilogics.apps.mvvm_advanced.network.middlewares.processResponse
import pk.com.poc.app.data.api.RemoteSource
import javax.inject.Inject

class Repository @Inject constructor(private val remoteSource: RemoteSource) {

    /**
     * Get Latest Videos
     * @return LiveData<Response<ApiResponse<PagingData<Video>>>>
     */
    fun getVideos() =
        processResponse { remoteSource.getVideos() }
}
