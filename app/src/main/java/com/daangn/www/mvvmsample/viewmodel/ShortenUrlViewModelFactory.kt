package com.daangn.www.mvvmsample.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.daangn.www.mvvmsample.model.Repository

@Suppress("UNCHECKED_CAST")
class ShortenUrlViewModelFactory(private val repository: Repository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ShortenUrlViewModel(repository) as T
    }
}