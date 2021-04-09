package com.genxaswhiz.library.extension

import android.app.Activity
import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.LayoutRes
import com.bumptech.glide.Glide
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, parent: ViewGroup? = this) {
    LayoutInflater.from(context).inflate(layoutRes, parent)
}

fun ImageView.show(resId: Int) {
    Glide.with(this)
        .load(resId)
        .into(this)
}

fun ImageView.show(imageUrl: String) {
    Glide.with(this)
        .load(imageUrl)
        .into(this)
}

fun ImageView.showSVG(context: Context, imageUrl: String) {
    val uri = Uri.parse(imageUrl)
    GlideToVectorYou.justLoadImage(context as Activity, uri, this)
}