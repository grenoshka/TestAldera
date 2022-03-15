package com.example.testaldera.di

import com.example.data.network.ApiClient
import com.example.data.network.provideOkHttpClient
import com.example.data.network.provideRetrofitClient
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    @Singleton
    fun gson(): Gson =
        GsonBuilder().create()

    @Provides
    @Singleton
    fun apiClient(retrofit: Retrofit): ApiClient =
        retrofit.create(ApiClient::class.java)

    @Provides
    @Singleton
    fun retrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit {
        return provideRetrofitClient(okHttpClient, gson)
    }

    @Provides
    @Singleton
    fun okHttpClient(): OkHttpClient {
        return provideOkHttpClient()
    }
}