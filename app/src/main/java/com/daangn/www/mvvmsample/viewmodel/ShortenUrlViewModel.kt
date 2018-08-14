package com.daangn.www.mvvmsample.viewmodel

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.daangn.www.mvvmsample.model.Repository
import com.daangn.www.mvvmsample.utils.SingleLiveEvent
import com.rengwuxian.materialedittext.validation.METValidator
import com.rengwuxian.materialedittext.validation.RegexpValidator
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ShortenUrlViewModel(private val repository: Repository) : DisposableViewModel() {

    private val _shortenUrl = MutableLiveData<String>()
    private val _error = MutableLiveData<String>()
    private val _clickCopyToClipboard = SingleLiveEvent<String>()
    private val _clickOpenWeb = SingleLiveEvent<String>()
    private val _clickConvert = SingleLiveEvent<Any>()

    val showResult = MutableLiveData<Boolean>()

    //mutableLiveData를 immutable 하게 노출
    val shortenUrl: LiveData<String> get() = _shortenUrl
    val error: LiveData<String> get() = _error
    val clickCopyToClipboard: LiveData<String> get() = _clickCopyToClipboard
    val clickOpenWeb: LiveData<String> get() = _clickOpenWeb
    val clickConvert: LiveData<Any> get() = _clickConvert

    fun getEmailValidator(errorMessage: String): METValidator {
        return RegexpValidator(errorMessage, Patterns.WEB_URL.pattern())
    }

    fun getShortenUrl(url: String) {
        addDisposable(repository.getShortenUrl(url)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                showResult.postValue(true)
                _shortenUrl.postValue(it.url)
            }, {
                _error.postValue(it.message)
            }))
    }

    fun clickConvert() {
        _clickConvert.call()
    }

    fun clickCopyToClipboard(){
        _clickCopyToClipboard.value = _shortenUrl.value
    }

    fun clickOpenWeb() {
        _clickOpenWeb.value = _shortenUrl.value
    }
}