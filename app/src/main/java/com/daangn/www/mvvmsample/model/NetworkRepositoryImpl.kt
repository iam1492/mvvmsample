package com.daangn.www.mvvmsample.model

import com.daangn.www.mvvmsample.api.Api
import io.reactivex.Single

class NetworkRepositoryImpl(val api: Api): Repository {
    override fun getShortenUrl(url: String): Single<ShortenUrl> {
        return api.shorturl(url)
            .map { shortenUrlResponse ->
                ShortenUrl(
                    url = shortenUrlResponse.result.url,
                    hash = shortenUrlResponse.result.hash,
                    orgUrl = shortenUrlResponse.result.orgUrl)
            }
    }
}