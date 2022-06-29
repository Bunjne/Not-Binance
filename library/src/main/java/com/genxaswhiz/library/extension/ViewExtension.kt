package com.genxaswhiz.library.extension

import android.app.Activity
import android.content.Context
import android.net.Uri
import android.text.Html
import android.text.method.LinkMovementMethod
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
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
        .transform(GranularRoundedCorners(32f, 32f, 16f, 16f))
        .into(this)
}

fun ImageView.showSVG(context: Context, imageUrl: String) {
    if (imageUrl.endsWith("svg")) {
        val uri = Uri.parse(imageUrl)
        GlideToVectorYou.justLoadImage(context as Activity, uri, this)
    } else {
        Glide.with(this)
            .load(imageUrl)
            .into(this)
    }
}

fun TextView.makeTextLinkable(description: String) {
    text = Html.fromHtml(description)
    movementMethod = LinkMovementMethod.getInstance()
}

