package com.ciyp.utils.glide


import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.mikhaellopez.circularimageview.CircularImageView


fun loadImage(context: Context, url: String, circularImageView: CircularImageView) {
    Glide.with(context)
        .load(url)
        .fitCenter()
        .centerCrop()
        .into(circularImageView)
}

fun loadImage(context: Context, drawable: Int, circularImageView: CircularImageView) {
    Glide
        .with(context)
        .load(drawable)
        .fitCenter()
        .centerCrop()
        .into(circularImageView)
}

fun loadImage(context: Context, drawable: Int, imageView: ImageView) {
    Glide
        .with(context)
        .load(drawable)
        .fitCenter()
        .centerCrop()
        .into(imageView)
}

fun loadImage(context: Context, url: String, placeholder: Int, imageView: ImageView) {
    Glide.with(context)
        .load(url)
        .placeholder(placeholder)
        .fitCenter()
        .centerCrop()
        .into(imageView)
}




