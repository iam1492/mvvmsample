package com.daangn.www.mvvmsample.view

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.daangn.www.mvvmsample.R
import com.daangn.www.mvvmsample.databinding.ActivityShortenUrlBinding
import com.daangn.www.mvvmsample.viewmodel.ShortenUrlViewModel
import com.daangn.www.mvvmsample.viewmodel.ShortenUrlViewModelFactory
import org.koin.android.ext.android.inject

class ShortenUrlActivity : BaseActivity<ActivityShortenUrlBinding>() {

    override val layoutResourceId: Int = R.layout.activity_shorten_url

    private val shortenUrlViewModelFactory: ShortenUrlViewModelFactory by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val shortenUrlViewModel = ViewModelProviders.of(this, shortenUrlViewModelFactory).get(ShortenUrlViewModel::class.java)
//        shortenUrlViewModel.getShortenUrl("https://google.com")
        shortenUrlViewModel.error.observe(this, Observer<String> { t -> Toast.makeText(this@ShortenUrlActivity, t, Toast.LENGTH_LONG).show() })

        viewDataBinding.shortenUrlViewModel = shortenUrlViewModel
        viewDataBinding.setLifecycleOwner(this)
    }
}
