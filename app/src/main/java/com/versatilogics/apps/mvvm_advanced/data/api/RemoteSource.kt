package pk.com.poc.app.data.api

import com.versatilogics.apps.mvvm_advanced.data.api.ApiService
import com.versatilogics.apps.mvvm_advanced.network.middlewares.BaseDataSource
import javax.inject.Inject

class RemoteSource @Inject constructor(private val apiService: ApiService) : BaseDataSource() {

    suspend fun getVideos() =
        getResult { apiService.getVideos() }

}