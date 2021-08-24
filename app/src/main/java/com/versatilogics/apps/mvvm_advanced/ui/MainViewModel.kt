package com.versatilogics.apps.mvvm_advanced.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.versatilogics.apps.mvvm_advanced.data.models.Video
import com.versatilogics.apps.mvvm_advanced.data.repository.Repository
import com.versatilogics.apps.mvvm_advanced.network.enums.Response
import com.versatilogics.apps.mvvm_advanced.network.response.ApiResponse
import com.versatilogics.apps.mvvm_advanced.network.response.PagingData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _uiVideoState: LiveData<Response<ApiResponse<PagingData<Video>>>> = repository.getVideos()

    val uiVideoState: LiveData<Response<ApiResponse<PagingData<Video>>>>
        get() = _uiVideoState

}