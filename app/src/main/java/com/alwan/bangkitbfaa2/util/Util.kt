package com.alwan.bangkitbfaa2.util

import android.widget.ImageView
import com.alwan.bangkitbfaa2.R
import com.bumptech.glide.Glide

fun ImageView.loadImage(url: String?) {
    Glide.with(this.context)
        .load(url)
        .centerCrop()
        .placeholder(R.drawable.avatar_placeholder)
        .into(this)
}
