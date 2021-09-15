package com.weber.tcgbusfsdemo.di

import com.weber.tcgbusfsdemo.network.ParseplatformService
import com.weber.tcgbusfsdemo.network.TcgbusfsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class ParseplatformRetrofit

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class TcgbusfsRetrofit

    @Singleton
    @Provides
    fun okHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor())
            .build()
    }

    @ParseplatformRetrofit
    @Provides
    fun retrofitParseplatform(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://watch-master-staging.herokuapp.com/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    @TcgbusfsRetrofit
    @Provides
    fun retrofitTcgbusfs(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://tcgbusfs.blob.core.windows.net/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun parseplatformService(@ParseplatformRetrofit retrofit: Retrofit): ParseplatformService {
        return retrofit.create(ParseplatformService::class.java)
    }

    @Provides
    fun tcgbusfsService(@TcgbusfsRetrofit retrofit: Retrofit): TcgbusfsService {
        return retrofit.create(TcgbusfsService::class.java)
    }
}