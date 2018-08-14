package com.daangn.www.mvvmsample.utils

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import androidx.annotation.UiThread
import androidx.core.content.getSystemService
import com.daangn.www.mvvmsample.R

@UiThread
fun Context.copyToClipBoard(content: String, callback: (() -> Unit)? = null) {
    val clip = ClipData.newPlainText(resources.getString(R.string.clip_label), content)
    val clipboard: ClipboardManager? = getSystemService()
    clipboard?.primaryClip = clip
    callback?.invoke()
}