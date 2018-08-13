package com.daangn.www.mvvmsample.di

import com.daangn.www.mvvmsample.model.NetworkRepositoryImpl
import com.daangn.www.mvvmsample.model.Repository
import com.daangn.www.mvvmsample.viewmodel.ShortenUrlViewModelFactory
import org.koin.dsl.module.Module
import org.koin.dsl.module.module

val shortenUrlModules: Module = module {
    factory {
        NetworkRepositoryImpl(get()) as Repository
    }

    factory {
        ShortenUrlViewModelFactory(get())
    }
}