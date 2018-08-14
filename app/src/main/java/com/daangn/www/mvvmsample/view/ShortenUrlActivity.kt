package com.daangn.www.mvvmsample.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.daangn.www.mvvmsample.R
import com.daangn.www.mvvmsample.databinding.ActivityShortenUrlBinding
import com.daangn.www.mvvmsample.utils.copyToClipBoard
import com.daangn.www.mvvmsample.viewmodel.ShortenUrlViewModel
import com.daangn.www.mvvmsample.viewmodel.ShortenUrlViewModelFactory
import org.koin.android.ext.android.inject

class ShortenUrlActivity : BaseActivity<ActivityShortenUrlBinding>() {

    override val layoutResourceId: Int = R.layout.activity_shorten_url

    private val shortenUrlViewModelFactory: ShortenUrlViewModelFactory by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val shortenUrlViewModel = ViewModelProviders.of(this, shortenUrlViewModelFactory).get(ShortenUrlViewModel::class.java)

/*
        shortenUrlViewModel.clickConvert.observe(this, Observer {
            shortenUrlViewModel.getShortenUrl(viewDataBinding.urlEditText.text.toString())
        })
*/

        shortenUrlViewModel.observeConvertToShortenUrl().observe(this, Observer {
            shortenUrlViewModel.getShortenUrl(viewDataBinding.urlEditText.text.toString())
        })

        shortenUrlViewModel.error.observe(this, Observer<String> { t ->
            Toast.makeText(this@ShortenUrlActivity, t, Toast.LENGTH_LONG).show()
        })

        shortenUrlViewModel.clickCopyToClipboard.observe(this, Observer { clipUrl ->
            copyToClipBoard(clipUrl) {
                Toast.makeText(this@ShortenUrlActivity, getString(R.string.clipboard_copied, clipUrl), Toast.LENGTH_LONG).show()
            }
        })

        shortenUrlViewModel.clickOpenWeb.observe(this, Observer { clipUrl ->
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(clipUrl)))
        })

        viewDataBinding.urlEditText.addValidator(shortenUrlViewModel.getEmailValidator(getString(R.string.error_validate_email)))

        viewDataBinding.shortenUrlViewModel = shortenUrlViewModel
        viewDataBinding.setLifecycleOwner(this)
    }
}
