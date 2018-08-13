package com.daangn.www.mvvmsample.model

import io.reactivex.Single

interface Repository {
    fun getShortenUrl(url: String): Single<ShortenUrl>
}