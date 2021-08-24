package com.versatilogics.apps.mvvm_advanced.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import com.versatilogics.apps.mvvm_advanced.config.CONSTANTS
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson): Retrofit {

        if (!CONSTANTS.CONFIG.isProductionMode()) Timber.plant(Timber.DebugTree())
        val httpLoggingInterceptor = HttpLoggingInterceptor { message ->
            if (!CONSTANTS.CONFIG.isProductionMode()) Timber.i(message)
        }
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        val client = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor { chain ->
                chain.proceed(
                    chain.request()
                        .newBuilder()
                        .build()
                )
            }.readTimeout(CONSTANTS.NETWORK.READ_TIMEOUT, TimeUnit.SECONDS)
            .connectTimeout(CONSTANTS.NETWORK.CONNECTION_TIMEOUT, TimeUnit.SECONDS)
            .build()

        return Retrofit.Builder()
            .baseUrl(CONSTANTS.SERVER.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()

    }

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()
}