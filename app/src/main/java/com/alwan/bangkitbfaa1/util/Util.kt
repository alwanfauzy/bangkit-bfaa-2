package com.alwan.bangkitbfaa1.util

import android.widget.ImageView
import com.alwan.bangkitbfaa1.R
import com.bumptech.glide.Glide

fun ImageView.loadImage(url: Int?) {
    Glide.with(this.context)
        .load(url)
        .centerCrop()
        .placeholder(R.drawable.avatar_placeholder)
        .into(this)
}
