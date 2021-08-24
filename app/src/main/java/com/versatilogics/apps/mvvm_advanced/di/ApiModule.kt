package com.versatilogics.apps.mvvm_advanced.di

import com.versatilogics.apps.mvvm_advanced.data.api.ApiService
import com.versatilogics.apps.mvvm_advanced.data.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pk.com.poc.app.data.api.RemoteSource
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Singleton
    @Provides
    fun provideRemoteDataSource(apiService: ApiService) = RemoteSource(apiService)

    @Singleton
    @Provides
    fun provideRepository(
        remoteSource: RemoteSource
    ) = Repository(remoteSource)

}