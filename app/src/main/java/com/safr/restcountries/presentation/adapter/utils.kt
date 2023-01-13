package com.safr.restcountries.presentation.adapter

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.safr.restcountries.R


fun ImageView.loadImageUtils(link: String?) {
            glideImgUtils(context, link, this)
    }

private fun glideImgUtils(context: Context, link: String?, img: ImageView) {
    Glide.with(context)
        .asBitmap()
        .load(link)
        .placeholder(R.drawable.ic_launcher_background)
        .into(img)
}