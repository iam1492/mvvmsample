package com.daangn.www.mvvmsample.viewmodel

import androidx.lifecycle.MutableLiveData
import com.daangn.www.mvvmsample.model.Repository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ShortenUrlViewModel(private val repository: Repository) : DisposableViewModel() {

    val shortenUrl: MutableLiveData<String> = MutableLiveData()
    val error: MutableLiveData<String> = MutableLiveData()
    val clickCopyToClipboard = SingleLiveEvent<Any>()

    fun getShortenUrl(url: String) {
        addDisposable(repository.getShortenUrl(url)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                shortenUrl.postValue(it.url)
            }, {
                error.postValue(it.message)
            }))
    }

    fun clickCopyToClipboard(){
        clickCopyToClipboard.call()
    }
}