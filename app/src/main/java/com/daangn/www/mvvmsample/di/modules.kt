package com.daangn.www.mvvmsample.di

import com.daangn.www.mvvmsample.BuildConfig
import com.daangn.www.mvvmsample.R
import com.daangn.www.mvvmsample.api.Api
import com.daangn.www.mvvmsample.utils.baseUrl
import com.daangn.www.mvvmsample.utils.headerInterceptor
import com.daangn.www.mvvmsample.utils.loggingInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module.Module
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val apiModules: Module = module {
    single {
        Retrofit.Builder()
            .client(OkHttpClient.Builder()
                .addInterceptor(get(headerInterceptor))
                .addInterceptor(get(loggingInterceptor))
                .build())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .build()
            .create(Api::class.java)
    }

    single(headerInterceptor) {
        Interceptor { chain ->
            val builder = chain.request().newBuilder().apply {
                header("X-Naver-Client-Id", androidContext().resources.getString(R.string.naver_client_id))
                header("X-Naver-Client-Secret", androidContext().resources.getString(R.string.naver_api_key))
            }
            chain.proceed(builder.build())
        }
    }

    single(loggingInterceptor) {
        HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG)
                HttpLoggingInterceptor.Level.BASIC
            else
                HttpLoggingInterceptor.Level.NONE
        } as Interceptor
    }
}

val appModules = listOf(apiModules, shortenUrlModules)