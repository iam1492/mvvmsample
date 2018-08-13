package com.daangn.www.mvvmsample.api

import com.daangn.www.mvvmsample.model.ShortenUrl

data class ShortenUrlResponse(val message: String,
                              val result: ShortenUrl,
                              val code: String)